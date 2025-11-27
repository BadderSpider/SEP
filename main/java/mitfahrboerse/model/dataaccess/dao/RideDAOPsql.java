package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.*;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;
import mitfahrboerse.model.dataaccess.exceptions.NoResultException;
import mitfahrboerse.model.dataaccess.util.DAOMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;

/**
 * The PostgreSQL-specific implementation of the {@link RideDAO} interface.
 * It manages the persistence of ride templates, concrete rides, and stops
 * by executing SQL queries against the database within a transactional context.
 *
 * @author Jonathan Schilcher
 */
public final class RideDAOPsql extends AbstractDAO implements RideDAO {

    /**
     * Constructs a new RideDAOPsql with a database connection provided by a transaction.
     * This ensures all operations are part of the same atomic transaction.
     *
     * @param connection The database connection from the current transaction context.
     */
    public RideDAOPsql(Connection connection) {
        super(connection);
    }


    @Override
    public long createRide(AbstractRideDTO ride) throws DataAccessException {
        String sql = "INSERT INTO t_drive (driver_id, description, max_seats, price_per_person, transportation_type, max_luggage_pieces) VALUES (?, ?, ?, ?, ?, ?)";
        long rideId = create(sql,
                ride.getDriverId(),
                ride.getDescription(),
                ride.getMaxSeats(),
                ride.getPricePerSeat(),
                ride.getTransportationType(),
                ride.getMaxBaggage()
        ).orElse(-1L);

        if (rideId > 0 && ride.getStops() != null) {
            for (StopDTO stop : ride.getStops()) {
                addStopToRide(rideId, stop);
            }
        }
        return rideId;
    }

    @Override
    public void updateRide(AbstractRideDTO ride) throws DataAccessException {
        String sql = "UPDATE t_drive SET description = ?, max_seats = ?, price_per_person = ?, transportation_type = ?, max_luggage_pieces = ? WHERE drive_id = ?";
        execute(sql,
                ride.getDescription(),
                ride.getMaxSeats(),
                ride.getPricePerSeat(),
                ride.getTransportationType(),
                ride.getMaxBaggage(),
                ride.getRideId()
        );
    }

    @Override
    public void deleteRide(long rideId) throws DataAccessException {
        String sql = "DELETE FROM t_drive WHERE drive_id = ?";
        execute(sql, rideId);
    }

    @Override
    public void findRideById(AbstractRideDTO abstractRideDTO) throws DataAccessException {
        String sql = "SELECT * FROM t_drive WHERE drive_id = ?";
        Optional<AbstractRideDTO> optRide = findOne(sql, DAOMapper::mapAbstractRide, abstractRideDTO.getRideId());
        if (optRide.isPresent()) {
            AbstractRideDTO ride = optRide.get();
            fillAbstractAttrs(abstractRideDTO, ride);
        } else {
            throw new NoResultException("Ride with id " + abstractRideDTO.getRideId() + " not found");
        }
    }

    @Override
    public void addStopToRide(long rideId, StopDTO stop) throws DataAccessException {
        String shiftSql = "UPDATE t_waypoint SET sequence = sequence + 1 WHERE drive_id = ? AND sequence >= ?";
        execute(shiftSql, rideId, stop.getOrder());

        // SQL zum Einfügen des neuen Wegpunkts an der jetzt freien Position
        String insertSql = "INSERT INTO t_waypoint (drive_id, address, type, sequence, timestamp) VALUES (?, ?, ?, ?, ?)";
        execute(insertSql,
                rideId,
                stop.getLocation(),
                stop.getType(),
                stop.getOrder(),
                Timestamp.from(stop.getTimeStamp().toInstant())
        );
    }

    @Override
    public void deleteStopFromRide(long stopId) throws DataAccessException {
        String sql = "DELETE FROM t_waypoint WHERE waypoint_id = ?";
        execute(sql, stopId);
    }

    @Override
    public long createConcreteRide(RideDTO concreteRide) throws DataAccessException {
        long rideId = concreteRide.getRideId();
        if (rideId <= 0) {
            rideId = createRide(concreteRide);
        }

        String sql = "INSERT INTO t_concrete_drive (drive_id, drive_date, status) VALUES (?, ?, ?)";
        execute(sql, rideId, Date.valueOf(concreteRide.getDate()), concreteRide.getRideStatus());
        return rideId;
    }

    @Override
    public void updateConcreteRideStatus(long concreteRideId, RideStatus status) throws DataAccessException {
        String sql = "UPDATE t_concrete_drive SET status = ? WHERE drive_id = ?";
        execute(sql, status, concreteRideId);
    }

    @Override
    public void findConcreteRideById(RideDTO rideDTO) throws DataAccessException {
        String sql = "SELECT * FROM v_concrete_drive_details WHERE drive_id = ?";
        Optional<RideDTO> optRide = findOne(sql, DAOMapper::mapRide, rideDTO.getRideId());
        if (optRide.isPresent()) {
            RideDTO ride = optRide.get();
            fillAbstractAttrs(rideDTO, ride);
            rideDTO.setDate(ride.getDate());
            rideDTO.setPublicationDate(ride.getPublicationDate());
            rideDTO.setRideStatus(ride.getRideStatus());
        } else {
            throw new NoResultException("Concrete ride with id " + rideDTO.getRideId() + " not found");
        }
    }

    @Override
    public long createRecurringRide(CycleDTO cycle) throws DataAccessException {
        long rideId = createRide(cycle);
        String sql = "INSERT INTO t_recurrence (drive_id, rule, start_date, end_date) VALUES (?, ?, ?, ?)";
        execute(sql,
                rideId,
                cycle.getRule(),
                Date.valueOf(cycle.getStart()),
                Date.valueOf(cycle.getEnd())
        );
        return rideId;
    }

    @Override
    public void findRecurringRideById(CycleDTO cycleDTO) throws DataAccessException {
        String sql = "SELECT * FROM v_recurring_drive_details WHERE drive_id = ?";
        Optional<CycleDTO> optCycle = findOne(sql, DAOMapper::mapRecurringRide, cycleDTO.getRideId());
        if (optCycle.isPresent()) {
            CycleDTO cycle = optCycle.get();
            fillAbstractAttrs(cycleDTO, cycle);
            cycleDTO.setRule(cycle.getRule());
            cycleDTO.setStart(cycle.getStart());
            cycleDTO.setEnd(cycle.getEnd());
            // TODO: cycleDTO.setExceptions();
        } else {
            throw new NoResultException("Recurring ride with id " + cycleDTO.getRideId() + " not found");
        }
    }

    @Override
    public List<StopDTO> findStopsByRideId(long rideId) throws DataAccessException {
        String sql = """
            SELECT
                w.waypoint_id,
                w.drive_id,
                w.address,
                w.type,
                w.sequence,
                w.timestamp,
                -- Berechnete Spalten für das DTO:
                (d.max_seats - COALESCE(SUM(b.booked_seats), 0)) AS number_of_people,
                (d.max_luggage_pieces - COALESCE(SUM(b.booked_luggage_pieces), 0)) AS number_of_luggage_pieces
            FROM t_waypoint w
            JOIN t_drive d ON w.drive_id = d.drive_id
            -- Left Join, um Buchungen zu finden, die diesen Wegpunkt abdecken
            LEFT JOIN (
                SELECT
                    bk.booked_seats,
                    bk.booked_luggage_pieces,
                    w_in.sequence AS start_seq,
                    w_out.sequence AS end_seq,
                    bk.concrete_drive_id
                FROM t_booking bk
                JOIN t_waypoint w_in ON bk.entry_waypoint_id = w_in.waypoint_id
                JOIN t_waypoint w_out ON bk.exit_waypoint_id = w_out.waypoint_id
                WHERE bk.status = 'ACCEPTED' -- Nur akzeptierte Buchungen zählen
            ) b ON w.drive_id = b.concrete_drive_id
                AND w.sequence >= b.start_seq  -- Passagier ist schon eingestiegen (oder steigt hier ein)
                AND w.sequence < b.end_seq     -- Passagier ist NOCH NICHT ausgestiegen
            WHERE w.drive_id = ?
            GROUP BY w.waypoint_id, d.max_seats, d.max_luggage_pieces
            ORDER BY w.sequence ASC;
        """;

        return findAll(sql, DAOMapper::mapStop, rideId);
    }

    @Override
    public List<BookingDTO> findBookingsByConcreteRideId(long concreteRideId) throws DataAccessException {
        String sql = "SELECT * FROM t_booking WHERE concrete_drive_id = ?";
        return findAll(sql, DAOMapper::mapBooking, concreteRideId);
    }

    @Override
    public List<OfferDTO> findOffersByConcreteRideId(long rideId) throws DataAccessException {
        String sql = "SELECT * FROM t_offer WHERE drive_id = ?";
        return findAll(sql, DAOMapper::mapOffer, rideId);
    }


    /**
    @autor Matthias Schmitt
     **/
    @Override
    public List<RideDTO> findRidesByCriteria(SearchCriteriaDTO criteria) throws DataAccessException {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM v_concrete_drive_details cd ");

        applyFilters(criteria, sql, params);

        if (criteria.getSorting() != null) {
            String sortBy = criteria.getSorting().getSortBy();
            String direction = criteria.getSorting().getDirection().name();

            String dbColumn = switch (sortBy) {
                case "pricePerSeat" -> "cd.price_per_person";
                case "startTime" -> "cd.drive_date";
                case "maxSeats" -> "cd.max_seats";
                case "maxBaggage" -> "cd.max_luggage_pieces";
                default -> "cd.drive_date";
            };
            sql.append(" ORDER BY ").append(dbColumn).append(" ").append(direction);
        } else {
            sql.append(" ORDER BY cd.drive_date ASC");
        }

        if (criteria.getPagination() != null) {
            sql.append(" LIMIT ? OFFSET ?");
            params.add(criteria.getPagination().getPageSize());
            params.add(criteria.getPagination().getOffset());
        }

        List<RideDTO> rides = findAll(sql.toString(), DAOMapper::mapRide, params.toArray());

        for (RideDTO ride : rides) {
            ride.setStops(findStopsByRideId(ride.getRideId()));
        }

        return rides;
    }

    @Override
    public long countRidesByCriteria(SearchCriteriaDTO criteria) throws DataAccessException {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT COUNT(*) FROM v_concrete_drive_details cd ");

        applyFilters(criteria, sql, params);

        return findScalar(sql.toString(), Long.class, params.toArray()).orElse(0L);
    }

    /**
     @autor Matthias Schmitt
     */
    private void applyFilters(SearchCriteriaDTO criteria, StringBuilder sql, List<Object> params) {
        sql.append(" WHERE 1=1 ");

        Map<String, Object> filters = criteria.getFilters();
        if (filters == null || filters.isEmpty()) {
            return;
        }

        if (isValidFilter(filters, "startAddress")) {
            sql.append(" AND EXISTS (SELECT 1 FROM t_waypoint w WHERE w.drive_id = cd.drive_id AND w.type != 'FINISH' AND w.address ILIKE ?) ");
            params.add("%" + filters.get("startAddress") + "%");
        }

        if (isValidFilter(filters, "endAddress")) {
            sql.append(" AND EXISTS (SELECT 1 FROM t_waypoint w WHERE w.drive_id = cd.drive_id AND w.type != 'START' AND w.address ILIKE ?) ");
            params.add("%" + filters.get("endAddress") + "%");
        }

        if (isValidFilter(filters, "pricePerSeat")) {
            try {
                double maxPrice = Double.parseDouble(filters.get("pricePerSeat").toString());
                sql.append(" AND cd.price_per_person <= ? ");
                params.add(maxPrice);
            } catch (NumberFormatException ignored) {}
        }

        if (isValidFilter(filters, "transportVehicle")) {
            sql.append(" AND cd.transportation_type = ?::transportation_type ");
            params.add(filters.get("transportVehicle"));
        }

        // Zeit (Optional)
        if (isValidFilter(filters, "startTime")) {
            // Datumslogik hier ergänzen
        }
    }

    private boolean isValidFilter(Map<String, Object> filters, String key) {
        return filters.containsKey(key) && filters.get(key) != null && !filters.get(key).toString().isBlank();
    }


    private void fillAbstractAttrs(AbstractRideDTO fill, AbstractRideDTO values) throws DataAccessException {
        fill.setRideId(values.getRideId());
        fill.setDriverId(values.getDriverId());
        fill.setTransportationType(values.getTransportationType());
        fill.setPricePerSeat(values.getPricePerSeat());
        fill.setMaxSeats(values.getMaxSeats());
        fill.setMaxBaggage(values.getMaxBaggage());
        fill.setDescription(values.getDescription());
        fill.setStops(findStopsByRideId(values.getRideId()));
    }
}