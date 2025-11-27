package mitfahrboerse.global.dto;



/**
 * Data Transfer Object representing a rating given by a passenger
 * for a completed booking.
 *
 * @author Anton Hollube
 */
public class RatingDTO {

    private long userId;
    private int stars;          

    public RatingDTO(int stars, long userId) {
        this.stars = stars;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user (therefore the passenger) the rating belongs to.
     * 
     * @param userId The valid user ID.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStars() {
        return stars;
    }

    /**
     * Sets the amount of stars the user wishes to rate the ride.
     * 
     * @param stars The amount of stars.
     */
    public void setStars(int stars) {
        this.stars = stars;
    }
}