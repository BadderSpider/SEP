package mitfahrboerse.model.presentation.util;

import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;
import jakarta.faces.validator.ValidatorException;

/**
 * Custom JSF Validator, checks if an email is already registered in the system.
 *
 * @author Anton Hollube
 */
@FacesValidator("emailExistsValidator")
public class EmailExistsValidator implements Validator<Object> {


    /**
     * Validates if the given email already exists.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    }
}