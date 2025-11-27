package mitfahrboerse.model.dataaccess.dao;


import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;
import mitfahrboerse.model.dataaccess.exceptions.DataIntegrityViolationException;
import mitfahrboerse.model.dataaccess.exceptions.DataProcessException;
import mitfahrboerse.model.dataaccess.exceptions.NonUniqueResultException;
import mitfahrboerse.model.dataaccess.util.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An abstract base class for DAOs, providing generic and reusable database query methods.
 * It encapsulates common JDBC boilerplate code for executing queries and handling results.
 *
 * @author Jonathan Schilcher
 */
public abstract class AbstractDAO {

    protected final Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Executes a query that returns a single (scalar) value.
     * Ideal for `COUNT(*)`, `MAX()`, or fetching a single column from one row.
     *
     * @param sql    The SQL query, which should return exactly one column.
     * @param type   The class of the expected return type (e.g., Long.class, String.class).
     * @param params The query parameters.
     * @param <T>    The generic type of the return value.
     * @return An Optional containing the value, or an empty Optional if no result was found.
     * @throws DataAccessException if a database access error occurs.
     */
    protected <T> Optional<T> findScalar(String sql, Class<T> type, Object... params) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.ofNullable(rs.getObject(1, type));
                }
            }
        } catch (SQLException e) {
            throw handleSQLException(e, "Database query failed for findScalar");
        }
        return Optional.empty();
    }

    /**
     * Executes a query that is expected to return a single result.
     *
     * @param sql    The SQL query to execute.
     * @param mapper The RowMapper to convert the result set row to an object.
     * @param params The query parameters for the prepared statement.
     * @param <T>    The type of the result object.
     * @return An {@link Optional} containing the result, or empty if not found.
     * @throws NonUniqueResultException if the query returns more than one result.
     * @throws DataAccessException for underlying database access errors.
     */
    protected <T> Optional<T> findOne(String sql, RowMapper<T> mapper, Object... params) throws DataAccessException {
        List<T> results = findAll(sql, mapper, params);

        if (results.size() > 1) {
            throw new NonUniqueResultException("Query was expected to return a single result, but returned " + results.size() + ".");
        }

        return results.stream().findFirst();
    }

    /**
     * Executes a query and maps all rows of the result set to a list of objects.
     *
     * @param sql    The SQL query to execute.
     * @param mapper The RowMapper to convert each result set row to an object.
     * @param params The query parameters for the prepared statement.
     * @param <T>    The type of the result objects.
     * @return A {@link List} of mapped objects; the list is empty if no results are found.
     * @throws DataProcessException if mapping a row from the result set fails.
     * @throws DataAccessException for general database access errors.
     */
    protected <T> List<T> findAll(String sql, RowMapper<T> mapper, Object... params) throws DataAccessException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapper.map(rs));
                }
            }
        } catch (SQLException e) {
            throw handleSQLException(e, "Database query failed for findAll");
        } catch (Exception e) {
            throw new DataProcessException("Failed to process data from result set", e);
        }
        return results;
    }

    /**
     * Executes an INSERT statement and returns the auto-generated primary key.
     *
     * @param sql    The SQL INSERT statement to execute.
     * @param params The parameters for the prepared statement.
     * @return An Optional containing the generated key (typically a Long), or empty if no key was returned.
     * @throws DataAccessException if a database access error occurs.
     */
    protected Optional<Long> create(String sql, Object... params) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(stmt, params);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return Optional.of(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw handleSQLException(e, "Database create failed");
        }
        return Optional.empty();
    }

    /**
     * Executes a generic DML statement like UPDATE or DELETE.
     *
     * @param sql    The SQL statement to execute.
     * @param params The parameters for the prepared statement.
     * @throws DataAccessException if a database access error occurs.
     */
    protected void execute(String sql, Object... params) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, params);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw handleSQLException(e, "Database execute failed");
        }
    }

    /**
     * A helper method to safely set parameters on a PreparedStatement.
     * It includes special handling for Enum types, which are persisted as their string names.
     */
    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            if (params[i] instanceof Enum<?>) {
                stmt.setObject(i + 1, ((Enum<?>) params[i]).name(), Types.OTHER);
            } else {
                stmt.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * Translates a generic {@link SQLException} into a more specific {@link DataAccessException} subtype.
     * This is used to provide more meaningful exceptions to the application's business layer.
     *
     * @param e The original SQL exception.
     * @param message A descriptive message of what failed.
     * @return The specific DataAccessException subclass.
     */
    public static DataAccessException handleSQLException(SQLException e, String message) {
        String sqlState = e.getSQLState();

        if (sqlState != null && sqlState.startsWith("23")) {
            return new DataIntegrityViolationException(message + ": A data integrity constraint was violated.", e);
        }

        return new DataAccessException(message, e);
    }
}
