package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.global.dto.UserRole;
import mitfahrboerse.model.business.service.PasswordService;
import mitfahrboerse.model.business.service.ProfileService;
import mitfahrboerse.model.presentation.util.UserSessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Backing bean for the profile.xhtml page.
 * Inherits from SafeActionBean.
 * To manage viewing and editing of a user's profile.
 * Loading Pattern:
 * 1. The 'userId' is passed via f:viewParam.
 * 2. f:viewAction calls loadUser() to fetch the UserDTO.
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class ProfileBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The ID of the user to load.
     * Set by f:viewParam name="userId". If 0 or not present,
     * the bean will load the profile of the currently logged-in user.
     */
    private long userId;
    
    /**
     * The DTO holding all data for the user being edited.
     * Loaded by the loadUser() viewAction.
     * Bound to all form fields (e.g., 'it_firstName').
     */
    private UserDTO userDTO;

    // --- Form Fields ---
    /**
     * Holds the uploaded file part for the new profile picture.
     * Bound to 'if_uploadPicture'.
     */
    private Part uploadedImage;
    
    /**
     * Holds the new password entered by an admin.
     * Bound to 'is_adminNewPass'.
     */
    private String isAdminNewPassword;
    
    /**
     * Holds the status of the 'Administrator' role checkbox (admin-only).
     * Bound to 'sbc_setAdmin'.
     */
    private boolean isAdminRole;

    @Inject
    private UserSessionBean userSessionBean;

    /**
     * Initializes the bean.
     * Creates an empty UserDTO to prevent NullPointerExceptions.
     */
    @PostConstruct
    public void init() {
    }

    /**
     * ViewAction method (called by f:viewAction).
     * To load the user profile based on the 'userId' view parameter.
     * If no userId is provided, it loads the profile of the logged-in user.
     */
    public void loadUser() {
    }

    /**
     * Action method for 'cl_reqPassChange'.
     * To request a password change email for the *currently logged-in* user.
     * @return The navigation outcome.
     */
    public String requestPasswordChange() {
		return null;
    }

    /**
     * Action method for 'cb_saveProfile'.
     * To save all changes made to the profile.
     * @return The navigation outcome (refreshes page).
     */
    public String save() {
        return null; 
    }

    /**
     * Action method for 'cb_deleteAccount'.
     * To delete the user's own account.
     * @return The navigation outcome (redirects to logout/home).
     */
    public String deleteAccount() {
        return null;
    }

    // --- GETTER & SETTER ---

    /**
     * Provides the User ID from the view parameter.
     * @return The user ID.
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the user ID from the f:viewParam.
     * @param userId The user ID.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Provides the loaded UserDTO for form bindings.
     * @return The UserDTO.
     */
    public UserDTO getUserDTO() {
        return userDTO;
    }

    /**
     * Sets the UserDTO.
     * @param userDTO The UserDTO.
     */
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    /**
     * Provides the Part for 'if_uploadPicture'.
     * @return The uploaded file.
     */
    public Part getUploadedImage() {
        return uploadedImage;
    }

    /**
     * Sets the Part from 'if_uploadPicture'.
     * @param uploadedImage The uploaded file.
     */
    public void setUploadedImage(Part uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    /**
     * Provides the value for 'is_adminNewPass'.
     * @return The new password.
     */
    public String getIsAdminNewPassword() {
        return isAdminNewPassword;
    }

    /**
     * Sets the value from 'is_adminNewPass'.
     * @param isAdminNewPassword The new password.
     */
    public void setIsAdminNewPassword(String isAdminNewPassword) {
        this.isAdminNewPassword = isAdminNewPassword;
    }

    /**
     * Provides the value for 'sbc_setAdmin'.
     * @return true if the admin role is selected.
     */
    public boolean getIsAdminRole() {
        return isAdminRole;
    }

    /**
     * Sets the value from 'sbc_setAdmin'.
     * @param isAdminRole true if the admin role is selected.
     */
    public void setIsAdminRole(boolean isAdminRole) {
        this.isAdminRole = isAdminRole;
    }
    
    /**
     * Helper to get all UserRoles for a dropdown (if needed).
     * @return List of all UserRole enums.
     */
    public List<UserRole> getUserRoles() {
        return Arrays.asList(UserRole.values());
    }
}