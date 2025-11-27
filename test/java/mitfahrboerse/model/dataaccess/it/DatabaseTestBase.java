package mitfahrboerse.model.dataaccess.it;

import mitfahrboerse.model.dataaccess.util.LifecycleDataManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Properties;

public abstract class DatabaseTestBase {

    @BeforeAll
    static void globalSetup() throws IOException, SQLException {
        String dbUrl = System.getenv("TEST_DB_URL");
        String dbUser = System.getenv("TEST_DB_USER");
        String dbPassword = System.getenv("TEST_DB_PASSWORD");

        // Fallback für lokales Ausführen in der IDE (ohne Docker)
        if (dbUrl == null) {
            dbUrl = "jdbc:postgresql://localhost:5432/mitfahrboerse_test";
            dbUser = "testuser";
            dbPassword = "testpass";
        }

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            boolean schemaExists = false;
            try (ResultSet rs = conn.getMetaData().getTables(null, null, "t_user", null)) {
                if (rs.next()) {
                    schemaExists = true;
                }
            }

            if (!schemaExists) {
                try (Statement stmt = conn.createStatement()) {
                    // Sicherheitshalber aufräumen, falls Fragmente da sind
                    stmt.execute("DROP SCHEMA public CASCADE; CREATE SCHEMA public; GRANT ALL ON SCHEMA public TO public;");

                    String schemaSql = loadSchemaSQL();
                    stmt.execute(schemaSql);
                }
            }
        }

        // 3. Config für die Applikation fälschen
        File tempConfigFile = createTempPropertyFile(dbUrl, dbUser, dbPassword);

        // 4. Applikation starten
        LifecycleDataManager.onStartup(tempConfigFile.getAbsolutePath());
    }

    @AfterAll
    static void globalTeardown() {
        LifecycleDataManager.onShutdown();
    }

    /**
     * Erstellt eine temporäre Properties-Datei, damit ConfigReader
     * die Verbindung zum Testcontainer findet.
     */
    private static File createTempPropertyFile(String url, String user, String pw) throws IOException {
        Properties props = new Properties();
        props.setProperty("db.postgresql.url", url);
        props.setProperty("db.postgresql.user", user);
        props.setProperty("db.postgresql.password", pw);
        props.setProperty("db.postgresql.pool.size", "5");
        props.setProperty("default.context", "DEFAULT");

        File tempFile = File.createTempFile("db-test", ".properties");
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            props.store(out, "Dynamic Test Configuration");
        }
        return tempFile;
    }

    /**
     * Lädt das Schema SQL.
     * Pfad basierend auf deinem Screenshot: src/main/resources/sql/schema.sql
     * Im Classpath ist das dann: "sql/schema.sql"
     */
    private static String loadSchemaSQL() throws IOException {
        // WICHTIG: Kein führender Slash bei getResourceAsStream relativ zum Classloader
        String schemaPath = "sql/schema.sql";

        try (InputStream resourceStream = DatabaseTestBase.class.getClassLoader().getResourceAsStream(schemaPath)) {
            if (resourceStream == null) {
                throw new IOException("Schema file not found in Classpath at: '" + schemaPath + "'. " +
                        "Checked path based on src/main/resources/sql/schema.sql");
            }
            return new String(resourceStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}