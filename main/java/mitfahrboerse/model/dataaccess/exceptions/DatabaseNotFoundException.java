package mitfahrboerse.model.dataaccess.exceptions;

/**
 * An unchecked exception thrown when the application cannot connect to the database
 * because it is unavailable at the configured URL. This represents a critical
 * configuration or infrastructure problem that is not expected to be handled by the caller.
 *
 * @author Jonathan Schilcher
 */
public class DatabaseNotFoundException extends DatabaseFailureException {

    /**
     * Constructs a new DatabaseNotFoundException with the specified detail message.
     * @param message The detail message describing the failure.
     */
    public DatabaseNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new DatabaseNotFoundException with the specified detail message and cause.
     * @param message The detail message describing the failure.
     * @param cause The underlying cause of this exception.
     */
    public DatabaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
