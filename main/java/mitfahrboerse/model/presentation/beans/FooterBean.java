package mitfahrboerse.model.presentation.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Backing bean for the footer.xhtml template
 * Inherits from SafeActionBean.
 * This bean exists to support the footer template.
 * Currently, it has no actions, but could be used to display dynamic
 * content, like a version number in the future.
 *
 * @author Anton Hollube
 */
@ApplicationScoped 
@Named
public class FooterBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Initializes the bean.
     */
    @PostConstruct
    public void init() {
    }

}