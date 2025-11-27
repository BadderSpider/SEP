package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.RequestDTO;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.global.dto.RideDTO;
import mitfahrboerse.model.business.service.FindService;
import mitfahrboerse.model.business.service.ProfileService;
import mitfahrboerse.model.business.service.RequestService;
import mitfahrboerse.model.presentation.util.OwnershipChecker;
import mitfahrboerse.model.presentation.util.UserSessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Backing bean for the request_details.xhtml page.
 * Inherits from SafeActionBean.
 * To display detailed information about a single ride request.
 * It uses a two-phase loading mechanism:
 * 1. The RequestDTO is loaded by a Converter via f:viewParam.
 * 2. The f:viewAction calls loadRequestDetails() to fetch associated data (requester profile).
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class RequestDetailBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The main RequestDTO for this page.
     * Loaded automatically by JSF via the f:viewParam
     * tag (name="reqId") and the RequestConverter.
     */
    private RequestDTO requestDTO;

    /**
     * The full UserDTO of the request's creator (requester).
     * This object is loaded by the 'loadRequestDetails' viewAction
     * to display the requester's name, rating, and contact info.
     */
    private UserDTO requester;

    /**
     * Holds the ID of the ride selected in the 'som_offerRide' dropdown.
     */
    private long selectedRideId;
    
    /**
     * Holds the list of rides the current user can offer.
     * Bound to 'som_offerRide'.
     */
    private List<RideDTO> availableRides;
    
    @Inject
    private UserSessionBean userSessionBean;
    
    @Inject
    private OwnershipChecker ownershipChecker;

    /**
     * Initializes the bean.
     */
    @PostConstruct
    public void init() {
    }

    /**
     * ViewAction method (called by f:viewAction).
     * To load all secondary data (requester profile, available rides)
     * *after* the main requestDTO has been loaded by the converter.
     */
    public void loadRequestDetails() {

    }

    /**
     * Action method for 'cb_offerRide' button.
     * To create an offer from the current user (driver) for this request.
     */
    public void offerRide() {
    }

    /**
     * Action method for 'cb_deleteRequest' button.
     * To delete the currently viewed request (only for owner/admin).
     */
    public String deleteRequest() {
		return null;

    }

    /**
     * Helper method for 'rendered' attributes.
     * Checks if the currently logged-in user is the owner of this request.
     * @return true if the user is the owner, false otherwise.
     */
    public boolean isOwnRequest() {
		return false;
    }

    // --- GETTER & SETTER ---

    /**
     * Provides the RequestDTO (loaded by converter) to the Facelet.
     * @return The RequestDTO.
     */
    public RequestDTO getRequestDTO() {
        return requestDTO;
    }

    /**
     * Sets the RequestDTO (used by JSF converter).
     * @param requestDTO The RequestDTO.
     */
    public void setRequestDTO(RequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }

    /**
     * Provides the full requester UserDTO (loaded by viewAction) to the Facelet.
     * Bound to: 'o_requesterName', 'gi_requesterImage'
     * @return The UserDTO of the requester.
     */
    public UserDTO getRequester() {
        return requester;
    }

    /**
     * Sets the requester UserDTO.
     * @param requester The UserDTO of the requester.
     */
    public void setRequester(UserDTO requester) {
        this.requester = requester;
    }

    /**
     * Provides the ID of the ride selected in the 'som_offerRide' dropdown.
     * @return The selected ride ID.
     */
    public long getSelectedRideId() {
        return selectedRideId;
    }

    /**
     * Sets the ID of the ride selected in the 'som_offerRide' dropdown.
     * @param selectedRideId The selected ride ID.
     */
    public void setSelectedRideId(long selectedRideId) {
        this.selectedRideId = selectedRideId;
    }

    /**
     * Provides the list of available rides (f:selectItems) for the 'som_offerRide' dropdown.
     * @return A List of RideDTOs.
     */
    public List<RideDTO> getAvailableRides() {
        return availableRides;
    }

    /**
     * Sets the list of available rides.
     * @param availableRides A List of RideDTOs.
     */
    public void setAvailableRides(List<RideDTO> availableRides) {
        this.availableRides = availableRides;
    }
}