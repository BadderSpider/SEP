package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.PasswordResetDTO;
import mitfahrboerse.global.dto.VerificationTokenDTO;
import mitfahrboerse.model.business.service.PasswordService;
import mitfahrboerse.model.business.service.TokenService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Backing bean for the change_password.xhtml page.
 * Inherits from SafeActionBean.
 * To manage the password change process initiated from a token link.
 * It validates the token on page load (via f:viewAction) and saves the new password.
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class ChangePasswordBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * DTO to hold the token from the URL (via f:viewParam 'vp_token')
     * and the new password from the form (via 'is_newPassword').
     */
    private PasswordResetDTO passwordResetDto;
    
    /**
     * Bound to the 'is_passwordRepeat' field for validation.
     */
    private String passwordRepeat;
    
    /**
     * Used to show/hide the form (true if token is valid).
     */
    private boolean isTokenValid = false;

    /**
     * Initializes the bean.
     */
    @PostConstruct
    public void init() {

    }

    /**
     * ViewAction method, called by f:viewAction.
     * To validate the token provided in the URL *before* the page is rendered.
     * It uses safeAction to catch InvalidTokenException.
     */
    public void validateToken() {
    }

    /**
     * Action method for 'cb_savePassword' button.
     * To save the new password using the (already validated) token data.
     *
     * @return The navigation outcome (e.g., redirect to login).
     */
    public String saveNewPassword() {
		return passwordRepeat;
    
    }

    // --- GETTER & SETTER ---

    /**
     * Provides the DTO for binding to f:viewParam 'vp_token'
     * and form field 'is_newPassword'.
     * @return The PasswordResetDTO.
     */
    public PasswordResetDTO getPasswordResetDto() {
        return this.passwordResetDto;
    }

    /**
     * Sets the DTO (used by JSF).
     * @param passwordResetDto The DTO.
     */
    public void setPasswordResetDto(PasswordResetDTO passwordResetDto) {
        this.passwordResetDto = passwordResetDto;
    }

    /**
     * Provides the password confirmation field value.
     * Bound to `is_passwordRepeat`.
     * @return The repeated password string.
     */
    public String getPasswordRepeat() {
        return this.passwordRepeat;
    }

    /**
     * Sets the password confirmation field value.
     * @param passwordRepeat The repeated password string.
     */
    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    /**
     * Controls rendering of the password form.
     * @return true if the token was validated successfully, false otherwise.
     */
    public boolean isTokenValid() {
        return isTokenValid;
    }

    /**
     * Sets the token validation status.
     * @param isTokenValid the new status.
     */
    public void setTokenValid(boolean isTokenValid) {
        this.isTokenValid = isTokenValid;
    }
}