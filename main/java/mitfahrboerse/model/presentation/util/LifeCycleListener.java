package mitfahrboerse.model.presentation.util;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import mitfahrboerse.model.business.exception.BusinessException;
import mitfahrboerse.model.business.util.LifeCycleBusinessManager;

/**
 * WebApplication Listener that is responsible for the application's startup and shutdown.
 *
 * @author René Schmaderer
 */
@ApplicationScoped
@Named
@WebListener
public class LifeCycleListener implements ServletContextListener {

    @Inject LifeCycleBusinessManager lifeCycleBusinessManager;
    /**
     * {@inheritDoc}
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            lifeCycleBusinessManager.start();
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}