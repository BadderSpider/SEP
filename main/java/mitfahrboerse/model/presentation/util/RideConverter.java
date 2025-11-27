package mitfahrboerse.model.presentation.util;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import mitfahrboerse.global.dto.AbstractRideDTO;
import mitfahrboerse.global.dto.RideDTO;
import mitfahrboerse.model.business.service.RideService;


/**
 * Custom JSF Converter for converting a Ride-ID-String to a full RideDTO object
 * by calling the business logic.
 * This is used by f:viewParam to automatically load a ride from a URL parameter.
 *
 * @author Anton Hollube
 */
@FacesConverter("rideConverter")
public class RideConverter implements Converter<AbstractRideDTO> {

    /**
     * To convert a Ride-ID (e.g., "123") from the view (URL parameter)
     * into a full RideDTO object by fetching it from the database via the RideService.
     */
    @Override
    public RideDTO getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
        
    }

    /**
     * To convert a full RideDTO object back into its unique ID (String)
     * for rendering in the view (e.g., in hidden fields or links).
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, AbstractRideDTO value) {
		return null;
       
    }
}