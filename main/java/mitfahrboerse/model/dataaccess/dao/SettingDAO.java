package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.SystemSettingsDTO;

/**
 * Manages data access for the application's global system settings.
 * It is assumed that there is only a single entity (e.g., one row in a table)
 * representing these settings.
 *
 * @author Jonathan Schilcher
 */
public interface SettingDAO {

    /**
     * Retrieves the current system settings from the database.
     *
     * @return A SystemSettingsDTO containing all global configuration values.
     */
    SystemSettingsDTO getSystemSettings();

    /**
     * Updates the system settings in the database.
     *
     * @param settings The SystemSettingsDTO with the new values to be persisted.
     */
    void updateSystemSettings(SystemSettingsDTO settings);
}