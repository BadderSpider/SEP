package mitfahrboerse.model.dataaccess.exceptions;

/**
 * A checked exception thrown when a query was expected to return at least one result
 * (e.g., finding an entity by its primary key) but returned none.
 *
 * @author Jonathan Schilcher
 */
public class NoResultException extends DataAccessException {

    /**
     * Constructs a new NoResultException with the specified detail message.
     * @param message The detail message describing the error.
     */
    public NoResultException(String message) {
        super(message);
    }

    /**
     * Constructs a new NoResultException with the specified detail message and cause.
     * @param message The detail message describing the error.
     * @param cause The underlying cause of this exception.
     */
    public NoResultException(String message, Throwable cause) {
        super(message, cause);
    }
}
