package mitfahrboerse.model.presentation.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.Validator;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.validator.FacesValidator;


/**
 * Custom JSF Validator that checks if the given string input matches the
 * minimal requirements.
 *
 * @author René Schmaderer
 */
@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator<String> {
	
	 private static final int MIN_PASSWORD_LENGTH = 8;

    /**
     * Validates the given values length.
     *
     * @param context   The {@link FacesContext} for the request being processed.
     * @param component The {@link UIComponent} we are checking.
     * @param value     The String to be validated.
     * @throws ValidatorException if the value is not long enough to meet the passwort requirements.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value.length() < MIN_PASSWORD_LENGTH) {
            // TODO Rene: Retrieve localized message from resource bundle (language.properties)
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Password too short",
                    "Password needs to be at least " + MIN_PASSWORD_LENGTH +"characters long."
            ));        }
    }
}