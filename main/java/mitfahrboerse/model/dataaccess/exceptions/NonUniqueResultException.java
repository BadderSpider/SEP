package mitfahrboerse.model.dataaccess.exceptions;

/**
 * A checked exception thrown when a query was expected to return a single,
 * unique result, but it returned multiple results. This often indicates
 * a data integrity problem or a flawed query.
 *
 * @author Jonathan Schilcher
 */
public class NonUniqueResultException extends DataAccessException {

    /**
     * Constructs a new NonUniqueResultException with the specified detail message.
     * @param message The detail message describing the error.
     */
    public NonUniqueResultException(String message) {
        super(message);
    }

    /**
     * Constructs a new NonUniqueResultException with the specified detail message and cause.
     * @param message The detail message describing the error.
     * @param cause The underlying cause of this exception.
     */
    public NonUniqueResultException(String message, Throwable cause) {
        super(message, cause);
    }
}
