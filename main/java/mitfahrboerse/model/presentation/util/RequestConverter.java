package mitfahrboerse.model.presentation.util;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import mitfahrboerse.global.dto.RequestDTO;
import mitfahrboerse.model.business.exception.BusinessException;
import mitfahrboerse.model.business.service.FindService; 
import mitfahrboerse.model.business.service.RequestService; 

/**
 * Custom JSF Converter for converting a Request-ID-String to a full RequestDTO object
 * by calling the business logic.
 * This is used by f:viewParam to automatically load a request from a URL parameter.
 *
 * @author Anton Hollube
 */
@FacesConverter("requestConverter")
public class RequestConverter implements Converter<RequestDTO> {

    /**
     * To convert a Request-ID (e.g., "42") from the view (URL parameter)
     * into a full RequestDTO object by fetching it from the database.
     */
    @Override
    public RequestDTO getAsObject(FacesContext context, UIComponent component, String value) {
		return null;

    }

    /**
     * To convert a full RequestDTO object back into its unique ID (String)
     * for rendering in the view.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, RequestDTO value) {
    	return null;
    }

}