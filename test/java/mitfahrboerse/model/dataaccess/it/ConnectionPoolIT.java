package mitfahrboerse.model.dataaccess.it;

import mitfahrboerse.model.dataaccess.util.ConnectionPool;
import mitfahrboerse.model.dataaccess.util.DataAccessContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integrationstest für den ConnectionPool.
 * Basiert auf DatabaseTestBase für Docker-Umgebung.
 */
public class ConnectionPoolIT extends DatabaseTestBase {

    private final DataAccessContext context = DataAccessContext.DEFAULT;

    @Test
    @DisplayName("Pool sollte Singleton pro Context sein")
    void testSingletonPattern() {
        ConnectionPool pool1 = ConnectionPool.getInstance(context);
        ConnectionPool pool2 = ConnectionPool.getInstance(context);

        assertNotNull(pool1, "Pool Instanz darf nicht null sein");
        assertSame(pool1, pool2, "Es sollte exakt dieselbe Instanz zurückgegeben werden (Singleton)");
    }

    @Test
    @DisplayName("Sollte gültige Verbindung liefern und zurücknehmen")
    void testGetAndReleaseConnection() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance(context);

        // 1. Verbindung holen
        Connection conn = pool.getConnection();
        assertNotNull(conn, "Verbindung darf nicht null sein");
        assertFalse(conn.isClosed(), "Verbindung muss offen sein");
        assertTrue(conn.isValid(2), "Verbindung muss valide sein (Ping zur DB)");

        // 2. Verbindung zurückgeben
        pool.releaseConnection(conn);

        // 3. Prüfen, ob wir wieder eine Verbindung bekommen (Recycling)
        Connection conn2 = pool.getConnection();
        assertNotNull(conn2, "Sollte nach Release wieder eine Verbindung bekommen");

        // Cleanup
        pool.releaseConnection(conn2);
    }

    @Test
    @DisplayName("Pool sollte bei Erschöpfung null zurückgeben (Blockiereigenschaft testen)")
    void testPoolExhaustion() throws SQLException {
        // In DatabaseTestBase haben wir pool.size = 5 gesetzt
        int poolSize = 5;
        ConnectionPool pool = ConnectionPool.getInstance(context);
        List<Connection> borrowedConnections = new ArrayList<>();

        // 1. Leere den gesamten Pool
        for (int i = 0; i < poolSize; i++) {
            Connection c = pool.getConnection();
            assertNotNull(c, "Verbindung " + (i + 1) + " sollte verfügbar sein");
            borrowedConnections.add(c);
        }

        // 2. Versuche eine 6. Verbindung zu holen
        // Da die Implementierung poll() nutzt, erwarten wir sofort null (nicht blockierend)
        // oder ein Timeout, je nach Implementierung. Im Code war es poll(), also null.
        Connection extraConnection = pool.getConnection();
        assertNull(extraConnection, "Pool sollte leer sein und null zurückgeben");

        // 3. Eine Verbindung zurückgeben
        pool.releaseConnection(borrowedConnections.getFirst());
        borrowedConnections.removeFirst();

        // 4. Jetzt sollte wieder eine verfügbar sein
        Connection recycledConnection = pool.getConnection();
        assertNotNull(recycledConnection, "Nach release() sollte wieder eine Verbindung da sein");

        // Cleanup: Alle restlichen zurückgeben
        pool.releaseConnection(recycledConnection);
        for (Connection c : borrowedConnections) {
            pool.releaseConnection(c);
        }
    }

    @Test
    @DisplayName("Sollte korrekte Datenbank-Metadaten haben")
    void testConnectionMetadata() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance(context);
        Connection conn = pool.getConnection();

        try {
            String dbProductName = conn.getMetaData().getDatabaseProductName();
            // Wir erwarten PostgreSQL, da wir den entsprechenden Testcontainer nutzen
            assertEquals("PostgreSQL", dbProductName);

            // Optional: User prüfen (haben wir in DatabaseTestBase auf "testuser" gesetzt)
            String dbUser = conn.getMetaData().getUserName();
            assertEquals("testuser", dbUser);

        } finally {
            pool.releaseConnection(conn);
        }
    }
}
