package mitfahrboerse.model.business.exception;

/**
 * Thrown when a user attempts to register an email that already exists.
 * Inherits from BusinessException.
 *
 * @author Anton Hollube
 */
public class UserExistsException extends BusinessException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a specific error message.
     *
     * @param message The detail message.
     */
    public UserExistsException(String message) {
        super(message);
    }

    /**
     * Constructor with a specific error message and a cause.
     * Used to wrap a lower-level exception (e.g., a DataAccessException).
     *
     * @param message The detail message.
     * @param cause The cause, which is saved for later retrieval.
     */
    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}