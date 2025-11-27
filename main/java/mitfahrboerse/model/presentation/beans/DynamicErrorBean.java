package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.DynamicErrorDTO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Backing bean for the dynamic error page.
 * To receive error details (code, message, stacktrace)
 * from the CustomExceptionHandler via the JSF Flash scope and display
 * them on the error page.
 *
 * @author Anton Hollube
 */
@RequestScoped
@Named
public class DynamicErrorBean {


    /**
     * DTO to hold all error details.
     * Bound to 'o_errorMessage' and 'o_errorCode'.
     */
    private DynamicErrorDTO errorDTO;
    
    @Inject
    private FacesContext facesContext;


    /**
     * Initializes the bean.
     * Reads the DynamicErrorDTO object that the
     * CustomExceptionHandler placed in the Flash scope before redirecting.
     */
    @PostConstruct
    public void init() {
        
    }

    // --- GETTER & SETTER ---

    /**
     * Gets the Data Transfer Object containing error details (code, message).
     * @return The DynamicErrorDTO.
     */
    public DynamicErrorDTO getErrorDTO() {
        return this.errorDTO;
    }

    /**
     * Sets the Data Transfer Object containing error details.
     * @param errorDTO The DynamicErrorDTO.
     */
    public void setErrorDTO(DynamicErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }
}