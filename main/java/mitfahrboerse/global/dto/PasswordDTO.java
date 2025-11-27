package mitfahrboerse.global.dto;

/**
 * Data Transfer Object for the password and its hash value.
 *  
 * @author Kerstin Arnoczky
 */
public class PasswordDTO {

    private long userId;
    private String salt;
    private String passwordHash;

    public PasswordDTO() {}

    public PasswordDTO(long userId, String salt, String passwordHash) {
        this.userId = userId;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }

    public long getUserId() {
        return userId;
    }

    /**
     * Sets the user ID the password belongs to.
     * 
     * @param userId The valid user ID.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSalt() {
        return salt;
    }

    /**
     * Sets the salt for the user.
     * 
     * @param salt The user salt.
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the hash for the given password.
     * 
     * @param passwordHash The hash of the password.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
