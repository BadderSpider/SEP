package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.VerificationTokenDTO;
import mitfahrboerse.model.business.service.ProfileService;
import mitfahrboerse.model.business.service.TokenService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Backing bean for the verification.xhtml page.
 * Inherits from SafeActionBean.
 * To handle the token validation logic triggered by a user
 * clicking the verification link in their registration email.
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class VerificationBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * DTO to hold the token from the URL (via f:viewParam 'vp_token').
     */
    private VerificationTokenDTO verificationTokenDTO;
    
    /**
     * Controls rendering of the "success" message and login link.
     */
    private boolean success = false;
    
    /**
     * Holds the message (success or error) to be displayed.
     * Bound to 'o_verificationMessage'.
     */
    private String verificationMessage;

    /**
     * Initializes the bean.
     */
    @PostConstruct
    public void init() {

    }

    /**
     * ViewAction method, called by f:viewAction.
     * To validate the email verification token *before* the page
     * is rendered. It activates the user's account if the token is valid.
     */
    public void validateToken() {
        
    }

    // --- GETTER & SETTER ---

    /**
     * Provides the DTO for binding to the 'vp_token' view parameter.
     * @return The VerificationTokenDTO.
     */
    public VerificationTokenDTO getVerificationTokenDTO() {
        return this.verificationTokenDTO;
    }

    /**
     * Sets the DTO (used by JSF).
     * @param verificationTokenDTO The VerificationTokenDTO.
     */
    public void setVerificationTokenDTO(VerificationTokenDTO verificationTokenDTO) {
        this.verificationTokenDTO = verificationTokenDTO;
    }

    /**
     * Controls rendering of the 'l_toLogin' link.
     * @return true if validation succeeded, false otherwise.
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * Sets the success status.
     * @param success true if successful.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Provides the message (success or error) to 'o_verificationMessage'.
     * @return The status message.
     */
    public String getVerificationMessage() {
        return verificationMessage;
    }

    /**
     * Sets the message (used internally).
     * @param verificationMessage The status message.
     */
    public void setVerificationMessage(String verificationMessage) {
        this.verificationMessage = verificationMessage;
    }
}