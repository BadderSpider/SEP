package mitfahrboerse.model.dataaccess.util;

/**
 * Defines the available data access contexts for the application.
 * Each context links a logical name to a specific {@link Transaction} implementation and
 * a configuration block in the properties file.
 *
 * @author Jonathan Schilcher
 */
public enum DataAccessContext {

    /**
     * The default context for the primary PostgreSQL database.
     */
    DEFAULT("postgresql", PostgresqlTransaction.class);

    private final String dbName;
    private final Class<? extends Transaction> transactionType;

    /**
     * Constructor for a DataAccessContext enum value.
     *
     * @param dbName The name of the database configuration in the properties file (e.g., "postgresql").
     * @param transactionType The corresponding Transaction implementation class.
     */
    DataAccessContext(String dbName, Class<? extends Transaction> transactionType) {
        this.dbName = dbName;
        this.transactionType = transactionType;
    }

    /**
     * Returns the name of the associated database configuration.
     * This is used as a key to look up settings in the properties file.
     *
     * @return The database configuration name.
     */
    String getDbName() {
        return dbName;
    }

    /**
     * Returns the class of the associated {@link Transaction} implementation.
     * This is used by the TransactionFactory to instantiate the correct transaction type.
     *
     * @return The Transaction implementation class.
     */
    Class<? extends Transaction> getTransactionType() {
        return transactionType;
    }
}
