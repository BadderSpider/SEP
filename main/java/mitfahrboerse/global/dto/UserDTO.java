package mitfahrboerse.global.dto;

/**
 * Data Transfer Object for a user's full profile.
 *
 * @author Anton Hollube
 */
public class UserDTO {

    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;

    private UserRole userRole;
    private int ratingCount;
    private double averageRating;
    private boolean mailVerified;

    // Only used by the view
    private boolean notifications;
    private String profilePictureURL;

    public UserDTO() {}

    public UserDTO(long userId, String firstName, String lastName, String email, String phone, AddressDTO address, UserRole userRole, int ratingCount, double averageRating, boolean mailVerified) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
        this.ratingCount = ratingCount;
        this.averageRating = averageRating;
        this.mailVerified = mailVerified;

        notifications = false;
        profilePictureURL = null;
    }

    public long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user.
     * 
     * @param userId The user ID.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * 
     * @param firstName The user's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * 
     * @param lastName The user's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * 
     * @param email The user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the user.
     * 
     * @param phone The user's phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDTO getAddress() {
        return address;
    }

    /**
     * Sets the address of the user.
     * 
     * @param address The user's address.
     */
    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets the role of the user.
     * 
     * @param userRole The user's role.
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    /**
     * Sets the amount of ratings the user has ever received.
     * 
     * @param ratingCount The user's rating.
     */
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Sets the average rating for the user.
     * 
     * @param averageRating The user's average rating.
     */
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public boolean isMailVerified() {
        return mailVerified;
    }

    /**
     * Sets whether the user's email has been verified or not.
     * 
     * @param mailVerified True if the mail is verified, otherwise false.
     */
    public void setMailVerified(boolean mailVerified) {
        this.mailVerified = mailVerified;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    /**
     * Sets the URL for the user's profile picture.
     * 
     * @param profilePictureURL The URL of the image.
     */
    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public boolean isNotifications() {
        return notifications;
    }

    /**
     * Sets whether the user would like to receive email notifications.
     * 
     * @param notifications True if the user wants to receive notifications, otherwise false.
     */
    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }
}