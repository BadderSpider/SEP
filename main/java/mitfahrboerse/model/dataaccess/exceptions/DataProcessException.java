package mitfahrboerse.model.dataaccess.exceptions;

/**
 * An unchecked exception representing a critical, unexpected error during the processing
 * of data, such as mapping a ResultSet to a DTO. This typically indicates a programming
 * error (e.g., a mismatch between the DTO and the database schema).
 *
 * @author Jonathan Schilcher
 */
public class DataProcessException extends DatabaseFailureException {

    /**
     * Constructs a new DataProcessException with the specified detail message.
     * @param message The detail message describing the processing failure.
     */
    public DataProcessException(String message) {
        super(message);
    }

    /**
     * Constructs a new DataProcessException with the specified detail message and cause.
     * @param message The detail message describing the processing failure.
     * @param cause The underlying cause of this exception.
     */
    public DataProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
