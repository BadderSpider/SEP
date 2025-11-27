package mitfahrboerse.model.presentation.util;


/**
 * A static utility class for accessing translated strings from the
 * application's resource bundles (e.g., language_de.properties).
 *
 * To allow Java code (like Beans, Validators, or Services)
 * to retrieve internationalized messages (e.g., for error handling
 * in SafeActionBean) based on the user's current session language.
 *
 * @author Anton Hollube
 */
public class ResourceBundleHelper {


    /**
     * The base name of the resource bundle, defined in faces-config.xml.
     */
    private static final String BUNDLE_BASE_NAME = "language";

    private ResourceBundleHelper() {
    }

    /**
     * Gets a string for the given key from the resource bundle,
     * using the current user's locale from the FacesContext.
     *
     * @param key The key of the string to retrieve (e.g., "error.emailExists").
     * @return The translated string, or a placeholder if the key is not found.
     */
    public static String getMessage(String key) {
		return key;
       
    }
}