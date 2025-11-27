package mitfahrboerse.WeldTestBase;

import mitfahrboerse.model.presentation.util.OwnershipChecker;
import mitfahrboerse.model.business.service.AuthenticationService;
import mitfahrboerse.model.business.util.LifeCycleBusinessManager;
import mitfahrboerse.model.dataaccess.dao.UserDAO;
import mitfahrboerse.model.dataaccess.util.LifecycleDataManager;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.view.ViewScoped;

/**
 * Basisklasse für alle Integrationstests mit Weld.
 * Die Annotation @EnableWeld startet den CDI-Container für jede Testklasse.
 */
@EnableWeld
public abstract class WeldTestBase {

    /**
     * Konfiguriert den Weld-Container.
     * Hier aktivieren wir die Standard-Scopes, die in einer Web-App üblich sind.
     * * WICHTIG: Standardmäßig scannt Weld-JUnit ALLES im Classpath.
     * Das ist oft gut, kann aber bei Datenbank-Klassen zu Problemen führen, 
     * wenn keine echte DB da ist.
     */
    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(WeldInitiator.createWeld()
            // 1. WICHTIG: Explizit die Pakete scannen!
            // .addPackages(true, Klasse.class) scannt das Paket der Klasse rekursiv (true).
            
            // Scannt alles unter model.presentation.util (da liegt OwnershipChecker & UserSessionBean)
            .addPackages(true, OwnershipChecker.class)

            // Scannt alles unter model.business.service (für spätere Service-Tests)
            .addPackages(true, AuthenticationService.class)
            
    		// Business Layer (Services, Utils/Manager)
            .addPackages(true, AuthenticationService.class)
            .addPackages(true, LifeCycleBusinessManager.class)
            
            // Data Access Layer (DAOs, Transaction, Utils)
            // Wichtig, da Business-Manager oft DAOs injecten
            .addPackages(true, UserDAO.class)
            .addPackages(true, LifecycleDataManager.class))
            // 2. Scopes aktivieren
            .activate(RequestScoped.class, SessionScoped.class, ApplicationScoped.class, ViewScoped.class)
            .build();
}