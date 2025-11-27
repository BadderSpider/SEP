package mitfahrboerse.model.presentation.exceptionhandler;

/**
 * Unchecked exception thrown by the TrespassListener when a user
 * attempts to access an unauthorized resource.
 * Caught by the CustomExceptionHandler.
 *
 * @author Anton Hollube
 */
public class UserNotAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a specific error message.
     *
     * @param message The detail message.
     */
    public UserNotAuthorizedException(String message) {
        super(message);
    }

}