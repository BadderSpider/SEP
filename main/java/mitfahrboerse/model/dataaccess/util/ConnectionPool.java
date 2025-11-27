package mitfahrboerse.model.dataaccess.util;

import mitfahrboerse.model.dataaccess.exceptions.DatabaseFailureException;
import mitfahrboerse.model.dataaccess.exceptions.DatabaseNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Manages a pool of database connections for a specific data access context.
 * Pooling enhances performance by reusing existing connections, avoiding the overhead
 * of establishing a new connection for every request. Each context has its own singleton pool.
 *
 * @author Jonathan Schilcher
 */
public final class ConnectionPool {

    private static final Map<DataAccessContext, ConnectionPool> instances = new ConcurrentHashMap<>();

    private final BlockingQueue<Connection> availableConnections;
    private final List<Connection> allConnections;

    private ConnectionPool(ConnectionConfig config) {
        this.availableConnections = new LinkedBlockingQueue<>(config.poolSize());
        this.allConnections = new CopyOnWriteArrayList<>();

        // Pre-populate the pool with new connections.
        for (int i = 0; i < config.poolSize(); i++) {
            try {
                Connection connection = DriverManager.getConnection(config.url(), config.user(), config.password());
                availableConnections.add(connection);
                allConnections.add(connection);
            } catch (SQLException e) {
                throw new DatabaseNotFoundException("Failed to create initial database connection for pool. Check DB status and connection URL.", e);
            }
        }
    }

    /**
     * Gets the singleton instance of the connection pool for a given data access context.
     * If the pool does not exist for the context, it is created and initialized.
     *
     * @param context The data access context.
     * @return The singleton ConnectionPool instance for that context.
     */
    public static ConnectionPool getInstance(DataAccessContext context) {
        return instances.computeIfAbsent(context, k -> {
            ConnectionConfig config = ConfigReader.getInstance().getDbConfiguration(k);
            return new ConnectionPool(config);
        });
    }

    /**
     * Retrieves a database connection from the pool.
     * If no connection is available, this method may return null or block, depending on the queue implementation.
     *
     * @return A database connection, or null if the pool is empty and non-blocking.
     */
    public Connection getConnection() {
        return availableConnections.poll();
    }

    /**
     * Returns a connection to the pool, making it available for reuse.
     * If the connection is closed or invalid, it is discarded.
     *
     * @param connection The connection to release back to the pool.
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                if (connection.isClosed()) {
                    // If the connection was closed externally, remove it from the master list.
                    allConnections.remove(connection);
                    return;
                }
                availableConnections.put(connection);
            } catch (Exception e) {
                throw new DatabaseFailureException("Failed to release connection back to pool", e);
            }
        }
    }

    /**
     * Closes all connections within this specific pool instance.
     */
    public void close() {
        for (Connection connection : allConnections) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Log error but continue trying to close other connections.
                System.err.println("Failed to close a connection during pool shutdown: " + e.getMessage());
            }
        }
        allConnections.clear();
        availableConnections.clear();
    }

    /**
     * Closes all connection pools managed by the application.
     * This should be called on application shutdown to release all database resources.
     */
    public static void closeAll() {
        instances.values().forEach(ConnectionPool::close);
        instances.clear();
    }
}
