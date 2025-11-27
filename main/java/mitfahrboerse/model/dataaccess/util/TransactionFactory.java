package mitfahrboerse.model.dataaccess.util;

import java.lang.reflect.Constructor;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * A factory for creating {@link Transaction} objects.
 * It abstracts the instantiation of specific transaction implementations (e.g., {@link PostgresqlTransaction})
 * based on the provided {@link DataAccessContext}.
 *
 * @author Jonathan Schilcher
 */
public final class TransactionFactory {

    private static TransactionFactory instance;
    private final DataAccessContext defaultContext;

    private TransactionFactory(DataAccessContext context) {
        this.defaultContext = context;
    }

    /**
     * Gets the singleton instance of the TransactionFactory.
     *
     * @return The singleton instance.
     */
    public static synchronized TransactionFactory getInstance() {
        if (instance == null) {
            // The default context is determined by the ConfigReader.
            instance = new TransactionFactory(ConfigReader.getInstance().getDefaultContext());
        }
        return instance;
    }

    /**
     * Starts a new transaction using the application's default data access context.
     *
     * @return A new Transaction object ready for use.
     */
    public Transaction startTransaction() {
        return startTransaction(defaultContext);
    }

    /**
     * Starts a new transaction for a specific data access context.
     * It uses reflection to create an instance of the transaction class defined in the context.
     *
     * @param context The data access context to use.
     * @return A new Transaction object.
     */
    public Transaction startTransaction(DataAccessContext context) {
        try {
            Class<? extends Transaction> transactionClass = context.getTransactionType();
            Constructor<? extends Transaction> constructor = transactionClass.getConstructor(
                    DataAccessContext.class
            );
            return constructor.newInstance(context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}