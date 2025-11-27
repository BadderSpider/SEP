package mitfahrboerse.global.dto;

import java.time.OffsetDateTime;

/**
 * Data Transfer Object for a ride request.
 *
 * @author Anton Hollube
 */
public class RequestDTO {

    private long requestId;
    private long requesterId;
    private String start;
    private String destination;
    private int price;
    private int baggage;
    private String description;
    private OffsetDateTime desiredTime;

    public RequestDTO(long requestId, long requesterId, String start, String destination, int price, int baggage, String description, OffsetDateTime desiredTime) {
        this.requestId = requestId;
        this.requesterId = requesterId;
        this.start = start;
        this.destination = destination;
        this.price = price;
        this.baggage = baggage;
        this.description = description;
        this.desiredTime = desiredTime;
    }

    public long getRequestId() {
        return requestId;
    }

    /**
     * Sets the ID of the request.
     * 
     * @param requestId The request ID.
     */
    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getRequesterId() {
        return requesterId;
    }

    /**
     * Sets the user ID of the user publishing the request.
     * 
     * @param requesterId The valid user ID.
     */
    public void setRequesterId(long requesterId) {
        this.requesterId = requesterId;
    }

    public String getStart() {
        return start;
    }

    /**
     * Sets the starting point the requester would like to start from.
     * 
     * @param start The starting point.
     */
    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    /**
     * Sets the end point the requester would like to go to.
     * 
     * @param destination The ending point.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPrice() {
        return price;
    }

    /**
     * Sets the price the requester offers.
     * 
     * @param price The price of the request.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    public int getBaggage() {
        return baggage;
    }

    /**
     * Sets the amount of baggage the requester uses.
     * 
     * @param baggage The amount of baggage for the request.
     */
    public void setBaggage(int baggage) {
        this.baggage = baggage;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the request.
     * 
     * @param description The description of the request.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getDesiredTime() {
        return desiredTime;
    }

    /**
     * Sets the time the requester would like to start travel.
     * 
     * @param desiredTime The time of the request.
     */
    public void setDesiredTime(OffsetDateTime desiredTime) {
        this.desiredTime = desiredTime;
    }
}