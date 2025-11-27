package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.CycleDTO;
import mitfahrboerse.global.dto.CycleType;
import mitfahrboerse.global.dto.RideDTO;
import mitfahrboerse.global.dto.StopDTO;
import mitfahrboerse.model.business.service.FindService;
import mitfahrboerse.model.business.service.RideService;
import mitfahrboerse.model.presentation.util.OwnershipChecker;
import mitfahrboerse.model.presentation.util.UserSessionBean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Backing bean for the ride_form.xhtml page.
 * Inherits from SafeActionBean.
 * To manage the creation (new) and editing (existing) of ride offers.
 * Loading Pattern:
 * Edit: The main RideDTO is loaded via f:viewParam and RideConverter.
 * New: If no ID is present, init() creates a new, empty RideDTO.
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class RideFormBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The main DTO for the form.
     * Loaded by the RideConverter if 'rideId' is in the URL (Edit mode).
     * If URL has no ID, it's initialized in init() (New mode).
     * Bound to all fields like 'it_price', 'it_seats'.
     */
    private RideDTO rideDTO;
    

    /**
     * Holds data for a new stopover before it's added to the list.
     * Bound to 'it_newStopAddress' and 'it_newStopTime'.
     */
    private StopDTO newStopover;
    
    /**
     * Holds the date for a new exception day before it's added.
     * Bound to 'it_newExceptionDate'.
     */
    private LocalDate newExceptionDate;
    
    /**
     * Holds the state for the 'isRecurring' checkbox.
     * Bound to 'sbc_isRecurring'.
     */
    private boolean isRecurring;

    
    @Inject
    private OwnershipChecker ownershipChecker;
    
    @Inject
    private UserSessionBean userSessionBean;

    /**
     * Initializes the bean.
     */
    @PostConstruct
    public void init() {

    }

    /**
     * Action method for 'cb_save' button.
     * Saves the ride (either creating a new one or updating an existing one).
     *
     * @return The navigation outcome (redirects to my_rides).
     */
    public String save() {
		return null;
      
    }

    /**
     * Action method for 'cb_delete' button.
     * Deletes the ride currently being edited.
     *
     * @return The navigation outcome (redirects to my_rides).
     */
    public String deleteRide() {
		return null;
    }

    /**
     * Action method for 'cb_addStopover'.
     * Adds the temporary 'newStopover' object to the ride's list of stops.
     */
    public void addStopover() {
    	
    }
    
    /**
     * Action method for 'cb_deleteStop'.
     * Removes a specific stopover from the ride's list.
     *
     * @param stopover The StopDTO object to remove (passed from dt_stopovers).
     */
    public void removeStopover(StopDTO stopover) {
        this.rideDTO.getStops().remove(stopover);
    }

    /**
     * Action method for 'cb_addExceptionDay'.
     * Adds the temporary 'newExceptionDate' to the ride's list of exceptions.
     */
    public void addExceptionDay() {
    }

    /**
     * Action method for 'cb_deleteException'.
     * Removes a specific exception day from the ride's list.
     *
     * @param exceptionDate The date object to remove.
     */
    public void removeExceptionDay(LocalDate exceptionDate) {
    }

    // --- GETTER & SETTER ---

    /**
     * Provides the main DTO for form bindings.
     * @return The RideDTO (or CycleDTO).
     */
    public RideDTO getRideDTO() {
        return rideDTO;
    }

    /**
     * Sets the main DTO.
     * @param rideDTO The RideDTO.
     */
    public void setRideDTO(RideDTO rideDTO) {
        this.rideDTO = rideDTO;
    }

    /**
     * Provides the temporary StopDTO for 'it_newStopAddress' / 'it_newStopTime'.
     * @return The new StopDTO.
     */
    public StopDTO getNewStopover() {
        return newStopover;
    }

    /**
     * Sets the temporary StopDTO.
     * @param newStopover The new StopDTO.
     */
    public void setNewStopover(StopDTO newStopover) {
        this.newStopover = newStopover;
    }

    /**
     * Provides the temporary Date for 'it_newExceptionDate'.
     * @return The new exception date.
     */
    public LocalDate getNewExceptionDate() {
        return newExceptionDate;
    }

    /**
     * Sets the temporary Date (used by JSF).
     * @param newExceptionDate The new exception date.
     */
    public void setNewExceptionDate(LocalDate newExceptionDate) {
        this.newExceptionDate = newExceptionDate;
    }

    /**
     * Provides the boolean state for 'sbc_isRecurring'.
     * @return true if the ride is recurring.
     */
    public boolean getIsRecurring() {
        return isRecurring;
    }

    /**
     * Sets the boolean state for 'sbc_isRecurring'.
     * @param isRecurring true if the ride is recurring.
     */
    public void setIsRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    /**
     * Gets the list of available cycle types (e.g., WEEKLY, MONTHLY) for 'som_recurrenceRule'.
     * @return A List of CycleType enums.
     */
    public List<CycleType> getCycleTypes() {
        return Arrays.asList(CycleType.values());
    }
}