package mitfahrboerse.global.dto;

/**
 * Data Transfer Object representing an offer made by a driver 
 * (via a specific Ride) for a ride request.
 *
 * @author Anton Hollube
 */
public class OfferDTO {

    private long offerId;
    private long rideId; 
    private long requestId;
    private OfferStatus status;

    public OfferDTO(long offerId, long rideId, long requestId, OfferStatus status) {
        this.offerId = offerId;
        this.rideId = rideId;
        this.requestId = requestId;
        this.status = status;
    }

    // --- GETTER ---

    public long getOfferId() {
        return this.offerId;
    }

    public long getRide() {
        return this.rideId;
    }

    public long getRequest() {
        return this.requestId;
    }

    public OfferStatus getStatus() {
        return this.status;
    }

    // --- SETTER ---

    /**
     * Sets the ID of this offer.
     * 
     * @param value The offer ID.
     */
    public void setOfferId(long value) {
        this.offerId = value;
    }

    /**
     * Sets the ride that is being offered in response to the request.
     * 
     * @param value The RideDTO being offered.
     */
    public void setRide(long value) {
        this.rideId = value;
    }

    /**
     * Sets the request this offer is for.
     * 
     * @param value The RequestDTO.
     */
    public void setRequest(long value) {
        this.requestId = value;
    }

    /**
     * Sets the status of the offer (PENDING, ACCEPTED).
     * 
     * @param value The status string.
     */
    public void setStatus(OfferStatus value) {
        this.status = value;
    }
}