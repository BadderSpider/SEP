package mitfahrboerse.model.business.exception;

/**
 * Thrown when a user attempts an action that is not permitted by business rules
 * (e.g., cancelling a ride after the deadline or editing a completed ride).
 * Inherits from BusinessException.
 *
 * @author Anton Hollube
 */
public class OperationNotAllowedException extends BusinessException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a specific error message.
     *
     * @param message The detail message.
     */
    public OperationNotAllowedException(String message) {
        super(message);
    }

    /**
     * Constructor with a specific error message and a cause.
     * Used to wrap a lower-level exception.
     *
     * @param message The detail message.
     * @param cause The cause, which is saved for later retrieval.
     */
    public OperationNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}