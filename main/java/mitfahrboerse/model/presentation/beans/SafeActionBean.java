package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.model.business.exception.BusinessException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.util.concurrent.Callable;

/**
 * Abstract base class for backing beans that execute "safe" actions.
 * Its purpose is to provide a central mechanism for executing business logic,
 * automatically catching any thrown {@link BusinessException} (or other exceptions),
 * and displaying them as a user-friendly global FacesMessage.
 *
 * @author Anton Hollube
 */
public abstract class SafeActionBean {
    /**
     * A functional interface for actions that throw a BusinessException
     * but do not return a value (e.g., save, delete, update).
     */
    @FunctionalInterface
    static interface VoidAction {
        void run() throws BusinessException;
    }

    /**
     * Executes an action that returns a value (e.g., getRide, findRides).
     * It catches BusinessExceptions and other Exceptions, displays them as a
     * global FacesMessage, and returns null on failure.
     *
     * @param <T>            The return type of the action (e.g., RideDTO, List<RideDTO>).
     * @param action         The lambda (as a Callable) containing the business logic.
     * @param successMessage The success message to show (optional, can be null).
     * @return The result of the action (T) on success, or null on failure.
     */
    protected <T> T safeAction(Callable<T> action, String successMessage) {
        try {
            // Execute the provided lambda action
            T result = action.call();

            // If a success message is provided, show it
            if (successMessage != null && !successMessage.isEmpty()) {
                addGlobalInfoMessage("Success", successMessage);
            }

            return result;

        } catch (BusinessException e) {
            // Catch the expected BusinessException (e.g., "Ride is full")
            handleException(e);
            return null; // Always return null on failure
        } catch (Exception e) {
            // Catch unexpected RuntimeExceptions (e.g., NullPointerException)
            handleException(new BusinessException("An unexpected system error occurred.", e));
            return null;
        }
    }
    
    /**
     * Executes an action that returns a value (e.g., getRide, findRides).
     * Overloaded version without a success message.
     */
    protected <T> T safeAction(Callable<T> action) {
        return safeAction(action, null);
    }

    /**
     * Executes a void action (e.g., save, delete, update).
     * Catches BusinessExceptions and displays them as a global FacesMessage.
     *
     * @param action The lambda (as a VoidAction) containing the business logic.
     * @param successMessage The success message to show (optional, can be null).
     * @return true on success, false on failure.
     */
    protected boolean safeAction(VoidAction action) {
        try {
            action.run();
            return true; // Erfolg

        } catch (BusinessException e) {
            handleException(e);
            return false; 
        } catch (Exception e) {
            handleException(new BusinessException("Ein unerwarteter Systemfehler ist aufgetreten.", e));
            return false;
        }
    }

    /**
     * Handles a caught exception by displaying it as a global FacesMessage.
     * This is the central point for formatting error messages.
     *
     * @param e The BusinessException that was caught.
     */
    protected void handleException(BusinessException e) {

    }
    /**
     * Adds a global FacesMessage with SEVERITY_INFO.
     */
    protected void addGlobalInfoMessage(String summary, String detail) {
        addGlobalMessage(FacesMessage.SEVERITY_INFO, summary, detail);
    }
    
    /**
     * Adds a global FacesMessage with SEVERITY_WARN.
     */
    protected void addGlobalWarnMessage(String summary, String detail) {
        addGlobalMessage(FacesMessage.SEVERITY_WARN, summary, detail);
    }
    
    /**
     * Adds a global FacesMessage with SEVERITY_ERROR.
     */
    protected void addGlobalErrorMessage(String summary, String detail) {
        addGlobalMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    }

    /**
     * Adds a global FacesMessage with the specified severity.
     */
    protected void addGlobalMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
}