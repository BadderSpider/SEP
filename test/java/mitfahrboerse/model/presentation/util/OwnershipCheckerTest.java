package mitfahrboerse.model.presentation.util;

import jakarta.inject.Inject;
import mitfahrboerse.WeldTestBase.WeldTestBase; 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OwnershipCheckerTest extends WeldTestBase {

    // Wir lassen Weld diese Bean erstellen und injizieren
    @Inject
    OwnershipChecker ownershipChecker;

    // Wir injizieren auch die SessionBean, um zu prüfen, ob sie da ist
    @Inject
    UserSessionBean userSessionBean;

    @Test
    void testContainerStartupAndInjection() {
        // 1. Prüfen: Wurde der OwnershipChecker injiziert?
        assertNotNull(ownershipChecker, "Der OwnershipChecker sollte von Weld injiziert worden sein.");

        // 2. Prüfen: Wurde die UserSessionBean (die im Checker steckt) auch erstellt?
        assertNotNull(userSessionBean, "Die UserSessionBean sollte existieren.");
        
        // 3. Einen Methodenaufruf testen (auch wenn Logik noch leer ist, darf es nicht crashen)
        // Standardmäßig ist userId im SessionBean 0
        boolean result = ownershipChecker.validateOwnership(999);
        
        // Da du noch keine Mock-Daten hast und userId 0 ist, erwarten wir false (oder was deine Logik hergibt)
        assertFalse(result, "Sollte false sein, da User ID 0 nicht 999 ist.");
    }
}