package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.BookingDTO;
import mitfahrboerse.global.dto.BookingStatus;
import mitfahrboerse.global.dto.RatingDTO;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;

/**
 * Defines the data access contract for Booking and Rating entities.
 * This interface provides all CRUD (Create, Read, Update, Delete) operations for bookings
 * and the functionality for submitting ratings.
 *
 * @author Jonathan Schilcher
 */
public interface BookingDAO {

    /**
     * Persists a new booking for a ride in the database.
     *
     * @param booking The BookingDTO containing all booking details.
     * @return The unique ID generated for the new booking.
     * @throws DataAccessException if a database error occurs during creation.
     */
    long createBooking(BookingDTO booking) throws DataAccessException;

    /**
     * Updates the status of an existing booking (e.g., from REQUESTED to ACCEPTED).
     *
     * @param bookingId The ID of the booking to update.
     * @param status The new BookingStatus.
     * @throws DataAccessException if the booking does not exist or a database error occurs.
     */
    void updateBookingStatus(long bookingId, BookingStatus status) throws DataAccessException;

    /**
     * Permanently removes a booking from the database.
     *
     * @param bookingId The ID of the booking to delete.
     * @throws DataAccessException if a database error occurs.
     */
    void deleteBooking(long bookingId) throws DataAccessException;

    /**
     * Retrieves a booking by its unique ID.
     *
     * @param bookingDTO The DTO with the id field filled out.
     * @throws DataAccessException if a database error occurs.
     */
    void findBookingById(BookingDTO bookingDTO) throws DataAccessException;

    // --- Rating Methods ---

    /**
     * Submits a rating for a user (typically the driver of a completed ride).
     * This operation is designed to be atomic, updating both the rating count
     * and the calculated average rating for the user.
     *
     * @param rating The RatingDTO containing the star rating and the ID of the user being rated.
     * @throws DataAccessException if the user does not exist or a database error occurs.
     */
    void submitRating(RatingDTO rating) throws DataAccessException;

    /**
     * Checks if a specific user already has a booking for a specific ride.
     * This is useful to prevent duplicate bookings. The check is performed regardless of the booking's status.
     *
     * @param passengerId The ID of the passenger.
     * @param concreteRideId The ID of the concrete ride.
     * @return true if a booking already exists, false otherwise.
     * @throws DataAccessException if a database error occurs.
     */
    boolean hasUserAlreadyBooked(long passengerId, long concreteRideId) throws DataAccessException;
}