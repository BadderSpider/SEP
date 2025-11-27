package mitfahrboerse.model.business.service;

import mitfahrboerse.global.dto.RequestDTO;
import mitfahrboerse.global.dto.RideDTO;
import mitfahrboerse.model.business.exception.BusinessException;

/**
 * Handles all business logic for creating, finding, and deleting ride requests.
 * This service manages the passenger-side, allowing users
 * to post a "ride wanted" advertisement (a Request).
 *
 * @author Anton Hollube
 */
public class RequestService {

    private RequestService() {
    }

    /**
     * Creates a new ride request.
     * To allow a user to post a request for a ride,
     * specifying their desired start, destination, and time.
     *
     * @param requestDto The DTO containing all data for the new request.
     * @return The newly created and persisted RequestDTO.
     * @throws BusinessException If validation fails (ValidationException) or a data access error occurs.
     */
    public static RequestDTO createRequest(RequestDTO requestDto) throws BusinessException {
        return null;
    }

    /**
     * Updates an existing ride request.
     * This method allows the original requester to modify the details of
     * their posted request (e.g., change the time or price).
     *
     * @param requestDto The DTO containing the updated request data.
     * @return The persisted RequestDTO with updated fields.
     * @throws BusinessException If the user is not allowed to update (OperationNotAllowedException) or a data access error occurs.
     */
    public static RequestDTO updateRequest(RequestDTO requestDto) throws BusinessException {
        return null;
    }

    /**
     * Deletes an existing ride request.
     * This method allows the original requester to remove their post
     * from the "Requests" page, for example if they no longer need a ride.
     *
     * @param requestDto DTO containing the requestId of the request to delete.
     * @throws BusinessException If the user is not allowed to delete (OperationNotAllowedException) or a data access error occurs.
     */
    public static void deleteRequest(RequestDTO requestDto) throws BusinessException {
    }
    
    public static RequestDTO getRequestByID(RequestDTO requestDto) throws BusinessException {
    	long requestID = requestDto.getRequestId();
		return null;}
}