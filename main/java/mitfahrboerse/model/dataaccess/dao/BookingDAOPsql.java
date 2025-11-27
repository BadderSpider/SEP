package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.BookingDTO;
import mitfahrboerse.global.dto.BookingStatus;
import mitfahrboerse.global.dto.RatingDTO;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;
import mitfahrboerse.model.dataaccess.exceptions.NoResultException;
import mitfahrboerse.model.dataaccess.util.DAOMapper;

import java.sql.Connection;
import java.util.Optional;

/**
 * The PostgreSQL-specific implementation of the {@link BookingDAO} interface.
 * This class executes SQL commands for booking and rating entities against a PostgreSQL database.
 * Instances are created by a transaction to ensure all operations are part of a transactional context.
 *
 * @author Jonathan Schilcher
 */
public final class BookingDAOPsql extends AbstractDAO implements BookingDAO {

    /**
     * Constructs a new BookingDAOPsql with a database connection provided by a transaction.
     * This ensures that all database operations within this DAO instance are part of the same transaction.
     *
     * @param connection The database connection from the current transaction context.
     */
    public BookingDAOPsql(Connection connection) {
        super(connection);
    }


    @Override
    public long createBooking(BookingDTO booking) throws DataAccessException {
        String sql = "INSERT INTO t_booking (concrete_drive_id, passenger_id, entry_waypoint_id, exit_waypoint_id, booked_luggage_pieces, booked_seats, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return create(sql,
                booking.getRideId(),
                booking.getPassengerId(),
                booking.getStartId(),
                booking.getStopId(),
                booking.getBookedBaggage(),
                booking.getBookedSeats(),
                booking.getStatus()
        ).orElse(-1L);
    }

    @Override
    public void updateBookingStatus(long bookingId, BookingStatus status) throws DataAccessException {
        String sql = "UPDATE t_booking SET status = ? WHERE booking_id = ?";
        execute(sql, status, bookingId);
    }

    @Override
    public void deleteBooking(long bookingId) throws DataAccessException {
        String sql = "DELETE FROM t_booking WHERE booking_id = ?";
        execute(sql, bookingId);
    }

    @Override
    public void findBookingById(BookingDTO bookingDTO) throws DataAccessException {
        String sql = "SELECT * FROM t_booking WHERE booking_id = ?";
        Optional<BookingDTO> optBooking = findOne(sql, DAOMapper::mapBooking, bookingDTO.getBookingId());
        if (optBooking.isPresent()) {
            BookingDTO booking = optBooking.get();
            bookingDTO.setBookingId(booking.getBookingId());
            bookingDTO.setRideId(booking.getRideId());
            bookingDTO.setPassengerId(booking.getPassengerId());
            bookingDTO.setStartId(booking.getStartId());
            bookingDTO.setStopId(booking.getStopId());
            bookingDTO.setStatus(booking.getStatus());
            bookingDTO.setBookedSeats(booking.getBookedSeats());
            bookingDTO.setBookedBaggage(booking.getBookedBaggage());
        } else {
            throw new NoResultException("Booking with id " + bookingDTO.getBookingId() + " not found");
        }
    }

    @Override
    public void submitRating(RatingDTO rating) throws DataAccessException {
        String sql = "UPDATE t_user SET number_of_ratings = number_of_ratings + 1, " +
                "average_rating = ((average_rating * number_of_ratings) + ?) / (number_of_ratings + 1) " +
                "WHERE user_id = ?";
        execute(sql, rating.getStars(), rating.getUserId());
    }


    @Override
    public boolean hasUserAlreadyBooked(long passengerId, long concreteRideId) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM t_booking WHERE passenger_id = ? AND concrete_drive_id = ?";
        long count = findScalar(sql, Long.class, passengerId, concreteRideId).orElse(0L);
        return count > 0;
    }
}