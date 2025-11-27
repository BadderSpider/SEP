package mitfahrboerse.global.dto;

import java.time.OffsetDateTime;


/**
 * Data Transfer Object for initiating a password reset.
 * 
 * @author Anton Hollube
 */
public class PasswordResetDTO extends PasswordDTO {

    private OffsetDateTime expiresAt;

    // Only used by the view
    private String token;
    private String newPassword;

    // Only used by the database
    private String tokenHash;
	private String newPasswordHash;

    public PasswordResetDTO(long userId, String salt, String passwordHash, OffsetDateTime expiresAt, String tokenHash, String newPasswordHash) {
        super(userId, salt, passwordHash);
        this.expiresAt = expiresAt;
        this.token = null;
        this.newPassword = null;
        this.tokenHash = tokenHash;
        this.newPasswordHash = newPasswordHash;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    /**
     * Sets the time when the password reset expires.
     * 
     * @param expiresAt The time of the expiration date. 
     */
    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    /**
     * Sets the token for the password reset.
     * 
     * @param token The password reset's token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the new password the user wishes to use.
     * 
     * @param newPassword The new password of the user.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    /**
     * Sets the hash for the token.
     * 
     * @param tokenHash The token's hash.
     */
    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }

    public String getNewPasswordHash() {
        return newPasswordHash;
    }

    /**
     * Sets the hash of the new password.
     * 
     * @param newPasswordHash The hash of the new password.
     */
    public void setNewPasswordHash(String newPasswordHash) {
        this.newPasswordHash = newPasswordHash;
    }
}