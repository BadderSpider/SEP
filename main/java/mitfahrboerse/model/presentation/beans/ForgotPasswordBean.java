package mitfahrboerse.model.presentation.beans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.model.business.service.PasswordService;
import java.io.Serializable;

/**
 * Backing bean for the forgot_password.xhtml page.
 * Inherits from SafeActionBean.
 * To manage the process of sending a password reset email to a user.
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class ForgotPasswordBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * DTO to hold the email from the 'it_email' field.
     * This is bound to userDto.email.
     */
    private UserDTO userDto;
	
    /**
     * Initializes the bean.
     * Creates a new, empty UserDTO instance to bind the email field.
     */
    @PostConstruct
    public void init() {
    //	this.userDto = new UserDTO();
    }

    /**
     * Action method for 'cb_sendEmail' button.
     * To initiate the password reset process for the given email
     * by calling the PasswordService.
     *
     * @return The navigation outcome (e.g., redirect to login or a confirmation page).
     */
    public String sendPasswordResetEmail() {
    	// Use safeAction for error handling (e.g., if email doesn't exist)
        boolean success = safeAction(() -> {
            PasswordService.requestPasswordReset(userDto);
        }); // Keine Erfolgsmeldung, um zu verbergen, ob die E-Mail existiert

        if (success) {
            // Aus Sicherheitsgründen zeigen wir immer eine "Erfolgs"-Seite an,
            // (auch wenn die E-Mail nicht existiert), um E-Mail-Fishing zu verhindern.
            // addGlobalInfoMessage("Anweisung gesendet", "Wenn diese E-Mail-Adresse registriert ist, erhalten Sie in Kürze Anweisungen zum Zurücksetzen Ihres Passworts.");
            return "/view/anonymous/login.xhtml?faces-redirect=true&reset=sent";
        } else {
            // Ein interner Fehler (DB, Mail-Server) ist aufgetreten.
            // safeAction hat die Fehlermeldung bereits hinzugefügt.
            return null;
        }
    }


    // --- GETTER & SETTER ---

    /**
     * Provides the UserDTO to the Facelet for data binding.
     * Bound to `it_email` (value="#{forgotPasswordBean.userDto.email}").
     * @return The UserDTO.
     */
    public UserDTO getUserDto() {
        return userDto;
    }

    /**
     * Sets the UserDTO instance (used by JSF).
     * @param userDto The UserDTO.
     */
    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }
}