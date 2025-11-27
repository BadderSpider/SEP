package mitfahrboerse.model.dataaccess.exceptions;

/**
 * An unchecked exception thrown when a critical error occurs during transaction management,
 * such as a failure to commit or roll back a transaction. This indicates a severe problem
 * with the transactional integrity of the data source and is not expected to be recovered from.
 *
 * @author Jonathan Schilcher
 */
public class TransactionException extends DatabaseFailureException {

    /**
     * Constructs a new TransactionException with the specified detail message.
     * @param message The detail message describing the transaction failure.
     */
    public TransactionException(String message) {
        super(message);
    }

    /**
     * Constructs a new TransactionException with the specified detail message and cause.
     * @param message The detail message describing the transaction failure.
     * @param cause The underlying cause of this exception.
     */
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
