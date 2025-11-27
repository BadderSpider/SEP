package mitfahrboerse.global.dto;

/**
 * Data Transfer Object representing a booking relation between a User and a Ride.
 *
 * @author Anton Hollube
 */
public class BookingDTO {

    private long bookingId;
    private long rideId;
    private long passengerId;
    private long startId;
    private long stopId;
    private BookingStatus status;
    private int bookedSeats;
    private int bookedBaggage;

    public BookingDTO(long bookingId, long rideId, long passengerId, long startId, long stopId, BookingStatus status, int bookedSeats, int bookedBaggage) {
        this.bookingId = bookingId;
        this.rideId = rideId;
        this.passengerId = passengerId;
        this.startId = startId;
        this.stopId = stopId;
        this.status = status;
        this.bookedSeats = bookedSeats;
        this.bookedBaggage = bookedBaggage;
    }

    public long getBookingId() {
        return this.bookingId;
    }

    public long getPassenger() {
        return this.passengerId;
    }

    public long getRideId() {
        return this.rideId;
    }

    public BookingStatus getStatus() {
        return this.status;
    }

    public int getBookedSeats() {
        return this.bookedSeats;
    }

    public int getRequestedBaggage() {
        return this.bookedBaggage;
    }

    /**
     * Sets the ID of the booking. The booking ID is not the ride ID.
     * 
     * @param bookingId The ID of the booking.
     */
    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Sets the ID of the ride. The ride ID is not the booking ID.
     * 
     * @param rideId The ID of the ride.
     */
    public void setRideId(long rideId) {
        this.rideId = rideId;
    }

    public long getPassengerId() {
        return passengerId;
    }

    /**
     * Sets the ID of the passenger.
     * 
     * @param passengerId The valid user ID of the passenger.
     */
    public void setPassengerId(long passengerId) {
        this.passengerId = passengerId;
    }

    public long getStartId() {
        return startId;
    }

    /**
     * Sets the ID for the starting point of the ride.
     * 
     * @param startId The ID of the stop where the ride begins.
     */
    public void setStartId(long startId) {
        this.startId = startId;
    }

    public long getStopId() {
        return stopId;
    }

    /**
     * Sets the ID for the end point of the ride.
     * 
     * @param stopId The ID of the stop where the ride ends.
     */
    public void setStopId(long stopId) {
        this.stopId = stopId;
    }

    /**
     * Sets the status of the booking, e. g. whether it's been accepted.
     * 
     * @param status The current status of the booking.
     */
    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    /**
     * Sets the amount of taken seats.
     * 
     * @param bookedSeats The amount of taken seats.
     */
    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public int getBookedBaggage() {
        return bookedBaggage;
    }

    /**
     * Sets the amount of taken baggage.
     * 
     * @param bookedBaggage The amount of taken baggage.
     */
    public void setBookedBaggage(int bookedBaggage) {
        this.bookedBaggage = bookedBaggage;
    }
}