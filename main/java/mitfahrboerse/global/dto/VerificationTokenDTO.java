package mitfahrboerse.global.dto;

import java.time.OffsetDateTime;

/**
 * Data Transfer Object for Mail and Password Change.
 *
 * @author Anton Hollube
 */
public class VerificationTokenDTO {

    private long userId;
    private TokenType tokenType;
    private OffsetDateTime expiresAt;
    private String email;

    // Only used by the view
    private String token;

    // Only used by the database
    private String tokenHash;

    public VerificationTokenDTO(long userId, TokenType tokenType, OffsetDateTime expiresAt, String tokenHash) {
        this.userId = userId;
        this.tokenType = tokenType;
        this.expiresAt = expiresAt;
        this.tokenHash = tokenHash;
    }

    public long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user that is to be verified.
     * 
     * @param userId The valid user ID.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    /**
     * Sets the token of the verification.
     * 
     * @param token The verification's token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    /**
     * Sets the hash of the token.
     * 
     * @param tokenHash The hash of the token.
     */
    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    /**
     * Sets the type of the verification.
     * 
     * @param tokenType The token type.
     */
    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    /**
     * Sets the time when the verification expires.
     * 
     * @param expiresAt The time of the expiration.
     */
    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {}
}