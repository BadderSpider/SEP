package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.BookingDTO;
import mitfahrboerse.global.dto.SearchCriteriaDTO;
import mitfahrboerse.model.presentation.util.UserSessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;


/**
 * Backing bean for the my_bookings.xhtml page.
 * Inherits from AbstractPagedTableBean.
 * To manage and display the list of rides the
 * currently logged-in user has booked (as a passenger).
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class MyBookingsBean extends AbstractPagedTableBean<BookingDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
        filter(); 
    }

    /**
     * Action method called by the paged table.
     * To load all bookings for the currently logged-in user.
     */
    @Override
    public void filter() {
        SearchCriteriaDTO criteria = getSearchCriteriaDto();
    }

    /**
     * Action method for 'cb_cancelBooking'.
     * Cancels a specific booking (e.g., the user no longer wants to ride).
     * @param item The BookingDTO (passed as 'pagedItem') to cancel.
     */
    public void cancelBooking(BookingDTO item) {
    }

    /**
     * Navigation method for 'l_rateRide'.
     * Navigates to the rating page for a completed ride.

     * @param item The BookingDTO (passed as 'pagedItem') to rate.
     * @return The navigation outcome string.
     */
    public String rateRide(BookingDTO item) {
    	return null;
    }

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
}