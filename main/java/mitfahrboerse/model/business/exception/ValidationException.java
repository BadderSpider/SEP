package mitfahrboerse.model.business.exception;

/**
 * Thrown when a general business rule validation fails
 * (e.g., invalid input, missing data, or an item not found by ID).
 * Inherits from BusinessException.
 *
 * @author Anton Hollube
 */
public class ValidationException extends BusinessException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a specific error message.
     *
     * @param message The detail message.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Constructor with a specific error message and a cause.
     * Used to wrap a lower-level exception.
     *
     * @param message The detail message.
     * @param cause The cause, which is saved for later retrieval.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}