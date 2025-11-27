package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.SystemSettingsDTO;

import java.sql.Connection;


/**
 * The PostgreSQL-specific implementation of the {@link SettingDAO} interface.
 * This class handles the data access for global system settings.
 * Instances are created by a transaction and are not meant to be used directly.
 *
 * @author Jonathan Schilcher
 */
public final class SettingDAOPsql extends AbstractDAO implements SettingDAO {

    /**
     * Constructs a new SettingDAOPsql with a database connection provided by a transaction.
     *
     * @param connection The database connection from the current transaction context.
     */
    public SettingDAOPsql(Connection connection) {
        super(connection);
    }

    @Override
    public SystemSettingsDTO getSystemSettings() {
        return null;
    }

    @Override
    public void updateSystemSettings(SystemSettingsDTO settings) {

    }
}
