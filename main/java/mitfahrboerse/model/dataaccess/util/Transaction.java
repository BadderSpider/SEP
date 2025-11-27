package mitfahrboerse.model.dataaccess.util;

import mitfahrboerse.model.dataaccess.exceptions.TransactionException;

/**
 * Defines the contract for a database transaction.
 * This interface allows for grouping multiple database operations into a single atomic unit.
 * It is designed to be used with a try-with-resources statement to ensure that the
 * transaction is always closed and the connection is released.
 *
 * @author Jonathan Schilcher
 */
public interface Transaction extends AutoCloseable {

    /**
     * Commits all changes made within this transaction to the database, making them permanent.
     *
     * @throws TransactionException if the commit fails.
     */
    void commit();

    /**
     * Discards all changes made within this transaction, reverting the database to its
     * state at the beginning of the transaction.
     *
     * @throws TransactionException if the rollback fails.
     */
    void rollback();

    /**
     * Ends the transaction. If it has not been committed, a rollback is performed automatically.
     * This method releases the underlying database connection back to the pool.
     */
    @Override
    void close();

    /**
     * Provides a specific DAO instance that operates within the context of this transaction.
     * All operations performed with the returned DAO will be part of this single transaction.
     *
     * @param daoInterface The interface of the desired DAO (e.g., UserDAO.class).
     * @param <T> The type of the DAO interface.
     * @return An instance of the requested DAO.
     */
    <T> T getDAO(Class<T> daoInterface);
}
