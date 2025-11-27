package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.RequestDTO;
import mitfahrboerse.model.business.service.RequestService;
import mitfahrboerse.model.presentation.util.OwnershipChecker;
import mitfahrboerse.model.presentation.util.UserSessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Backing bean for the request_form.xhtml page.
 * Inherits from SafeActionBean.
 * To manage the creation (new) and editing (existing) of ride requests.
 * Loading Pattern:
 * Edit: The RequestDTO is loaded via f:viewParam and RequestConverter.
 * New: If no ID is present, init() creates a new, empty RequestDTO.
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class RequestFormBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The main DTO for the form.
     * Loaded by the RequestConverter if 'reqId' is in the URL (Edit mode).
     * If URL has no ID, it's initialized in init() (New mode).
     * Bound to all fields like 'it_startAddress', 'it_price'.
     */
    private RequestDTO requestDTO;
    
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
     * Action method for 'cb_saveRequest' button.
     * Saves the request (either creating a new one or updating an existing one).
     * @return The navigation outcome (redirects to my_requests).
     */
    public String save() {
        return null; 
    }

    /**
     * Action method for 'cb_deleteRequest' button.
     * Deletes the request currently being edited.
     * @return The navigation outcome (redirects to my_requests).
     */
    public String deleteRequest() {
            return "/view/registered/my_requests.xhtml?faces-redirect=true";
    }

    /**
     * ViewAction method.
     * Loads the full request data after the 'reqId' has been set 
     * by the f:viewParam.
     * * If no ID (or ID=0) is present, it's a new request.
     * If an ID is present, it's an edit, and the full DTO is fetched.
     * This method also performs an ownership check to prevent
     * Insecure Direct Object Reference (IDOR) attacks.
     */
    public void loadRequestForm() {
    	
    }
    
    // --- GETTER & SETTER ---

    /**
     * Provides the main DTO for form bindings (e.g., 'it_startAddress').
     * @return The RequestDTO.
     */
    public RequestDTO getRequestDTO() {
        return requestDTO;
    }

    /**
     * Sets the main DTO.
     * @param requestDTO The RequestDTO.
     */
    public void setRequestDTO(RequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }
}