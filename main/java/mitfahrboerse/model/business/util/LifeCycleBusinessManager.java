package mitfahrboerse.model.business.util;

import jakarta.inject.Named;
import mitfahrboerse.model.business.exception.BusinessException;
import mitfahrboerse.model.dataaccess.util.LifecycleDataManager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mitfahrboerse.model.business.exception.BusinessException;

/**
 * Service class that is called by the LifecycleListener of the presentation layer.
 * This is an ApplicationScoped CDI-Bean that manages the lifecycle.
 *
 * @author Anton Hollube
 */
@ApplicationScoped
@Named
public class LifeCycleBusinessManager {
	
    @Inject
    private MaintenanceThread maintenanceThread;
    
    //LifeCycleDataManager Inject was removed because class is static. Use LifeCycleDataManager.onStartup() etc.
    

    /**
     * Called on application startup. Initializes business-layer resources.
     *
     * @throws BusinessException If a required resource cannot be initialized.
     */
    public void start() throws BusinessException {
    }

    /**
     * Called on application shutdown. Shuts down business-layer resources.
     *
     * @throws BusinessException If a resource cannot be shut down.
     */
    public void stop() throws BusinessException {
    }
}