package mitfahrboerse.model.presentation.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mitfahrboerse.model.presentation.util.UserSessionBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing bean for the help.xhtml component.
 * Logic to generate help keys based on user roles and page context.
 *
 * @author Matthias Schmitt
 */
@RequestScoped
@Named
public class HelpBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserSessionBean userSessionBean;

    public List<String> getHelpKeys() {
        List<String> keys = new ArrayList<>();
        String pageName = getCurrentPageName();

        if (pageName == null) return keys;

        keys.add("help.anonymous." + pageName);

        if (userSessionBean.isLoggedIn() || userSessionBean.isAdmin()) {
            keys.add("help.registered." + pageName);
        }

        if (userSessionBean.isAdmin()) {
            keys.add("help.admin." + pageName);
        }

        return keys;
    }

    private String getCurrentPageName() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context == null || context.getViewRoot() == null) return null;

        String viewId = context.getViewRoot().getViewId();
        if (viewId == null) return null;

        int lastSlash = viewId.lastIndexOf('/');
        int extension = viewId.lastIndexOf(".xhtml");

        if (lastSlash != -1 && extension != -1 && extension > lastSlash) {
            return viewId.substring(lastSlash + 1, extension);
        }

        return "not found";
    }
}