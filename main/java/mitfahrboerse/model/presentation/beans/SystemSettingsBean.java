package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.SystemSettingsDTO;
import mitfahrboerse.model.business.service.SettingsService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import java.io.Serializable;

/**
 * Backing bean for the system_settings.xhtml page.
 * Inherits from SafeActionBean.
 * This is an Admin-only bean that allows editing and saving
 * global application settings (logo, imprint, deadlines, etc.).
 *
 * @author Anton Hollube
 */
@ViewScoped
@Named
public class SystemSettingsBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The main DTO holding all system settings.
     * Loaded in init() and bound to all form fields
     * ('it_platformName', 'ita_imprint').
     */
    private SystemSettingsDTO settings;

    /**
     * Holds the uploaded file part for the new logo.
     * Bound to 'if_logoUpload'.
     */
    private Part uploadedLogoFile;

    /**
     * Initializes the bean.
     * Loads the current settings from the database via the
     * SettingsService to populate the form fields.
     */
    @PostConstruct
    public void init() {
    }

    /**
     * Action method for 'cb_saveSettings'.
     * Saves all changes to the system settings.
     */
    public void save() {
    }

    // --- GETTER & SETTER ---

    /**
     * Provides the main DTO for form bindings (e.g., 'ita_imprint').
     * @return The SystemSettingsDTO.
     */
    public SystemSettingsDTO getSettings() {
        return settings;
    }

    /**
     * Sets the main DTO.
     * @param settings The SystemSettingsDTO.
     */
    public void setSettings(SystemSettingsDTO settings) {
        this.settings = settings;
    }

    /**
     * Provides the Part for 'if_logoUpload'.
     * @return The uploaded logo file.
     */
    public Part getUploadedLogoFile() {
        return uploadedLogoFile;
    }

    /**
     * Sets the Part from 'if_logoUpload'.
     * @param uploadedLogoFile The uploaded logo file.
     */
    public void setUploadedLogoFile(Part uploadedLogoFile) {
        this.uploadedLogoFile = uploadedLogoFile;
    }
}