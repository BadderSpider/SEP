package mitfahrboerse.model.business.util;

import mitfahrboerse.model.business.exception.BusinessException;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A background thread that periodically executes maintenance tasks.
 * This class is now a CDI-Bean and gets its dependencies injected.
 *
 * @author Anton Hollube
 */

@Dependent 
public class MaintenanceThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(MaintenanceThread.class.getName());
    private static final long SLEEP_INTERVAL_MS = 1000 * 60 * 60 * 24; 

    @Inject
    private RideCleaner rideCleaner;

    @Inject
    private TokenCleaner tokenCleaner;

    @Inject
    private CreateMissingRecurringRides recurringRidesCreator;

    private volatile boolean running = true;

    /**
     * The main execution loop for the thread, calling tasks periodically.
     */
    @Override
    public void run() {

    }

    /**
     * Signals the thread to stop its execution loop.
     */
    public void stopThread() {
        running = false;
        this.interrupt(); 
    }

    /**
     * Starts the maintenance thread execution.
     */
    public void startThread() {
        running = true;
        this.start();
    }
}