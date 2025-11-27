package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.RequestDTO;
import mitfahrboerse.global.dto.OfferDTO;
import mitfahrboerse.global.dto.SearchCriteriaDTO;
import mitfahrboerse.model.business.service.FindService;
import mitfahrboerse.model.business.service.RequestService;
import mitfahrboerse.model.presentation.util.UserSessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Backing bean for the my_requests.xhtml page.
 * Inherits from AbstractPagedTableBean.
 * To manage and display a list of ride requests. For regular users,
 * it shows their own requests. For admins, it shows all requests in the system.
 * It also manages the dialog for incoming offers ('cb_showOffers').
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class MyRequestsBean extends AbstractPagedTableBean<RequestDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
    /**
     * Holds the list of offers for the request selected in the dialog.
     * Bound to 'dt_offers'.
     */
    private List<OfferDTO> currentOffers;
    
    @Inject
    private UserSessionBean userSessionBean;

    /**
     * Initializes the bean.
     * Initializes the parent table and loads the first page of data.
     */
    @PostConstruct
    @Override
    public void init() {
    	super.init();
        this.currentOffers = new ArrayList<>();
        filter();
    }

    /**
     * Action method called by the paged table.
     * To load the list of RequestDTOs based on the user's role.
     * Admins see all requests; regular users see only their own.
     */
    @Override
    public void filter() {
        SearchCriteriaDTO criteria = getSearchCriteriaDto();
    }
    
    /**
     * Action method for 'cb_deleteRequest'.
     * Deletes the selected request.
     * @param item The RequestDTO (passed as 'pagedItem') to delete.
     */
    public void deleteRequest(RequestDTO item) {
    }

    /**
     * Action method for 'cb_showOffers'.
     * Loads the offers for the selected request into the 'currentOffers' list
     * for the dialog.
     * @param item The RequestDTO (passed as 'pagedItem') to show offers for.
     */
    public void showOffers(RequestDTO item) {
      
    }

    /**
     * Action method for 'cb_acceptOffer' .
     * Accepts a driver's offer (from the dialog).
     * @param offer The OfferDTO (passed as 'offer') to accept.
     */
    public void acceptOffer(OfferDTO offer) {
    	
    }

    /**
     * Action method for 'cb_rejectOffer'.
     * Rejects a driver's offer (from the dialog).
     * @param offer The OfferDTO (passed as 'offer') to reject.
     */
    public void rejectOffer(OfferDTO offer) {
    }

    // --- GETTER & SETTER ---

    /**
     * Provides the list of offers for the currently selected request (for the dialog).
     * @return A List of OfferDTOs.
     */
    public List<OfferDTO> getCurrentOffers() {
        return this.currentOffers;
    }

    /**
     * Sets the list of offers for the currently selected request.
     * @param currentOffers A List of OfferDTOs.
     */
    public void setCurrentOffers(List<OfferDTO> currentOffers) {
        this.currentOffers = currentOffers;
    }

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
}