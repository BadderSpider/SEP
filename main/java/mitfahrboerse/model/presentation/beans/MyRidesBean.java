package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.BookingDTO;
import mitfahrboerse.global.dto.RideDTO;
import mitfahrboerse.global.dto.SearchCriteriaDTO;
import mitfahrboerse.model.business.service.BookingService;
import mitfahrboerse.model.business.service.FindService;
import mitfahrboerse.model.business.service.RideService;
import mitfahrboerse.model.presentation.util.UserSessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing bean for the my_rides.xhtml page.
 * Inherits from AbstractPagedTableBean.
 * To display and manage a list of rides. For regular users,
 * it shows only their own rides. For admins, it shows all rides in the system.
 * It also manages the dialog for booking requests ('cb_showBookings').
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class MyRidesBean extends AbstractPagedTableBean<RideDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Holds the list of booking requests for the ride selected in the dialog.
     * Bound to 'dt_bookings'.
     */
    private List<BookingDTO> currentBookings;
    
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
    	this.currentBookings = new ArrayList<>();
    	filter(); 
    }

    /**
     * Action method called by the paged table (e.g., search or page change).
     * To load the list of rides (RideDTOs) based on the user's role.
     * Admins see all rides; regular users see only their own.
     */
    @Override
    public void filter() {
        SearchCriteriaDTO criteria = getSearchCriteriaDto(); 
    }
    
    /**
     * Action method for 'cb_deleteRide'.
     * Deletes the selected ride.
     * @param rideDto The RideDTO to delete (passed from the table row).
     */
    public void deleteRide(RideDTO rideDto) {
    }

    /**
     * Action method for 'cb_showBookings'.
     * Loads the booking requests for the selected ride into the
     * 'currentBookings' list to display them in the dialog.
     * @param rideDto The RideDTO (passed as 'pagedItem') to show bookings for.
     */
    public void showBookingRequests(RideDTO rideDto) {
    }

    /**
     * Action method for 'cb_acceptBooking' .
     * Accepts a pending booking request from the dialog.
     * @param booking The BookingDTO (passed as 'booking') to accept.
     */
    public void acceptBooking(BookingDTO booking) {
    }

    /**
     * Action method for 'cb_rejectBooking'.
     * Rejects a pending booking request from the dialog.
     * @param booking The BookingDTO to reject.
     */
    public void rejectBooking(BookingDTO booking) {
    }

    /**
     * Action method for 'cb_removePassenger'.
     * Removes an already accepted passenger (booking) from a ride.
     * @param booking The BookingDTO to remove (cancel).
     */
    public void removePassenger(BookingDTO booking) {
    }

    // --- GETTER & SETTER ---

    /**
     * Provides the list of bookings for the currently selected ride.
     * @return A List of BookingDTOs.
     */
    public List<BookingDTO> getCurrentBookings() {
        return currentBookings;
    }

    /**
     * Sets the list of bookings for the currently selected ride.
     * @param currentBookings A List of BookingDTOs.
     */
    public void setCurrentBookings(List<BookingDTO> currentBookings) {
    	this.currentBookings = currentBookings;
    }

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
}