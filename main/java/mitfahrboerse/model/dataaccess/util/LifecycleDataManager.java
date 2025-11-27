package mitfahrboerse.model.dataaccess.util;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * A utility class to manage the lifecycle of the data access layer.
 * It provides centralized hooks for application startup and shutdown procedures.
 *
 * @author Jonathan Schilcher
 */
@ApplicationScoped //Was added for CDI Injection. TODO: Check if correctly scoped
public final class LifecycleDataManager {

    /**
     * Initializes the data access layer. This method should be called once on application startup.
     * Its primary responsibility is to initialize the {@link ConfigReader}.
     *
     * @param configFile The name of the configuration properties file (e.g., "db.properties").
     */
    public static void onStartup(String configFile) {

        try {
            ConfigReader.initialize(configFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Shuts down the data access layer. This method should be called once on application shutdown.
     * It ensures that all database connection pools are properly closed, releasing all resources.
     */
    public static void onShutdown() {
        try {
            ConnectionPool.closeAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
