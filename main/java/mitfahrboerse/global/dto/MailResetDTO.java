package mitfahrboerse.global.dto;

import java.time.OffsetDateTime;

/**
 * Data Transfer Object for encapsulating a reset mail request.
 *
 * @author Kerstin Arnoczky
 */
public class MailResetDTO {

	private long userId;
	private String newMail;
	private OffsetDateTime expiresAt;

    // Only used by the view
    private String token;

    // Only used by the database
    private String tokenHash;

    public MailResetDTO(long userId, String newMail, OffsetDateTime expiresAt, String tokenHash) {
        this.userId = userId;
        this.newMail = newMail;
        this.expiresAt = expiresAt;
        this.token = null;
        this.tokenHash = tokenHash;
    }

    public long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user that wishes to reset their email.
     * 
     * @param userId The valid user ID.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNewMail() {
        return newMail;
    }

    /**
     * Sets the email address the user wishes to switch to.
     * 
     * @param newMail The valid email address.
     */
    public void setNewMail(String newMail) {
        this.newMail = newMail;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    /**
     * Sets the time the sent out reset email is going to expire.
     * 
     * @param expiresAt The time the email expires.
     */
    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    /**
     * Sets the token of the mail reset.
     * 
     * @param token The token of the mail reset.
     */
    public void setToken(String token) {
        this.token = token;
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
}
