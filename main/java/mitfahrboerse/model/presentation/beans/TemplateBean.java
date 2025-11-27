package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.SystemSettingsDTO;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;


/**
 * Backing bean for the main template (main_view.xhtml).
 * Inherits from SafeActionBean.
 * To provide global view data (like the logo URL or theme color)
 * to the main application template.
 *
 * @author Matthias Schmitt
 */
@RequestScoped
@Named
public class TemplateBean extends SafeActionBean implements Serializable {
	
    private static final long serialVersionUID = 1L;

    /**
     * DTO holding system settings (logo, color, etc.).
     */
    private SystemSettingsDTO setting;

    /**
     * Initializes the bean by loading the system settings.
     * Loads the system settings to provide them to the template.
     */
    @PostConstruct
    public void init() {
    }

    /**
     * Gets the dynamic URL for the application logo.
     * To provide the correct path to the ImageHandler servlet
     * for rendering the logo.
     * @return The URL (e.g., "/imagehandler?id=logo") as a String.
     */
    public String getLogoUrl() {
        return "/imagehandler?id=logo"; 
    }

    /**
     * Gets the dynamic primary color for the theme.
     * To allow the admin to customize the look and feel.
     * @return The path to the CSS file (e.g., "styles.css").
     */
    public String getCssPath() {
        if (setting != null && setting.getPathToCss() != null) {
            return setting.getPathToCss().toString();
        }
        return "styles.css";
    }

    // --- GETTER & SETTER ---

    /**
     * Gets the DTO holding the system settings (logo, color, etc.).
     * @return The SystemSettingsDTO.
     */
    public SystemSettingsDTO getSetting() {
        return this.setting;
    }

    /**
     * Sets the DTO holding the system settings.
     * @param setting The SystemSettingsDTO.
     */
    public void setSetting(SystemSettingsDTO setting) {
        this.setting = setting;
    }
}