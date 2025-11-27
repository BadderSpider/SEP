-- =================================================================
-- 1. ENUMERATION TYPES
-- =================================================================
DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'USER_ROLE') THEN CREATE TYPE USER_ROLE AS ENUM ('USER', 'ADMIN'); END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'TOKEN_TYPE') THEN CREATE TYPE TOKEN_TYPE AS ENUM ('EMAIL', 'PASSWORD'); END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'DRIVE_STATUS') THEN CREATE TYPE DRIVE_STATUS AS ENUM ('PLANNED', 'ACTIVE', 'COMPLETED', 'CANCELLED'); END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'BOOKING_STATUS') THEN CREATE TYPE BOOKING_STATUS AS ENUM ('REQUESTED', 'ACCEPTED', 'DECLINED', 'CANCELED'); END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'OFFER_STATUS') THEN CREATE TYPE OFFER_STATUS AS ENUM ('OPEN', 'ACCEPTED', 'WITHDRAWN'); END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'WAYPOINT_TYPE') THEN CREATE TYPE WAYPOINT_TYPE AS ENUM ('START', 'STOP', 'FINISH'); END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'CYCLE_TYPE') THEN CREATE TYPE CYCLE_TYPE AS ENUM ('DAILY', 'WEEKDAYS', 'WEEKENDS', 'WEEKLY', 'MONTHLY'); END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'TRANSPORTATION_TYPE') THEN CREATE TYPE TRANSPORTATION_TYPE AS ENUM ('CAR', 'RE_TRAIN', 'IC_TRAIN', 'RE_BUS', 'IC_BUS'); END IF;
END$$;

-- =================================================================
-- 2. CORE TABLES
-- =================================================================

CREATE TABLE IF NOT EXISTS t_settings (
    settings_id INTEGER DEFAULT 0 PRIMARY KEY,
    enterprise_name VARCHAR(20) DEFAULT 'ride_share',
    logo_id BYTEA NOT NULL,
    logo_mimetype VARCHAR(50) NOT NULL,
    time_until_deletion_after_drive INTERVAL DEFAULT INTERVAL '60m' NOT NULL,
    time_until_drive_after_publishing INTERVAL DEFAULT INTERVAL '60m' NOT NULL,
    deadline_to_drive INTERVAL DEFAULT INTERVAL '60m' NOT NULL,
    css_file VARCHAR(50) DEFAULT 'template.css' NOT NULL,
    CHECK (settings_id = 0)
);

CREATE TABLE IF NOT EXISTS t_user (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL CHECK (email LIKE '%_@_%._%'),
    password_hash VARCHAR(255) NOT NULL,
    password_salt VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    role USER_ROLE NOT NULL DEFAULT 'USER',
    email_verified BOOLEAN NOT NULL DEFAULT FALSE,
    average_rating NUMERIC(3, 2) DEFAULT 0.00 CHECK (average_rating BETWEEN 0 AND 5),
    number_of_ratings INTEGER NOT NULL DEFAULT 0 CHECK (number_of_ratings >= 0),
    street VARCHAR(255),
    house_number VARCHAR(10),
    postal_code VARCHAR(10),
    city VARCHAR(100),
    country VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS t_profile_picture (
    user_id INTEGER PRIMARY KEY REFERENCES t_user(user_id) ON DELETE CASCADE,
    image_data BYTEA NOT NULL,
    mimetype VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS t_verification_tokens (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES t_user(user_id) ON DELETE CASCADE,
    token_type TOKEN_TYPE NOT NULL,
    token_hash VARCHAR(255) NOT NULL UNIQUE,
    new_email VARCHAR(255) UNIQUE CHECK (new_email LIKE '%_@_%._%'),
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS t_drive (
    drive_id SERIAL PRIMARY KEY,
    driver_id INTEGER NOT NULL REFERENCES t_user(user_id) ON DELETE CASCADE,
    description TEXT,
    max_seats INTEGER NOT NULL CHECK (max_seats > 0),
    price_per_person NUMERIC(10, 2) NOT NULL CHECK (price_per_person >= 0),
    transportation_type TRANSPORTATION_TYPE NOT NULL,
    publication_date DATE NOT NULL DEFAULT NOW(),
    max_luggage_pieces INTEGER NOT NULL DEFAULT 0 CHECK (max_luggage_pieces >= 0)
);

CREATE TABLE IF NOT EXISTS t_recurrence (
    drive_id INTEGER PRIMARY KEY REFERENCES t_drive(drive_id) ON DELETE CASCADE,
    rule CYCLE_TYPE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CHECK (end_date >= start_date)
);

CREATE TABLE IF NOT EXISTS t_recurrence_exception (
    recurrence_id INTEGER NOT NULL REFERENCES t_recurrence(drive_id) ON DELETE CASCADE,
    exception_date DATE NOT NULL,
    PRIMARY KEY (recurrence_id, exception_date)
);

CREATE TABLE IF NOT EXISTS t_concrete_drive (
    drive_id INTEGER PRIMARY KEY NOT NULL REFERENCES t_drive(drive_id) ON DELETE CASCADE,
    drive_date DATE NOT NULL,
    status DRIVE_STATUS NOT NULL DEFAULT 'PLANNED'
);

CREATE TABLE IF NOT EXISTS t_waypoint (
    waypoint_id SERIAL PRIMARY KEY,
    drive_id INTEGER NOT NULL REFERENCES t_drive(drive_id) ON DELETE CASCADE,
    address VARCHAR(255) NOT NULL,
    type WAYPOINT_TYPE NOT NULL,
    sequence INTEGER NOT NULL CHECK (sequence >= 0),
    timestamp TIMESTAMP WITH TIME ZONE NOT NULL,
    UNIQUE (drive_id, sequence)
);

CREATE TABLE IF NOT EXISTS t_request (
    request_id SERIAL PRIMARY KEY,
    requester_id INTEGER NOT NULL REFERENCES t_user(user_id) ON DELETE CASCADE,
    start_location VARCHAR(255) NOT NULL,
    destination_location VARCHAR(255) NOT NULL,
    desired_timestamp TIMESTAMP WITH TIME ZONE NOT NULL,
    price INTEGER CHECK (price >= 0),
    baggage INTEGER CHECK (baggage >= 0),
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS t_booking (
    booking_id SERIAL PRIMARY KEY,
    concrete_drive_id INTEGER NOT NULL REFERENCES t_concrete_drive(drive_id) ON DELETE RESTRICT,
    passenger_id INTEGER NOT NULL REFERENCES t_user(user_id) ON DELETE RESTRICT,
    entry_waypoint_id INTEGER NOT NULL REFERENCES t_waypoint(waypoint_id) ON DELETE RESTRICT,
    exit_waypoint_id INTEGER NOT NULL REFERENCES t_waypoint(waypoint_id) ON DELETE RESTRICT,
    booked_luggage_pieces INTEGER NOT NULL DEFAULT 0 CHECK (booked_luggage_pieces >= 0),
    booked_seats INTEGER NOT NULL CHECK (booked_seats > 0),
    status BOOKING_STATUS NOT NULL DEFAULT 'REQUESTED',
    UNIQUE (concrete_drive_id, passenger_id)
);

CREATE TABLE IF NOT EXISTS t_offer (
    offer_id SERIAL PRIMARY KEY,
    request_id INTEGER NOT NULL REFERENCES t_request(request_id) ON DELETE CASCADE,
    drive_id INTEGER NOT NULL REFERENCES t_drive(drive_id) ON DELETE CASCADE,
    status OFFER_STATUS NOT NULL DEFAULT 'OPEN',
    UNIQUE (request_id, drive_id)
);

-- =================================================================
-- 3. TRIGGERS
-- =================================================================

CREATE OR REPLACE FUNCTION check_booking_integrity_func() RETURNS TRIGGER AS $$
DECLARE
    v_drive_id_from_concrete_drive INTEGER;
    v_entry_waypoint RECORD;
    v_exit_waypoint RECORD;
BEGIN
    SELECT drive_id INTO v_drive_id_from_concrete_drive
    FROM t_concrete_drive
    WHERE drive_id = NEW.concrete_drive_id;

    SELECT drive_id, sequence INTO v_entry_waypoint FROM t_waypoint WHERE waypoint_id = NEW.entry_waypoint_id;
    SELECT drive_id, sequence INTO v_exit_waypoint FROM t_waypoint WHERE waypoint_id = NEW.exit_waypoint_id;

    IF v_entry_waypoint.drive_id != v_drive_id_from_concrete_drive THEN
        RAISE EXCEPTION 'Invariant violated: The entry waypoint does not belong to the booked drive.';
    END IF;

    IF v_exit_waypoint.drive_id != v_drive_id_from_concrete_drive THEN
        RAISE EXCEPTION 'Invariant violated: The exit waypoint does not belong to the booked drive.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_check_booking_integrity BEFORE INSERT OR UPDATE ON t_booking
    FOR EACH ROW EXECUTE FUNCTION check_booking_integrity_func();


-- =================================================================
-- 4. VIEWS
-- =================================================================

-- View für vollständige Details einer konkreten Fahrt
CREATE OR REPLACE VIEW v_concrete_drive_details AS
SELECT
    d.drive_id,
    d.driver_id,
    d.description,
    d.max_seats,
    d.price_per_person,
    d.transportation_type,
    d.publication_date,
    d.max_luggage_pieces,
    cd.drive_date,
    cd.status
FROM t_drive d
         JOIN t_concrete_drive cd ON d.drive_id = cd.drive_id;

-- View für vollständige Details einer wiederkehrenden Fahrt
CREATE OR REPLACE VIEW v_recurring_drive_details AS
SELECT
    d.drive_id,
    d.driver_id,
    d.description,
    d.max_seats,
    d.price_per_person,
    d.transportation_type,
    d.publication_date,
    d.max_luggage_pieces,
    r.rule,
    r.start_date,
    r.end_date
FROM t_drive d
         JOIN t_recurrence r ON d.drive_id = r.drive_id;

-- View, um Buchungsdetails mit Fahrerinformationen zu verknüpfen
CREATE OR REPLACE VIEW v_booking_details AS
SELECT
    b.booking_id,
    b.concrete_drive_id,
    b.passenger_id,
    b.entry_waypoint_id,
    b.exit_waypoint_id,
    b.booked_luggage_pieces,
    b.booked_seats,
    b.status,
    vcd.driver_id
FROM t_booking b
         JOIN v_concrete_drive_details vcd ON b.concrete_drive_id = vcd.drive_id;

-- View, um Angebotsdetails mit Fahrerinformationen zu verknüpfen
CREATE OR REPLACE VIEW v_offer_details AS
SELECT
    o.offer_id,
    o.request_id,
    o.drive_id,
    o.status,
    d.driver_id
FROM t_offer o
         JOIN t_drive d ON o.drive_id = d.drive_id;