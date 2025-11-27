package mitfahrboerse.model.presentation.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;

/**
 * Backing bean for the global message_panel.xhtml component.
 * To manage global (non-component-specific) FacesMessages
 * that appear in the global message panel.
 *
 * @author Matthias Schmitt
 */
@SessionScoped
@Named
public class MessagePanelBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public MessagePanelBean() {
    }

    /**
     * Initializes the bean.
     */
    @PostConstruct
    public void init() {
    }

    /**
     * Checks if there are any global messages (messages with clientId = null)
     * in the current FacesContext.
     * * @return true if global messages exist, false otherwise.
     */
    public boolean isHasGlobalMessages() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc != null && !fc.getMessageList(null).isEmpty();
    }

    /**
     * Action method for 'cb_closeMessages'.
     * Technically, JSF messages are request-scoped and cleared automatically
     * on the next request (which this button triggers).
     * So this method can remain empty or just log the action.
     */
    public void clearMessages() {
    }
}