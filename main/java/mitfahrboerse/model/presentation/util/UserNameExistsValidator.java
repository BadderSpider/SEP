package mitfahrboerse.model.presentation.util;

import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;
import jakarta.faces.validator.ValidatorException;

/**
 * Custom JSF Validator, checks if a username is already registered.
 *
 * @author Anton Hollube
 */
@FacesValidator("userNameExistsValidator")
public class UserNameExistsValidator implements Validator<Object> {

    /**
     * Validates if the given username already exists in the system.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    }
}