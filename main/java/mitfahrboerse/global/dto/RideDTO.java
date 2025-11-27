package mitfahrboerse.global.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for a specific ride. Encapsules information that is individual for every ride such as a ride ID.
 *
 * @author Kerstin Arnoczky
 */
public class RideDTO extends AbstractRideDTO{

	private LocalDate date;
    private LocalDate publicationDate;
	private RideStatus rideStatus;

    public RideDTO(long driverId, long rideId, TransportationType transportationType, double pricePerSeat, int maxSeats, int maxBaggage, String description, List<StopDTO> stops, LocalDate date, LocalDate publicationDate, RideStatus rideStatus) {
        super(driverId, rideId, transportationType, pricePerSeat, maxSeats, maxBaggage, description, stops);
        this.date = date;
        this.publicationDate = publicationDate;
        this.rideStatus = rideStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date when the ride takes place.
     * 
     * @param date The date of the ride.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    /**
     * Sets the date when the ride should be published.
     * 
     * @param publicationDate The publication date.
     */
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    /**
     * Sets the current status of the ride.
     * 
     * @param rideStatus The status of the ride.
     */
    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }
}
