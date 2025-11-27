package mitfahrboerse.model.dataaccess.util;

/**
 * A data record holding configuration parameters for a database connection.
 *
 * @param url The JDBC URL of the database.
 * @param user The username for the database connection.
 * @param password The password for the database connection.
 * @param poolSize The number of connections to maintain in the pool.
 *
 * @author Jonathan Schilcher
 */
public record ConnectionConfig(String url, String user, String password, int poolSize) {}
