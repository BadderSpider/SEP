package mitfahrboerse.model.dataaccess.exceptions;

/**
 * The base class for checked exceptions related to data access operations.
 * This type of exception should be thrown for potentially recoverable errors
 * or situations that the calling code should be aware of and handle explicitly
 * (e.g., an entry was not found, or a constraint violation occurred).
 *
 * @author Jonathan Schilcher
 */
public class DataAccessException extends Exception {

    /**
     * Constructs a new DataAccessException with the specified detail message.
     * @param message The detail message describing the error.
     */
    public DataAccessException(String message) {
        super(message);
    }

    /**
     * Constructs a new DataAccessException with the specified detail message and cause.
     * @param message The detail message describing the error.
     * @param cause The underlying cause of this exception (e.g., a SQLException).
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
