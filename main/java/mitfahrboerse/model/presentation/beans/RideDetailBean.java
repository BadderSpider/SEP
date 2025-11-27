package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.RideDTO;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.model.business.service.BookingService;
import mitfahrboerse.model.business.service.ProfileService;
import mitfahrboerse.model.business.service.RideService;
import mitfahrboerse.model.presentation.util.UserSessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Backing bean for the ride_details.xhtml page.
 * Inherits from SafeActionBean.
 * To display detailed information about a single ride. It loads the
 * ride via a Converter and then lazy-loads associated data (like driver profile).
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class RideDetailBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The main RideDTO.
     * This object is loaded automatically by JSF via the f:viewParam
     * tag and the RideConverter, using the 'rideId' from the URL.
     */
    private RideDTO rideDTO;

    /**
     * The full UserDTO of the ride's driver.
     * This object is loaded by the 'loadDriverDetails' viewAction
     * to display the driver's name, rating, and contact info.
     */
    private UserDTO driver;

    @Inject
    private UserSessionBean userSessionBean;

    /**
     * Initializes the bean.
     * (Empty, as DTOs are loaded by JSF).
     */
    @PostConstruct
    public void init() {
        // DTOs werden über f:viewParam und f:viewAction geladen
    }

    /**
     * ViewAction method (called by f:viewAction *after* rideDTO is loaded by converter).
     * To load secondary data (the driver's full profile)
     * needed for the view, which is not included in the main DTO.
     */
    public void loadDriverDetails() {
    }

    /**
     * Action method for 'cb_requestBooking' button.
     * To create a new booking request for the currently viewed ride.
     */
    public String requestBooking() {
		return null;
    }

    /**
     * Action method for 'cb_deleteRide' button.
     * To delete the currently viewed ride (only for owner/admin).
     */
    public String deleteRide() {
		return null;
    }

    /**
     * Helper method for 'rendered' attributes.
     * Checks if the currently logged-in user is the owner of this ride.
     * @return true if the user is the owner, false otherwise.
     */
    public boolean isOwnRide() {
		return false;

    }

    // --- GETTER & SETTER ---

    /**
     * Provides the RideDTO (loaded by converter) to the Facelet.
     * Bound to: f:viewParam name="rideId" value="#{rideDetailBean.rideDTO}" converter="rideConverter"
     * @return The RideDTO.
     */
    public RideDTO getRideDTO() {
        return rideDTO;
    }

    /**
     * Sets the RideDTO.
     * @param rideDTO The RideDTO.
     */
    public void setRideDTO(RideDTO rideDTO) {
        this.rideDTO = rideDTO;
    }

    /**
     * Provides the full driver UserDTO, loaded by viewAction to the Facelet.
     * Bound to: 'o_providerName', 'gi_providerImage'
     * @return The UserDTO of the provider.
     */
    public UserDTO getDriver() {
        return driver;
    }

    /**
     * Sets the driver UserDTO.
     * @param driver The UserDTO of the provider.
     */
    public void setDriver(UserDTO driver) {
        this.driver = driver;
    }
}