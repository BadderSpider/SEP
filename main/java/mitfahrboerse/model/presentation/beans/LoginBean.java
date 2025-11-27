package mitfahrboerse.model.presentation.beans;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import mitfahrboerse.global.dto.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import mitfahrboerse.model.business.service.AuthenticationService;
import mitfahrboerse.model.presentation.util.UserSessionBean;
import java.io.Serializable;

/**
 *
 * @author René Schmaderer
 */
@RequestScoped
@Named
public class LoginBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private LoginDTO loginDTO;

    @Inject
    private HttpServletRequest request;

    @Inject
    private UserSessionBean userSessionBean;

    /**
     * Initializes the bean with an empty LoginDTO.
     */

    @PostConstruct
    public void init() {
    	this.loginDTO = new LoginDTO();
    }

    /**
     * Action method for the 'cb_login' button.
     * To attempt logging the user in using the credentials in the LoginDTO.
     * It uses safeAction to catch and display BusinessExceptions (e.g., "Invalid credentials").
     *
     * @return The navigation outcome string (redirects to rides overview on success).
     */
    public String login() {

        UserDTO user = safeAction(() -> {
            return AuthenticationService.login(loginDTO);
        }, "Login erfolgreich!");

        if (user != null) {
            request.changeSessionId();
            userSessionBean.setUserDTO(user);

            // 3. Redirect to main page
            return "/rides.xhtml?faces-redirect=true";
        } else {
            return null; 
        }
    }



    public LoginDTO getLoginDTO() {
        return loginDTO;
    }


}