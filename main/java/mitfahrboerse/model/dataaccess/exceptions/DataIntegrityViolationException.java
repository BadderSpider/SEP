package mitfahrboerse.model.dataaccess.exceptions;

/**
 * A checked exception thrown when an attempted operation violates a database
 * integrity constraint, such as a foreign key constraint or a check constraint.
 *
 * @author Jonathan Schilcher
 */
public class DataIntegrityViolationException extends DataAccessException {

    /**
     * Constructs a new DataIntegrityViolationException with the specified detail message.
     * @param message The detail message describing the violation.
     */
    public DataIntegrityViolationException(String message) {
        super(message);
    }

    /**
     * Constructs a new DataIntegrityViolationException with the specified detail message and cause.
     * @param message The detail message describing the violation.
     * @param cause The underlying cause of this exception.
     */
    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
