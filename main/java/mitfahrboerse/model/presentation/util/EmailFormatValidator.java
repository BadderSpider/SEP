package mitfahrboerse.model.presentation.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.util.regex.Pattern;

/**
 * Custom JSF Validator that checks if a given string input follows a valid email format.
 *
 * @author René Schmaderer
 */
@FacesValidator("emailValidator")
public class EmailFormatValidator implements Validator<String> {

    /**
     * Regular Expression to validate the email format.
     * Requires an @-sign as well as a TLD with at least 2 characters.
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$");

    /**
     * Validates the given value against the email regex pattern.
     * If the value is {@code null} or empty, the validation is handled by
     * the {@code required="true"} attribute on the Facelet.
     *
     * @param context   The {@link FacesContext} for the request being processed.
     * @param component The {@link UIComponent} we are checking.
     * @param value     The String to be validated.
     * @throws ValidatorException if the value is not a valid email address format.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value == null || value.isEmpty()) {
            return;
        }

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            // TODO Rene: Retrieve localized message from resource bundle (language.properties)
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Invalid format",
                    "Invalid email address"
            ));
        }
    }
}