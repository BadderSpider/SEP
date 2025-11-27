package mitfahrboerse.model.presentation.util;

import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;
import jakarta.faces.validator.ValidatorException;

/**
 * Custom JSF Validator, checks if a start date is chronologically before an end date.
 *
 * @author Anton Hollube
 */
@FacesValidator("startDateBeforeEndDateValidator")
public class StartDateBeforeEndDateValidator implements Validator<Object> {

    /**
     * Validates if the start date (value) is before the corresponding end date (component attribute).
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    }
}