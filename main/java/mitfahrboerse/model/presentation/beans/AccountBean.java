package mitfahrboerse.model.presentation.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mitfahrboerse.model.business.service.AuthenticationService;
import mitfahrboerse.model.presentation.util.UserSessionBean;

import java.io.Serializable;

/**
 * Backing bean for account-related navigation (in the user dropdown menu 'p_accountMenu').
 * Inherits from SafeActionBean.
 * This bean provides simple navigation actions for the user's
 * "My Account" dropdown menu.
 *
 * @author Anton Hollube
 */
@SessionScoped
@Named
public class AccountBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    UserSessionBean userSessionBean;

    /**
     * Initializes the bean.
     */
    @PostConstruct
    public void init() {
    }

    /**
     * Action method for 'l_toMyRequests'.
     * Navigates the user to their "My Requests" page.
     *
     * @return The navigation outcome string for my_requests.xhtml.
     */
    public String mapsToMyRequests() {
        return "/view/registered/my_requests.xhtml?faces-redirect=true";
    }

    /**
     * Action method for 'cl_logout'.
     * Logs the user out, invalidates the session, and redirects to the home page.
     *
     * @return The navigation outcome string (rides.xhtml).
     */
    public String logout() {
        AuthenticationService.logout();
        userSessionBean.invalidateSession();
        return "/view/anonymous/rides.xhtml?faces-redirect=true";
    }

    /**
     * Action method for 'l_toProfile'.
     * Navigates the user to their own profile page.
     *
     * @return The navigation outcome string for profile.xhtml.
     */
    public String mapsToProfile() {
        long userId = userSessionBean.getUserId();
        return "/view/registered/profile.xhtml?faces-redirect=true&userId=" + userId;
    }

    /**
     * Action method for 'l_toMyRides'.
     * Navigates the user to their "My Rides" page.
     *
     * @return The navigation outcome string for my_rides.xhtml.
     */
    public String mapsToMyRides() {
        return "/view/registered/my_rides.xhtml?faces-redirect=true";
    }

    /**
     * Action method for 'l_toMyBookings'.
     * Navigates the user to their "My Bookings" page.
     *
     * @return The navigation outcome string for my_bookings.xhtml.
     */
    public String mapsToMyBookings() {
        return "/view/registered/my_bookings.xhtml?faces-redirect=true";
    }
}