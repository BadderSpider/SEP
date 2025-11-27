package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.*;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;

import java.util.List;

/**
 * Manages all data access operations for User entities, including profile data,
 * security credentials, and relationships to other entities like rides and bookings.
 *
 * @author Jonathan Schilcher
 */
public interface UserDAO {

    /**
     * Creates a new user with their profile information and password credentials.
     *
     * @param registrationDTO The DTO containing all necessary information for a new user.
     * @return The unique ID generated for the new user.
     * @throws DataAccessException if a database error occurs.
     */
    long createUser(RegistrationDTO registrationDTO) throws DataAccessException;

    /**
     * Updates an existing user's profile details.
     *
     * @param user The UserDTO with updated information.
     * @throws DataAccessException if a database error occurs.
     */
    void updateUser(UserDTO user) throws DataAccessException;

    /**
     * Deletes a user from the database.
     *
     * @param userId The ID of the user to delete.
     * @throws DataAccessException if a database error occurs.
     */
    void deleteUser(long userId) throws DataAccessException;

    /**
     * Finds a user by their unique ID and returns their full profile.
     *
     * @param userDTO The DTO with the id field filled out.
     * @throws DataAccessException if a database error occurs.
     */
    void findUserById(UserDTO userDTO) throws DataAccessException;

    /**
     * Finds a user by their email address.
     *
     * @param userDTO The DTO with the email field filled out.
     * @throws DataAccessException if a database error occurs.
     */
    void findUserByEmail(UserDTO userDTO) throws DataAccessException;

    /**
     * Saves or updates a user's profile picture.
     *
     * @param picture An ImageDTO containing the binary image data and mimetype.
     * @throws DataAccessException if a database error occurs.
     */
    void saveProfilePicture(ImageDTO picture) throws DataAccessException;

    /**
     * Retrieves a user's profile picture from the database.
     *
     * @param imageDTO The DTO with the id field filled out.
     * @throws DataAccessException if a database error occurs.
     */
    void findProfilePictureByUserId(ImageDTO imageDTO) throws DataAccessException;

    /**
     * Deletes a user's profile picture from the database.
     *
     * @param userId The ID of the user whose picture should be deleted.
     * @throws DataAccessException if a database error occurs.
     */
    void deleteProfilePicture(long userId) throws DataAccessException;

    // --- Security and Account Management ---

    /**
     * Retrieves essential password information (hash and salt) for a user by their email.
     * Used during the login process to verify a password.
     *
     * @param email The user's email.
     * @return A PasswordDTO, or null if the email is not found.
     * @throws DataAccessException if a database error occurs.
     */
    PasswordDTO findPasswordInfoByEmail(String email) throws DataAccessException;

    /**
     * Saves a new verification token to the database for password resets or email changes.
     *
     * @param token The VerificationTokenDTO containing the token details.
     * @throws DataAccessException if a database error occurs.
     */
    void saveVerificationToken(VerificationTokenDTO token) throws DataAccessException;

    /**
     * Finds the details of a pending password reset by its hashed token.
     *
     * @param hashedToken The securely hashed token string.
     * @return A PasswordResetDTO if the token is valid and not expired.
     * @throws DataAccessException if the token is not found or a database error occurs.
     */
    PasswordResetDTO getPasswordResetInfoByHashedToken(String hashedToken) throws DataAccessException;

    /**
     * Finds the details of a pending email address change by its hashed token.
     *
     * @param hashedToken The securely hashed token string.
     * @return A MailResetDTO if the token is valid and not expired.
     * @throws DataAccessException if the token is not found or a database error occurs.
     */
    MailResetDTO getMailResetInfoByHashedToken(String hashedToken) throws DataAccessException;

    /**
     * Updates a user's password hash and salt in the database.
     *
     * @param newPassword A PasswordDTO containing the new hash and salt, and the user's ID.
     * @throws DataAccessException if a database error occurs.
     */
    void resetPassword(PasswordDTO newPassword) throws DataAccessException;

    /**
     * Updates a user's email address in the database.
     *
     * @param userId The ID of the user.
     * @param newEmail The new email address.
     * @throws DataAccessException if a database error occurs.
     */
    void resetMail(long userId, String newEmail) throws DataAccessException;

    /**
     * Deletes a verification token from the database after it has been used.
     *
     * @param hashedToken The securely hashed token to be deleted.
     * @throws DataAccessException if a database error occurs.
     */
    void deleteVerificationTokenByHashedToken(String hashedToken) throws DataAccessException;

    // --- Relationship Finders ---

    /**
     * Retrieves all rides created by a specific driver.
     *
     * @param driverId The ID of the driver.
     * @return A list of their RideDTOs.
     * @throws DataAccessException if a database error occurs.
     */
    List<RideDTO> findRidesByDriverId(long driverId) throws DataAccessException;

    /**
     * Retrieves all bookings made by a specific passenger.
     *
     * @param passengerId The ID of the passenger.
     * @return A list of their BookingDTOs.
     * @throws DataAccessException if a database error occurs.
     */
    List<BookingDTO> findBookingsByPassengerId(long passengerId) throws DataAccessException;

    /**
     * Retrieves all ride requests created by a specific user.
     *
     * @param requesterId The ID of the user.
     * @return A list of their RequestDTOs.
     * @throws DataAccessException if a database error occurs.
     */
    List<RequestDTO> findRequestsByUserId(long requesterId) throws DataAccessException;

    /**
     * Retrieves all offers a driver has made in response to requests.
     *
     * @param driverId The ID of the driver.
     * @return A list of OfferDTOs made by that driver.
     * @throws DataAccessException if a database error occurs.
     */
    List<OfferDTO> findOffersByDriverId(long driverId) throws DataAccessException;

    /**
     * Retrieves all pending booking requests for all rides offered by a specific driver.
     *
     * @param driverId The ID of the driver.
     * @return A list of BookingDTOs with a 'REQUESTED' status.
     * @throws DataAccessException if a database error occurs.
     */
    List<BookingDTO> findPendingBookingsForDriver(long driverId) throws DataAccessException;

    /**
     * Retrieves all offers a driver has made that are still awaiting a response from the requester.
     *
     * @param driverId The ID of the driver.
     * @return A list of OfferDTOs with an 'OPEN' status.
     * @throws DataAccessException if a database error occurs.
     */
    List<OfferDTO> findPendingOffersByDriverId(long driverId) throws DataAccessException;

    // --- Search Methods ---

    /**
     * Retrieves a list of users based on flexible search criteria.
     *
     * @param criteria A DTO containing filters, pagination, and sorting information.
     * @return A list of matching UserDTOs.
     * @throws DataAccessException if a database error occurs.
     */
    List<UserDTO> findUsersByCriteria(SearchCriteriaDTO criteria) throws DataAccessException;

    /**
     * Counts the total number of users matching the given search criteria.
     *
     * @param criteria A DTO containing the filter information.
     * @return The total count of matching users.
     * @throws DataAccessException if a database error occurs.
     */
    long countUsersByCriteria(SearchCriteriaDTO criteria) throws DataAccessException;
}