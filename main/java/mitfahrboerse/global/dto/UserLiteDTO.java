package mitfahrboerse.global.dto;

/**
 * Data Transfer Object for a lightweight user representation.
 * Used to display public info like a driver's name and rating,
 * without exposing data like email or address.
 *
 * @author Anton Hollube
 */
public class UserLiteDTO {

    private long userId;
    private String firstName;
    private String lastName;
    private double averageRating;

    public UserLiteDTO(long userId, String firstName, String lastName,  double averageRating) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageRating = averageRating;
    }

    // --- GETTER ---

    public long getUserId() {
        return this.userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public double getAverageRating() {
        return this.averageRating;
    }

    // --- SETTER ---

    /**
     * Sets the unique ID for this user.
     * 
     * @param value The user's ID.
     */
    public void setUserId(long value) {
        this.userId = value;
    }

    /**
     * Sets the user's first name.
     * 
     * @param value The first name.
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Sets the user's last name.
     * 
     * @param value The last name.
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Sets the user's calculated average rating.
     * 
     * @param value The average rating.
     */
    public void setAverageRating(double value) {
        this.averageRating = value;
    }
}