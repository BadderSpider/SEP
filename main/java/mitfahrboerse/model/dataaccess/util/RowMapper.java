package mitfahrboerse.model.dataaccess.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A functional interface for mapping a single row of a {@link ResultSet} to an object.
 * Implementations of this interface define the logic for converting JDBC results into application DTOs.
 *
 * @param <T> The type of object this mapper produces.
 *
 * @author Jonathan Schilcher
 */
@FunctionalInterface
public interface RowMapper<T> {
    /**
     * Maps the current row of the given {@code ResultSet} to an object.
     *
     * @param rs The ResultSet to map from, positioned at the row to be processed.
     * @return The resulting object for the current row.
     * @throws SQLException if a database access error occurs or a column is not found.
     */
    T map(ResultSet rs) throws SQLException;
}
