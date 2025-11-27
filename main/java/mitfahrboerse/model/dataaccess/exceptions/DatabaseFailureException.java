package mitfahrboerse.model.dataaccess.exceptions;

/**
 * The base class for unchecked exceptions indicating a severe, likely unrecoverable database failure.
 * This exception and its subclasses signal critical system-level problems (e.g., loss of connectivity,
 * configuration errors) where the application cannot be expected to recover gracefully.
 *
 * @author Jonathan Schilcher
 */
public class DatabaseFailureException extends RuntimeException {

    /**
     * Constructs a new DatabaseFailureException with the specified detail message.
     * @param message The detail message describing the failure.
     */
    public DatabaseFailureException(String message) {
        super(message);
    }

    /**
     * Constructs a new DatabaseFailureException with the specified detail message and cause.
     * @param message The detail message describing the failure.
     * @param cause The underlying cause of this exception.
     */
    public DatabaseFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
