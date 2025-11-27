package mitfahrboerse.model.business.service;

import mitfahrboerse.global.dto.BookingDTO;
import mitfahrboerse.global.dto.RideDTO; 
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.model.business.exception.BusinessException;

import java.util.List;

/**
 * Handles all business logic for booking, canceling, accepting, or rejecting rides.
 * This service manages the complete lifecycle of a booking, acting as the central
 * controller for all state changes related to a passenger's request to join a ride.
 *
 * @author Anton Hollube
 */
public class BookingService {

    private BookingService() {
    }

    /**
     * Creates a new booking request from a user for a specific ride.
     * This is the initial action a passenger takes, creating the booking in a 'REQUESTED'
     * state, pending approval from the driver.
     *
     * @param rideDto The DTO containing the ID of the ride to be booked.
     * @param userDto The DTO containing the ID of the user requesting the booking.
     * @return The newly created BookingDTO with status REQUESTED.
     * @throws BusinessException If the ride is full (RideFullException) or a data access error occurs.
     */
    public static BookingDTO requestBooking(RideDTO rideDto, UserDTO userDto) throws BusinessException {
        return null;
    }

    /**
     * Accepts a pending booking request (Driver action).
     * This is a driver-specific action that confirms the passenger's seat and
     * changes the booking status to 'ACCEPTED'.
     *
     * @param bookingDto DTO containing the bookingId to accept.
     * @return The updated BookingDTO with status ACCEPTED.
     * @throws BusinessException If the booking is not in a state to be accepted (OperationNotAllowedException) or a data access error occurs.
     */
    public static BookingDTO acceptBooking(BookingDTO bookingDto) throws BusinessException {
        return null;
    }

    /**
     * Rejects a pending booking request (Driver action).
     * This is a driver-specific action to deny a passenger's request, changing
     * the booking status to 'REJECTED'.
     *
     * @param bookingDto DTO containing the bookingId to reject.
     * @return The updated BookingDTO with status REJECTED.
     * @throws BusinessException If the booking is not in a state to be rejected (OperationNotAllowedException) or a data access error occurs.
     */
    public static BookingDTO rejectBooking(BookingDTO bookingDto) throws BusinessException {
        return null;
    }

    /**
     * Cancels an existing booking, either by Passenger or Driver.
     * This action is used to invalidate a booking, either by a passenger retracting
     * their request or a driver removing an accepted passenger. It checks business rules
     * like cancellation deadlines.
     *
     * @param bookingDto DTO containing the bookingId to cancel.
     * @throws BusinessException If the cancellation deadline has passed (OperationNotAllowedException) or a data access error occurs.
     */
    public static void cancelBooking(BookingDTO bookingDto) throws BusinessException {
    }

    /**
     * Retrieves all bookings associated with a specific ride.
     * This method is used by the driver (e.g., in 'My Rides') to see all
     * passengers (both pending and accepted) for their ride.
     *
     * @param rideDto DTO containing the rideId.
     * @return A Set of BookingDTOs for that ride.
     * @throws BusinessException If a data access error occurs.
     */
    public static List<BookingDTO> getBookingsForRide(RideDTO rideDto) throws BusinessException {
        return null;
    }

    /**
     * Retrieves all bookings made by a specific user.
     * This method is used by the passenger to populate their 'My Bookings' page,
     * showing all rides they have requested or been accepted into.
     *
     * @param userDto DTO containing the userId.
     * @return A Set of BookingDTOs for that user.
     * @throws BusinessException If a data access error occurs.
     */
    public static List<BookingDTO> getBookingsForUser(UserDTO userDto) throws BusinessException {
        return null;
    }
}