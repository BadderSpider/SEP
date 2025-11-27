package mitfahrboerse.model.dataaccess.util;


import mitfahrboerse.model.dataaccess.exceptions.DatabaseFailureException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A singleton that reads and provides database configurations from a properties file.
 * It must be initialized once on application startup using the {@link #initialize(String)} method.
 * The class caches database configurations to avoid repeated file access.
 *
 * @author Jonathan Schilcher
 */
public final class ConfigReader {

    private static ConfigReader instance;
    private final Properties properties;

    private final Map<DataAccessContext, ConnectionConfig> dbConfigCache = new ConcurrentHashMap<>();

    private ConfigReader(String configFileName) {
        this.properties = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream(configFileName);
            if (input == null) {
                input = new FileInputStream(configFileName);
            }
            properties.load(input);
        } catch (Exception ex) {
            throw new DatabaseFailureException("Could not load configuration file '" + configFileName + "'.", ex);
        }
    }

    /**
     * Initializes the singleton ConfigReader instance with a specific configuration file.
     * This method must be called once before {@link #getInstance()} is used.
     *
     * @param configFileName The name of the properties file to load from the classpath.
     */
    public static synchronized void initialize(String configFileName) {
        if (instance == null) {
            instance = new ConfigReader(configFileName);
        }
    }

    /**
     * Gets the singleton instance of the ConfigReader.
     *
     * @return The singleton instance.
     * @throws DatabaseFailureException if the reader has not been initialized first.
     */
    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            throw new DatabaseFailureException("ConfigReader has not been initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Retrieves the database connection configuration for a given data access context.
     * The configuration is read from the properties file and cached for subsequent requests.
     *
     * @param context The data access context (e.g., DEFAULT).
     * @return The {@link ConnectionConfig} for the specified context.
     * @throws DatabaseFailureException if required properties for the context are missing.
     */
    public ConnectionConfig getDbConfiguration(DataAccessContext context) {
        return dbConfigCache.computeIfAbsent(context, k -> {
            String dbName = k.getDbName();
            String prefix = "db." + dbName;

            String url = getProperty(prefix + ".url");
            String user = getProperty(prefix + ".user");
            String password = getProperty(prefix + ".password");
            int poolSize = Integer.parseInt(getProperty(prefix + ".pool.size", "10"));

            if (url == null || url.isBlank()) {
                throw new DatabaseFailureException("Database URL for context '" + k + "' is not configured in properties file.");
            }
            return new ConnectionConfig(url, user, password, poolSize);
        });
    }

    /**
     * Retrieves the default data access context specified in the properties file.
     *
     * @return The default DataAccessContext (e.g., `DataAccessContext.DEFAULT`).
     * @throws DatabaseFailureException if the default context name is not a valid enum value.
     */
    public DataAccessContext getDefaultContext() {
        String defaultContextName = getProperty("default.context", "DEFAULT");
        try {
            return DataAccessContext.valueOf(defaultContextName);
        } catch (IllegalArgumentException e) {
            throw new DatabaseFailureException("Default context '" + defaultContextName + "' is not a valid DataAccessContext enum value.", e);
        }
    }

    private String getProperty(String key) { return properties.getProperty(key); }
    private String getProperty(String key, String defaultValue) { return properties.getProperty(key, defaultValue); }
}
