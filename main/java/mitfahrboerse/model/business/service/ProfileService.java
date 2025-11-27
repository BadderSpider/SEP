package mitfahrboerse.model.business.service;

import mitfahrboerse.global.dto.PasswordResetDTO;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.global.dto.*;
import mitfahrboerse.model.business.exception.BusinessException;

import java.util.List;


/**
 * Handles all business logic for managing user profiles.
 * This service is the central point for creating, retrieving, updating,
 * and deleting user account data, including sensitive information.
 *
 * @author Anton Hollube
 */
public class ProfileService {

	private ProfileService() {
    }

    /**
     * Retrieves the full profile data for a specific user,t o load all non-critical user data (name, address, etc.)
     * needed to display a user's profile page.
     *
     * @param userDto DTO containing the userId of the profile to retrieve.
     * @return The fully populated UserDTO.
     * @throws BusinessException If the user is not found (ValidationException) or a data access error occurs.
     */
    public static UserDTO getProfile(UserDTO userDto) throws BusinessException {
        return null;
    }

    /**
     * Retrieves the full profile data for a specific user by their email, to find a user during processes where only the email is
     * known, such as registration validation or password reset.
     *
     * @param email The email of the profile to retrieve.
     * @return The fully populated UserDTO.
     * @throws BusinessException If the user is not found (NoResultException) or a data access error occurs.
     */
    public static UserDTO getProfileByEmail(UserDTO userDto) throws BusinessException {
    	String email = userDto.getEmail();
        return null;
    }
    
    /**
     * Updates a user's profile data, to persist changes a user makes to their
     * own profile information (e.g., address, phone number).
     *
     * @param userDto The DTO containing the updated user data (e.g., address, phone).
     * @return The persisted UserDTO with updated fields.
     * @throws BusinessException If validation fails (ValidationException) or a data access error occurs.
     */
    public static UserDTO updateProfile(UserDTO userDto) throws BusinessException {
        return null;
    }

    /**
     * Changes the password for an *authenticated* user who knows their old password.
     * Its purpose is to allow a logged-in user to change their password from
     * their profile settings page, requiring verification of the old password.
     *
     * @param changeDto DTO containing the userId, the oldPassword, and the newPassword.
     * @throws BusinessException If the old password is incorrect (ValidationException) or a data access error occurs.
     */
    public static void changePassword(PasswordResetDTO changeDto) throws BusinessException {
    }

    /**
     * Deletes a user's own account.
     * This method is called by a user to permanently delete their own profile
     * and associated data from the system.
     *
     * @param userDto DTO containing the userId of the account to delete.
     * @throws BusinessException If the account cannot be deleted (OperationNotAllowedException) or a data access error occurs.
     */
    public static void deleteAccount(UserDTO userDto) throws BusinessException {
    }


    /**
     * Retrieves the profile picture for a specific user.
     * To supply the ImageHandler servlet with the
     * binary data for a user's profile picture.
     *
     * @param userId The ID of the user whose picture is requested.
     * @return The ImageDTO containing the binary data and file type.
     * @throws BusinessException If the picture or user is not found.
     */
    public static ImageDTO getProfilePicture(UserDTO user) throws BusinessException {
    	long userId = user.getUserId();
        // Must be implemented to call UserDAO.findProfilePictureByUserId
        return null;
    }

    /**
     * Updates or sets a user's profile picture.
     * To save a new image uploaded by the user
     * in their profile settings.
     *
     * @param userId The ID of the user.
     * @param picture The ImageDTO containing the new picture's binary data.
     * @throws BusinessException If the save operation fails.
     */
    public static void updateProfilePicture(ImageDTO picture) throws BusinessException {
    	long userId = picture.getUserId();
         // Must be implemented to call UserDAO.saveProfilePicture
    }

    /**
     * Deletes a user's profile picture.
     *
     * @param userId The ID of the user whose picture should be deleted.
     * @throws BusinessException If the delete operation fails.
     */
    public static void deleteProfilePicture(UserDTO user) throws BusinessException {
    	long userId = user.getUserId();
        // Must be implemented to call UserDAO.deleteProfilePicture
    }

    
    /**
     * Retrieves a list of all users in the system (Admin action).
     * To populate the User Management dashboard
     * for administrators.
     * 
     * @return A Set of all UserDTOs.
     * @throws BusinessException If a data access error occurs.
     */
    public static List<UserDTO> adminGetAllUsers() throws BusinessException {
        return null;
    }

    /**
     * Updates the role of a specific user (Admin action).
     * This method allows an admin to promote a user to an admin or
     * demote them.
     *
     * @param userDto DTO containing the userId and the new UserRole.
     * @return The updated UserDTO with the new role.
     * @throws BusinessException If the role cannot be updated (OperationNotAllowedException) or a data access error occurs.
     */
    public static UserDTO adminUpdateUserRole(UserDTO userDto) throws BusinessException {
        return null;
    }

    /**
     * Deletes a specific user account (Admin action).
     * This provides administrators with the ability to remove any user
     * from the system.
     *
     * @param userDto DTO containing the userId of the account to delete.
     * @throws BusinessException If the user cannot be deleted (OperationNotAllowedException) or a data access error occurs.
     */
    public static void adminDeleteUser(UserDTO userDto) throws BusinessException {
    }
}
