package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.OfferDTO;
import mitfahrboerse.global.dto.OfferStatus;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;

/**
 * Defines the data access contract for Offers.
 * An offer is a driver's response to a passenger's ride request, linking one of the driver's
 * rides to that request.
 *
 * @author Jonathan Schilcher
 */
public interface OfferDAO {

    /**
     * Creates a new offer from a driver for a specific ride request.
     *
     * @param offer The OfferDTO linking a ride to a request.
     * @return The unique ID generated for the new offer.
     * @throws DataAccessException if a database error occurs.
     */
    long createOffer(OfferDTO offer) throws DataAccessException;

    /**
     * Updates the status of an offer (e.g., from OPEN to ACCEPTED or DECLINED).
     *
     * @param offerId The ID of the offer to update.
     * @param status The new OfferStatus.
     * @throws DataAccessException if the offer does not exist or a database error occurs.
     */
    void updateOfferStatus(long offerId, OfferStatus status) throws DataAccessException;

    /**
     * Permanently removes an offer from the database.
     *
     * @param offerId The ID of the offer to delete.
     * @throws DataAccessException if a database error occurs.
     */
    void deleteOffer(long offerId) throws DataAccessException;

    /**
     * Finds an offer by its unique ID.
     *
     * @param offerDTO The DTO with the id field filled out.
     * @throws DataAccessException if a database error occurs.
     */
    void findOfferById(OfferDTO offerDTO) throws DataAccessException;

    /**
     * Checks if a driver has already made an offer for a specific ride request.
     * This is used to prevent duplicate offers.
     *
     * @param driverId The ID of the driver.
     * @param requestId The ID of the ride request.
     * @return true if an offer from this driver for this request already exists, false otherwise.
     * @throws DataAccessException if a database error occurs.
     */
    boolean hasDriverAlreadyOffered(long driverId, long requestId) throws DataAccessException;
}