package mitfahrboerse.model.business.service;

import mitfahrboerse.global.dto.RatingDTO;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.model.business.exception.BusinessException;

/**
 * Handles business logic for rating rides and calculating user averages.
 * This service's purpose is to manage the reputation system of the application,
 * allowing passengers to rate drivers after a completed ride.
 * 
 * @author Anton Hollube
 */
public class RatingService {

    private RatingService() {
    }

    /**
     * Submits a rating for a completed booking, to allow a passenger to give a star rating
     * for a ride, which finalizes the booking and updates the driver's
     * average rating.
     *
     * @param ratingDto DTO containing the booking ID (via BookingDTO) and the star rating.
     * @return The newly created and saved RatingDTO.
     * @throws BusinessException If the ride is not yet completed or already rated (OperationNotAllowedException).
     */
    public static RatingDTO rateRide(RatingDTO ratingDto) throws BusinessException {
        return null;
    }

    /**
     * Calculates and retrieves the average star rating for a specific user.
     * This method is used to compute and display a user's public reputation
     * (e.g., on their profile or on ride detail pages).
     *
     * @param userDto DTO containing the userId of the user to check.
     * @return The average rating as a double.
     * @throws BusinessException If the user is not found (ValidationException) or a data access error occurs.
     */
    public static double getAverageRating(UserDTO userDto) throws BusinessException {
        return 0.0;
    }
}