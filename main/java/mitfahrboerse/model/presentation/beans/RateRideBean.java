package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.RatingDTO;
import mitfahrboerse.model.business.service.BookingService;
import mitfahrboerse.model.business.service.RatingService;
import mitfahrboerse.model.business.service.FindService;
import mitfahrboerse.model.business.exception.BusinessException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Backing bean for the rate_ride.xhtml page .
 * Inherits from SafeActionBean.
 * To manage loading the ride/booking to be rated and saving the new rating.
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class RateRideBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The DTO to hold all rating data.
     */
    private RatingDTO rating;


    /**
     * Initializes the bean.
     */
    @PostConstruct
    public void init() {

    }

    /**
     * ViewAction method (called by f:viewAction).
     * To load the descriptive text for the ride after
     * the bookingId has been set by the viewParam.
     */
    public void loadRide() {
    }

    /**
     * Action method for 'cb_saveRating' button.
     * Saves the new rating (held in the DTO) for the current booking.
     * @return The navigation outcome (redirects to my_bookings).
     */
    public String saveRating() {
        return null; // Stay on page
    }

    // --- GETTER & SETTER ---

    /**
     * Provides the DTO that holds the rating data.
     * @return The RatingDTO.
     */
    public RatingDTO getRating() {
        return rating;
    }

    /**
     * Sets the DTO.
     * 
     * @param rating The RatingDTO.
     */
    public void setRating(RatingDTO rating) {
        this.rating = rating;
    }

}