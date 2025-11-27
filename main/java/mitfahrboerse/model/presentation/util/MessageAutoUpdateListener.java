package mitfahrboerse.model.presentation.util;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import mitfahrboerse.model.presentation.beans.MessagePanelBean;

import java.util.Collection;

/**
 * A PhaseListener that acts as a "watcher" for global messages.
 * If a global FacesMessage is detected during an AJAX request,
 * this listener automatically adds the message panel wrapper to the list of components to be rendered.
 * @author Matthias Schmitt
 */
public class MessageAutoUpdateListener implements PhaseListener {

    private static final String MESSAGE_PANEL_ID = "left-sidebar-wrapper";

    //toDO überlegen ob man nicht besser das rendertAttribut setzt

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();

        if (context.getPartialViewContext().isAjaxRequest()) {

            if (!context.getMessageList(null).isEmpty()) {

                Collection<String> renderIds = context.getPartialViewContext().getRenderIds();

                if (!renderIds.contains(MESSAGE_PANEL_ID)) {
                    renderIds.add(MESSAGE_PANEL_ID);
                }
            }
        }
    }

    @Override
    public void afterPhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}