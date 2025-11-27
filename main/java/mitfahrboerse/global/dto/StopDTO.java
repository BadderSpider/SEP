package mitfahrboerse.global.dto;

import java.time.OffsetDateTime;

/**
 * Data Transfer Object representing a single stop (e.g., start, stopover, destination) in a ride.
 *
 * @author Anton Hollube
 */
public class StopDTO {

    private long stopId;
    private long rideId;
    private StopType type;
    private String location;
    private int availableSeats;
    private int availableBaggage;
    private OffsetDateTime timeStamp;
    private int order;

    public StopDTO(long stopId, long rideId, StopType type, String location, int availableSeats, int availableBaggage, OffsetDateTime timeStamp, int order) {
        this.stopId = stopId;
        this.rideId = rideId;
        this.type = type;
        this.location = location;
        this.availableSeats = availableSeats;
        this.availableBaggage = availableBaggage;
        this.timeStamp = timeStamp;
        this.order = order;
    }


    public long getStopId() {
        return stopId;
    }

    /**
     * Sets the ID of the stop.
     * 
     * @param stopId The ID of the stop.
     */
    public void setStopId(long stopId) {
        this.stopId = stopId;
    }

    public long getRideId() {
        return rideId;
    }

    /**
     * Sets the ID of the ride the stop belongs to.
     * 
     * @param rideId The ID of the ride.
     */
    public void setRideId(long rideId) {
        this.rideId = rideId;
    }

    public StopType getType() {
        return type;
    }

    /**
     * Sets the type of the stop.
     * 
     * @param type The stop type.
     */
    public void setType(StopType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    /**
     * Sets the location aka address of the stop.
     * 
     * @param location The stop location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    /**
     * Sets the amount of available seats from this stop on moving forward.
     * 
     * @param availableSeats The amount of available seats.
     */
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getAvailableBaggage() {
        return availableBaggage;
    }

    /**
     * Sets the amount of available baggage from this stop on moving forward.
     * 
     * @param availableBaggage The amount of available baggage.
     */
    public void setAvailableBaggage(int availableBaggage) {
        this.availableBaggage = availableBaggage;
    }

    public OffsetDateTime getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the time when the stop will be reached and departed.
     * 
     * @param timeStamp The time for the stop.
     */
    public void setTimeStamp(OffsetDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getOrder() {
        return order;
    }

    /**
     * Sets the order when the stop takes place 
     * (the starting destination is reached first, the first stop second and so on).
     * 
     * @param order The order of the stop.
     */
    public void setOrder(int order) {
        this.order = order;
    }
}