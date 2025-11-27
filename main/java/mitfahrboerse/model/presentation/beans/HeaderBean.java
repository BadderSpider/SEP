package mitfahrboerse.model.presentation.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mitfahrboerse.model.business.service.AuthenticationService;
import mitfahrboerse.model.presentation.util.UserSessionBean;
import java.io.Serializable;

/**
 * Backing bean for the header.xhtml template.
 * Inherits from SafeActionBean.
 * To manage the state and actions of the main application header,
 * such as toggling the help sidebar and providing a logout action.
 *
 * @author Matthias Schmitt
 */
@SessionScoped
@Named
public class HeaderBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String imageURL = "/imagehandler?id=logo"; // If TemplateBean fails

    private boolean helpVisible = false;

    @Inject
    private UserSessionBean userSessionBean;

    @PostConstruct
    public void init() {
    }

    /**
     * Action method for 'cb_toggleHelp'.
     * To toggle the visibility of the help sidebar.
     */
    public void toggleHelp() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dies ist eine Testnachricht.", null));
   //toDo del->
        this.helpVisible = !this.helpVisible;
    }

    /**
     * Getter for the help visibility state.
     * Used in the view to render the sidebar conditionally.
     * @return true if the help sidebar should be shown.
     */
    public boolean isHelpVisible() {
        return helpVisible;
    }

    /**
     * Action method for 'cl_logout' (im Konto-Menü).
     * Logs the current user out of the system.
     * @return The navigation outcome (e.g., redirect to login page).
     */
    public String logout() {
        AuthenticationService.logout();
        userSessionBean.invalidateSession();
        return "/view/pages/anonymous/rides.xhtml?faces-redirect=true";
    }

    /**
     * Provides the URL for the logo ('g_logo').
     * @return The URL string.
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Sets the logo URL.
     * @param imageURL The URL string.
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}