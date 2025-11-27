package mitfahrboerse.model.presentation.exceptionhandler;

/**
 * Unchecked exception thrown when a request parameter from a URL is invalid.
 * Caught by the CustomExceptionHandler.
 *
 * @author Anton Hollube
 */
public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a specific error message.
     *
     * @param message The detail message.
     */
    public InvalidRequestException(String message) {
        super(message);
    }

}