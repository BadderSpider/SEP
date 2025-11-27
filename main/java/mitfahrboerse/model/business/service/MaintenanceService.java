package mitfahrboerse.model.business.service;

import mitfahrboerse.model.business.exception.BusinessException;

/**
 * Manages background tasks (e.g., deleting old rides, publishing recurring rides).
 * This service is the static entry point for starting and stopping background
 * maintenance tasks (e.g., via the `LifeCycleListener`).
 *
 * @author Anton Hollube
 */
public class MaintenanceService {

    private MaintenanceService() {
    }

    /**
     * Starts the background maintenance thread or scheduler.
     * This method should be called once at application startup (e.g., by LifeCycleListener)
     * to begin the periodic execution of cleanup tasks.heduler.
     */
    public static void startMaintenanceScheduler() {
    }

    /**
     * Stops the background maintenance thread or scheduler gracefully.
     * This method should be called once at application shutdown to ensure a
     * graceful termination of background processes.
     */
    public static void stopMaintenanceScheduler() {
    }

    /**
    * Executes all scheduled maintenance tasks (e.g., RideCleaner, TokenCleaner) once.
     * This provides a way to manually trigger the cleanup tasks (e.g., for testing
     * or an admin button) without waiting for the scheduler.once.
     *
     * @throws BusinessException If a data access error occurs during task execution.
     */
    public static void runScheduledTasks() throws BusinessException {
    }
}