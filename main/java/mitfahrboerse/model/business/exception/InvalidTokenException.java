package mitfahrboerse.model.business.exception;

/**
 * Thrown when a verification or password reset token is invalid, expired, or not found.
 * Inherits from BusinessException.
 *
 * @author Anton Hollube
 */
public class InvalidTokenException extends BusinessException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a specific error message.
     *
     * @param message The detail message.
     */
    public InvalidTokenException(String message) {
        super(message);
    }

    /**
     * Constructor with a specific error message and a cause.
     * Used to wrap a lower-level exception.
     *
     * @param message The detail message.
     * @param cause The cause, which is saved for later retrieval.
     */
    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}