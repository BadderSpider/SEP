package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.SystemSettingsDTO;
import mitfahrboerse.model.business.service.SettingsService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Backing bean for the privacy_policy.xhtml page.
 * Inherits from SafeActionBean.
 * To load and provide the system's privacy policy text content
 * from the database for display.
 *
 * @author Anton Hollube
 */
@RequestScoped
@Named
public class PrivacyPolicyBean extends SafeActionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * DTO to hold all system settings, including the privacy policy text.
     */
    private SystemSettingsDTO systemSettingsDTO;

    /**
     * Initializes the bean.
     * Loads the system settings (containing the privacy policy)
     * immediately using safeAction for error handling.
     */
    @PostConstruct
    public void init() {
       
    }

    // --- GETTER & SETTER ---
    
    /**
     * Provides the SystemSettingsDTO to the Facelet.
     * Bound to `o_privacyContent` (value="#{privacyPolicyBean.systemSettingsDTO.privacyPolicy}")
     * @return The SystemSettingsDTO.
     */
    public SystemSettingsDTO getSystemSettingsDTO() {
        return this.systemSettingsDTO;
    }

    /**
     * Sets the SystemSettingsDTO.
     * @param systemSettingsDTO The SystemSettingsDTO.
     */
    public void setSystemSettingsDTO(SystemSettingsDTO systemSettingsDTO) {
        this.systemSettingsDTO = systemSettingsDTO;
    }
}