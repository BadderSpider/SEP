package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.OfferDTO;
import mitfahrboerse.global.dto.RequestDTO;
import mitfahrboerse.global.dto.SearchCriteriaDTO;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;

import java.util.List;

/**
 * Defines the data access contract for ride Requests.
 * A request is created by a user who is looking for a ride.
 *
 * @author Jonathan Schilcher
 */
public interface RequestDAO {

    /**
     * Creates and persists a new ride request.
     *
     * @param request The RequestDTO containing the details of the request.
     * @return The unique ID generated for the new request.
     * @throws DataAccessException if a database error occurs.
     */
    long createRequest(RequestDTO request) throws DataAccessException;

    /**
     * Updates the details of an existing ride request.
     *
     * @param request The RequestDTO with updated information.
     * @throws DataAccessException if the request does not exist or a database error occurs.
     */
    void updateRequest(RequestDTO request) throws DataAccessException;

    /**
     * Deletes a ride request from the database.
     *
     * @param requestId The ID of the request to delete.
     * @throws DataAccessException if a database error occurs.
     */
    void deleteRequest(long requestId) throws DataAccessException;

    /**
     * Finds a ride request by its unique ID.
     *
     * @param requestDTO The DTO with the id field filled out.
     * @throws DataAccessException if a database error occurs.
     */
    void findRequestById(RequestDTO requestDTO) throws DataAccessException;

    /**
     * Finds all offers that have been made for a specific ride request.
     *
     * @param requestId The ID of the request.
     * @return A list of OfferDTOs associated with the request.
     * @throws DataAccessException if a database error occurs.
     */
    List<OfferDTO> findOffersByRequestId(long requestId) throws DataAccessException;

    /**
     * Retrieves a list of ride requests based on flexible search criteria.
     *
     * @param criteria A DTO containing filters, pagination, and sorting information.
     * @return A list of matching RequestDTOs.
     * @throws DataAccessException if a database error occurs.
     */
    List<RequestDTO> findRequestsByCriteria(SearchCriteriaDTO criteria) throws DataAccessException;

    /**
     * Counts the total number of ride requests matching the given search criteria.
     *
     * @param criteria A DTO containing the filter information.
     * @return The total count of matching entries.
     * @throws DataAccessException if a database error occurs.
     */
    long countRequestsByCriteria(SearchCriteriaDTO criteria) throws DataAccessException;
}