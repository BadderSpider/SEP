package mitfahrboerse.global.dto;

/**
 * Data Transfer Object for error details.
 * 
 * @author Anton Hollube
 */
public class DynamicErrorDTO {

    // Only used by the view
    private String errorCode;
    private String message;

    // --- GETTER ---

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    // --- SETTER ---

    /**
     * Sets the error code (e.g., "404").
     * @param value The error code.
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Sets the user-friendly error message.
     * @param value The error message.
     */
    public void setMessage(String value) {
        this.message = value;
    }
}