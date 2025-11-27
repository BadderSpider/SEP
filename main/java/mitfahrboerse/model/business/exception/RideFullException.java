package mitfahrboerse.model.business.exception;

/**
 * Thrown when a user attempts to book a ride that has no available seats.
 * Inherits from BusinessException.
 *
 * @author Anton Hollube
 */
public class RideFullException extends BusinessException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a specific error message.
     *
     * @param message The detail message.
     */
    public RideFullException(String message) {
        super(message);
    }

    /**
     * Constructor with a specific error message and a cause.
     * Used to wrap a lower-level exception.
     *
     * @param message The detail message.
     * @param cause The cause, which is saved for later retrieval.
     */
    public RideFullException(String message, Throwable cause) {
        super(message, cause);
    }
}