package mitfahrboerse.model.dataaccess.util;

import mitfahrboerse.global.dto.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;

/**
 * A utility class containing static methods for mapping {@link ResultSet} rows to Data Transfer Objects (DTOs).
 * This centralizes mapping logic, promoting code reuse and consistency across different DAOs.
 *
 * @author Jonathan Schilcher
 */
public final class DAOMapper {

    private DAOMapper() {}

    /**
     * Maps the current row of a ResultSet to a comprehensive {@link UserDTO}.
     */
    public static UserDTO mapUser(ResultSet rs) throws SQLException {
        AddressDTO address = new AddressDTO(
                rs.getString("country"),
                rs.getString("city"),
                rs.getString("postal_code"),
                rs.getString("street"),
                rs.getString("house_number")
        );

        return new UserDTO(
                rs.getLong("user_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                address,
                UserRole.valueOf(rs.getString("role")),
                rs.getInt("number_of_ratings"),
                rs.getDouble("average_rating"),
                rs.getBoolean("email_verified")
        );
    }

    /**
     * Maps the current row of a ResultSet to an {@link ImageDTO}.
     */
    public static ImageDTO mapImage(ResultSet rs) throws SQLException {
        return new ImageDTO(
                rs.getLong("user_id"),
                rs.getString("mimetype"),
                rs.getBytes("image_data")
        );
    }

    /**
     * Maps the current row of a ResultSet to a {@link BookingDTO}.
     */
    public static BookingDTO mapBooking(ResultSet rs) throws SQLException {
        return new BookingDTO(
                rs.getLong("booking_id"),
                rs.getLong("concrete_drive_id"),
                rs.getLong("passenger_id"),
                rs.getLong("entry_waypoint_id"),
                rs.getLong("exit_waypoint_id"),
                BookingStatus.valueOf(rs.getString("status")),
                rs.getInt("booked_seats"),
                rs.getInt("booked_luggage_pieces")
        );
    }

    /**
     * Maps the current row of a ResultSet to an {@link OfferDTO}.
     */
    public static OfferDTO mapOffer(ResultSet rs) throws SQLException {
        return new OfferDTO(
                rs.getLong("offer_id"),
                rs.getLong("drive_id"),
                rs.getLong("request_id"),
                OfferStatus.valueOf(rs.getString("status"))
        );
    }

    /**
     * Maps the current row of a ResultSet to a {@link RequestDTO}.
     */
    public static RequestDTO mapRequest(ResultSet rs) throws SQLException {
        return new RequestDTO(
                rs.getLong("request_id"),
                rs.getLong("requester_id"),
                rs.getString("start_location"),
                rs.getString("destination_location"),
                rs.getInt("price"),
                rs.getInt("baggage"),
                rs.getString("description"),
                rs.getTimestamp("desired_timestamp").toInstant().atOffset(ZoneOffset.UTC)
        );
    }

    /**
     * Maps the current row of a ResultSet to an {@link AbstractRideDTO}.
     */
    public static AbstractRideDTO mapAbstractRide(ResultSet rs) throws SQLException {
        return new AbstractRideDTO(
                rs.getLong("driver_id"),
                rs.getLong("drive_id"),
                TransportationType.valueOf(rs.getString("transportation_type")),
                rs.getDouble("price_per_person"),
                rs.getInt("max_seats"),
                rs.getInt("max_luggage_pieces"),
                rs.getString("description"),
                null // Stops are loaded separately.
        );
    }

    /**
     * Maps the current row of a ResultSet to a concrete {@link RideDTO}.
     */
    public static RideDTO mapRide(ResultSet rs) throws SQLException {
        return new RideDTO(
                rs.getLong("driver_id"),
                rs.getLong("drive_id"),
                TransportationType.valueOf(rs.getString("transportation_type")),
                rs.getDouble("price_per_person"),
                rs.getInt("max_seats"),
                rs.getInt("max_luggage_pieces"),
                rs.getString("description"),
                null, // Stops are loaded separately.
                rs.getDate("drive_date").toLocalDate(),
                rs.getDate("publication_date").toLocalDate(),
                RideStatus.valueOf(rs.getString("status"))
        );
    }

    /**
     * Maps the current row of a ResultSet to a recurring ride {@link CycleDTO}.
     */
    public static CycleDTO mapRecurringRide(ResultSet rs) throws SQLException {
        return new CycleDTO(
                rs.getLong("driver_id"),
                rs.getLong("drive_id"),
                TransportationType.valueOf(rs.getString("transportation_type")),
                rs.getDouble("price_per_person"),
                rs.getInt("max_seats"),
                rs.getInt("max_luggage_pieces"),
                rs.getString("description"),
                null, // Stops are loaded separately.
                CycleType.valueOf(rs.getString("rule")),
                rs.getDate("start_date").toLocalDate(),
                rs.getDate("end_date").toLocalDate(),
                null // Exceptions are not implemented in this mapping.
        );
    }

    /**
     * Maps the current row of a ResultSet to a {@link StopDTO}.
     */
    public static StopDTO mapStop(ResultSet rs) throws SQLException {
        return new StopDTO(
                rs.getLong("waypoint_id"),
                rs.getLong("drive_id"),
                StopType.valueOf(rs.getString("type")),
                rs.getString("address"),
                rs.getInt("number_of_people"),
                rs.getInt("number_of_luggage_pieces"),
                rs.getTimestamp("timestamp").toInstant().atOffset(ZoneOffset.UTC),
                rs.getInt("sequence")
        );
    }
}
