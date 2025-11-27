package mitfahrboerse.model.business.service;

import mitfahrboerse.global.dto.BookingDTO;
import mitfahrboerse.global.dto.RideDTO;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.model.business.exception.BusinessException;

/**
 * Handles complex business logic validations that require data access.
 * This service's purpose is to externalize complex business rules (e.g.,
 * checking deadlines, checking capacity) from the main services,
 * keeping them clean and focused on their primary task.
 *
 * @author Anton Hollube
 */
public class ValidationService {

    private ValidationService() {
    }

    /**
     * Checks if a specific ride can still be booked (e.g., has free seats).
     * To be called by `BookingService.requestBooking`
     * to prevent overbooking a ride by checking `(current_bookings &lt; max_seats)`.
     *
     * @param rideDto DTO containing the rideId to check.
     * @return true if booking is possible, false otherwise.
     * @throws BusinessException If a data access error occurs.
     */
    public static boolean canBookRide(RideDTO rideDto) throws BusinessException {
        return false;
    }

    /**
      * Checks if a specific booking can still be cancelled (e.g., deadline not passed).
     * To enforce the cancellation deadline rule. It is
     * called by `BookingService.cancelBooking` to check if
     * (now &lt; ride_start_time - cancellation_deadline_hours).
     *
     * @param bookingDto DTO containing the bookingId to check.
     * @return true if cancellation is allowed, false otherwise.
     * @throws BusinessException If a data access error occurs.
     */
    public static boolean canCancelBooking(BookingDTO bookingDto) throws BusinessException {
        return false;
    }

    /**
     * Checks if the deadline for editing a ride has expired.
     * To be called by `RideService.updateRide` to
     * prevent a driver from changing ride details (e.g., price) after a
     * certain deadline or once bookings exist.
     *
     * @param rideDto DTO containing the rideId to check.
     * @return true if the deadline is expired, false otherwise.
     * @throws BusinessException If a data access error occurs.
     */
    public static boolean isRideEditDeadlineExpired(RideDTO rideDto) throws BusinessException {
        return false;
    }

    /**
     * Checks if a user is generally allowed to book rides (e.g., not banned, email verified).
     * To be a central check in `BookingService.requestBooking`
     * to ensure the user's account is in good standing before allowing a booking.
     *
     * @param userDto DTO containing the userId to check.
     * @return true if the user is allowed to book, false otherwise.
     * @throws BusinessException If a data access error occurs.
     */
    public static boolean isUserAllowedToBook(UserDTO userDto) throws BusinessException {
        return false;
    }
}