package mitfahrboerse.model.dataaccess.util;

import mitfahrboerse.model.dataaccess.dao.*;
import mitfahrboerse.model.dataaccess.exceptions.DatabaseFailureException;
import mitfahrboerse.model.dataaccess.exceptions.TransactionException;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * A PostgreSQL-specific implementation of the {@link Transaction} interface.
 * It manages a single database connection from the pool, controls transaction boundaries
 * (commit, rollback), and acts as a factory for DAO instances that operate within its context.
 *
 * @author Jonathan Schilcher
 */
public final class PostgresqlTransaction implements Transaction {

    private static final Map<Class<?>, Class<?>> daoMappings = new HashMap<>();

    private final Connection connection;
    private final DataAccessContext context;
    private boolean isCommitted = false;

    static {
        registerDAOs();
    }

    private static void registerDAOs() {
        daoMappings.put(SettingDAO.class, SettingDAOPsql.class);
        daoMappings.put(UserDAO.class, UserDAOPsql.class);
        daoMappings.put(RideDAO.class, RideDAOPsql.class);
        daoMappings.put(RequestDAO.class, RequestDAOPsql.class);
        daoMappings.put(BookingDAO.class, BookingDAOPsql.class);
        daoMappings.put(OfferDAO.class, OfferDAOPsql.class);
    }

    /**
     * Creates a new PostgreSQL transaction. It acquires a connection from the pool
     * associated with the given context and disables auto-commit mode.
     * This call may block if no connections are available in the pool.
     *
     * @param context The data access context that determines which connection pool to use.
     * @throws TransactionException if setting auto-commit to false fails.
     */
    public PostgresqlTransaction(DataAccessContext context) {
        ConnectionPool pool = ConnectionPool.getInstance(context);
        this.connection = pool.getConnection();
        this.context = context;
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new TransactionException("Failed to disable auto-commit for new transaction.", e);
        }
    }

    @Override
    public void commit() {
        try {
            this.connection.commit();
            this.isCommitted = true;
        } catch (SQLException e) {
            throw new TransactionException("Failed to commit transaction", e);
        }
    }

    @Override
    public void rollback() {
        try {
            this.connection.rollback();
        } catch (SQLException e) {
            throw new TransactionException("Failed to rollback transaction", e);
        }
    }

    /**
     * Closes the transaction. If the transaction has not been explicitly committed,
     * it will be automatically rolled back. Finally, the underlying connection is
     * released back to the connection pool.
     */
    @Override
    public void close() {
        try {
            if (!isCommitted && !connection.isClosed()) {
                rollback();
            }
        } catch (SQLException | TransactionException e) {
            System.err.println("Error during automatic rollback on close: " + e.getMessage());
        }
        finally {
            try {
                if (!connection.isClosed()) {
                    ConnectionPool.getInstance(this.context).releaseConnection(this.connection);
                }
            } catch (SQLException e) {
                throw new DatabaseFailureException("Failed to check if connection is closed before releasing it.", e);
            }
        }
    }

    /**
     * Gets a DAO instance that operates within this transaction.
     * It uses reflection to instantiate the correct DAO implementation for the requested interface.
     *
     * @param daoInterface The interface of the desired DAO (e.g., UserDAO.class).
     * @param <T> The type of the DAO interface.
     * @return An instance of the requested DAO, bound to this transaction's connection.
     * @throws DatabaseFailureException if no implementation is registered or instantiation fails.
     */
    @Override
    public <T> T getDAO(Class<T> daoInterface) {
        Class<?> implClass = daoMappings.get(daoInterface);
        if (implClass == null) {
            throw new DatabaseFailureException("No DAO implementation registered for interface: " + daoInterface.getName());
        }

        try {
            Constructor<?> constructor = implClass.getConstructor(Connection.class);
            Object daoInstance = constructor.newInstance(this.connection);
            return daoInterface.cast(daoInstance);
        } catch (Exception e) {
            throw new DatabaseFailureException("Could not create DAO instance for: " + daoInterface.getName(), e);
        }
    }
}