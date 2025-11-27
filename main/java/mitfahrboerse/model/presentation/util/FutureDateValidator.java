package mitfahrboerse.model.presentation.util;

import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;
import jakarta.faces.validator.ValidatorException;
import java.time.OffsetDateTime;

/**
 * Custom JSF Validator, checks if a date / time is in the future.
 *
 * @author Anton Hollube
 */
@FacesValidator("futureDateValidator")
public class FutureDateValidator implements Validator<Object> {

    /**
     * Validates if the given date is in the future.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    }
}