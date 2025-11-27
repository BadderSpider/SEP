package mitfahrboerse.model.business.exception;

/**
 * Base exception for all checked business logic errors.
 * These must be handled by the presentation layer (SafeActionBean).
 *
 * @author Anton Hollube
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BusinessException() {
        super();
    }

    /**
     * Constructor with a specific error message.
     *
     * @param message The detail message.
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructor with a specific error message and a cause.
     *
     * @param message The detail message.
     * @param cause The cause, which is saved for later retrieval.
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}