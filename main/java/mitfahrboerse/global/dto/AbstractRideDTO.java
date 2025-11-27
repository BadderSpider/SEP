package mitfahrboerse.global.dto;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Data Transfer Object for a ride offer.
 *
 * @author Anton Hollube
 */
public class AbstractRideDTO {

    private long driverId;
    private long rideId;
    private TransportationType transportationType;
    private double pricePerSeat;
    private int maxSeats;
    private int maxBaggage;
    private String description;
    private List<StopDTO> stops;
    
    public AbstractRideDTO() {
    }

    public AbstractRideDTO(long driverId, long rideId, TransportationType transportationType, double pricePerSeat, int maxSeats, int maxBaggage, String description, List<StopDTO> stops) {
        this.driverId = driverId;
        this.rideId = rideId;
        this.transportationType = transportationType;
        this.pricePerSeat = pricePerSeat;
        this.maxSeats = maxSeats;
        this.maxBaggage = maxBaggage;
        this.description = description;
        this.stops = stops;
    }

    public long getDriverId() {
        return driverId;
    }

    /**
     * Sets the ID of the driver that the ride belongs to.
     * 
     * @param driverId ID of the user that owns the ride.
     */
    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public long getRideId() {
        return rideId;
    }

    /**
     * Sets the ID of the ride.
     * 
     * @param rideId Valid ID of the ride.
     */
    public void setRideId(long rideId) {
        this.rideId = rideId;
    }

    public TransportationType getTransportationType() {
        return transportationType;
    }

    /**
     * Sets the form of transportation.
     * 
     * @param transportationType The type of transportation the ride offers.
     */
    public void setTransportationType(TransportationType transportationType) {
        this.transportationType = transportationType;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    /**
     * Sets the price per individual seat.
     * 
     * @param pricePerSeat The cost of the seat.
     */
    public void setPricePerSeat(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    /**
     * Sets the maximum amount of seats available.
     * 
     * @param maxSeats The maximum amount of available seats.
     */
    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public int getMaxBaggage() {
        return maxBaggage;
    }

    /**
     * Sets the maximum amount of baggage available.
     * 
     * @param maxBaggage The maximum amount of available baggage.
     */
    public void setMaxBaggage(int maxBaggage) {
        this.maxBaggage = maxBaggage;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the ride.
     * 
     * @param description The description for the ride.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public List<StopDTO> getStops() {
        return stops;
    }

    /**
     * Sets all stops for the ride, including start, end and everything inbetween.
     * 
     * @param stops All stops as a List.
     */
    public void setStops(List<StopDTO> stops) {
        this.stops = stops;
    }
}