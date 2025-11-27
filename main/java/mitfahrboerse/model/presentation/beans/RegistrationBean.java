package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.RegistrationDTO;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.global.dto.UserRole;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mitfahrboerse.model.business.service.AuthenticationService;
import java.io.Serializable;

/**
 * Backing bean for the register.xhtml page.
 * Inherits from SafeActionBean.
 * To manage the creation of new users, either by an
 * anonymous user (register) or by an admin (createAdminUser).
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class RegistrationBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * DTO to hold all form data from register.xhtml.
     * Bound to all 'it_' and 'is_' fields (e.g., it_firstName, is_password).
     */
    private RegistrationDTO regDto;

    /**
     * Initializes the bean.
     * Creates a new, empty RegistrationDTO to bind to the JSF form.
     */
    @PostConstruct
    public void init() {
    	this.regDto = new RegistrationDTO();
    }

    /**
     * Action method for 'cb_register' button (anonymous user).
     * To register a new user, which triggers a verification email.
     * Uses safeAction for error handling.
     *
     * @return The navigation outcome (e.g., redirect to login or verification page).
     */
    public String register() {
        // Use safeAction (from SafeActionBean) for error handling
        UserDTO newUser = safeAction(() -> {
            // Password confirmation check (simple example)
            return AuthenticationService.register(regDto);
        }, "Registrierung erfolgreich! Bitte prüfen Sie Ihre E-Mails.");

        if (newUser != null) {
            // Success
            return "/view/anonymous/login.xhtml?faces-redirect=true&regSuccess=true";
        } else {
            // Failure, stay on page
            return null;
        }
    }

    /**
     * Action method for 'cb_createAdminUser' button (admin only).
     * To allow an admin to create a new user (or admin) account
     * that is instantly verified.
     *
     * @return The navigation outcome (e.g., redirect to user management).
     */
    public String createAdminUser() {
        // Use safeAction (from SafeActionBean) for error handling
        UserDTO newUser = safeAction(() -> {
            UserDTO user = AuthenticationService.register(regDto);
            return user;
        }, "Benutzer erfolgreich erstellt.");

        if (newUser != null) {
            // Redirect to user management page
            return "/view/admin/user_management.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

    // --- GETTER & SETTER ---

    /**
     * Provides the RegistrationDTO to the Facelet for data binding.
     * Bound to all form fields (e.g., value="#{registrationBean.regDto.firstName}").
     * @return The RegistrationDTO.
     */
    public RegistrationDTO getRegDto() {
        return regDto;
    }

    /**
     * Sets the RegistrationDTO instance.
     * @param regDto The RegistrationDTO.
     */
    public void setRegDto(RegistrationDTO regDto) {
        this.regDto = regDto;
    }
}