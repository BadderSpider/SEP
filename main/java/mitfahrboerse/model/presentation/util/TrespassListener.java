package mitfahrboerse.model.presentation.util;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

/**
 * Implements a JSF {@link PhaseListener} to check user authorization on view access
 * *before* the view is rendered (PhaseId.RESTORE_VIEW).
 *
 * This listener implements the **role-based security** (coarse-grained).
 * It checks if a user's role (ANONYMOUS, USER, ADMIN) is sufficient
 * to access a page based on its URL path (e.g., /registered/*, /admin/*).
 *
 * @author Anton Hollube
 */
public class TrespassListener implements PhaseListener {

    private static final long serialVersionUID = 1L;

    // --- Protected Page Prefixes ---
    private static final String ADMIN_PAGES_PREFIX = "/view/admin/";
    private static final String REGISTERED_PAGES_PREFIX = "/view/registered/";

    // --- Public Page Prefixes ---
    private static final String ANONYMOUS_PAGES_PREFIX = "/view/anonymous/";
    
    // --- Public Pages ---
    private static final String RIDES_PAGE = "/rides.xhtml"; 
    private static final String REQUESTS_PAGE = "/requests.xhtml";


    /**
     * Logic to execute *after* the RESTORE_VIEW phase.
     * This is where the security check happens.
     * @param event The JSF PhaseEvent.
     */
    @Override
    public void afterPhase(PhaseEvent event) {
    }

    /**
     * Logic to execute before the specified phase.
     *
     * @param event The JSF PhaseEvent.
     */
    @Override
    public void beforePhase(PhaseEvent event) {
    }

    /**
     * Helper method to redirect an unauthorized user to the login page.
     *
     * @param context The current FacesContext.
     */
    private void redirectToLogin(FacesContext context) {
    	
    }
    
    /**
     * Specifies which phase this listener should execute on.
     * RESTORE_VIEW is the first phase.
     *
     * @return The PhaseId (RESTORE_VIEW).
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
        }
}