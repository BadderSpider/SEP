package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.*;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;

import java.util.List;

/**
 * Defines the data access contract for Ride entities.
 * This interface distinguishes between a ride "template" (AbstractRideDTO), a single scheduled
 * "concrete ride" (RideDTO), and a "recurring ride" pattern (CycleDTO).
 *
 * @author Jonathan Schilcher
 */
public interface RideDAO {

    // --- Ride Template Methods ---

    /**
     * Creates a new, reusable ride template. This template contains the core information
     * about a ride but is not tied to a specific date.
     *
     * @param ride The AbstractRideDTO containing the ride's core details.
     * @return The unique ID generated for the ride template.
     * @throws DataAccessException if a database error occurs.
     */
    long createRide(AbstractRideDTO ride) throws DataAccessException;

    /**
     * Updates the details of a ride template (e.g., price, description).
     *
     * @param ride The AbstractRideDTO with updated information.
     * @throws DataAccessException if a database error occurs.
     */
    void updateRide(AbstractRideDTO ride) throws DataAccessException;

    /**
     * Deletes a ride template. This may cascade to delete associated concrete and recurring rides.
     *
     * @param rideId The ID of the ride template to delete.
     * @throws DataAccessException if a database error occurs.
     */
    void deleteRide(long rideId) throws DataAccessException;

    /**
     * Finds a ride template by its unique ID.
     *
     * @param abstractRideDTO The DTO with the id field filled out.
     * @throws DataAccessException if a database error occurs.
     */
    void findRideById(AbstractRideDTO abstractRideDTO) throws DataAccessException;

    /**
     * Adds a stop to a ride template's route. Implicitly handles reordering of subsequent stops.
     *
     * @param rideId The ID of the ride template.
     * @param stop The StopDTO to add.
     * @throws DataAccessException if a database error occurs.
     */
    void addStopToRide(long rideId, StopDTO stop) throws DataAccessException;

    /**
     * Removes a stop from a ride template.
     *
     * @param stopId The ID of the stop to remove.
     * @throws DataAccessException if a database error occurs.
     */
    void deleteStopFromRide(long stopId) throws DataAccessException;

    // --- Concrete Ride Methods ---

    /**
     * Creates a single, scheduled occurrence of a ride based on a ride template.
     *
     * @param concreteRide The RideDTO with a specific date and status.
     * @return The ID of the associated ride template.
     * @throws DataAccessException if a database error occurs.
     */
    long createConcreteRide(RideDTO concreteRide) throws DataAccessException;

    /**
     * Updates the status of a concrete ride (e.g., from PLANNED to ACTIVE).
     *
     * @param concreteRideId The ID of the concrete ride (same as the template ID).
     * @param status The new RideStatus.
     * @throws DataAccessException if a database error occurs.
     */
    void updateConcreteRideStatus(long concreteRideId, RideStatus status) throws DataAccessException;

    /**
     * Finds a specific, scheduled ride by its ID.
     *
     * @param rideDTO The DTO with the id field filled out.
     * @throws DataAccessException if a database error occurs.
     */
    void findConcreteRideById(RideDTO rideDTO) throws DataAccessException;

    // --- Recurring Ride Methods ---

    /**
     * Creates a new recurring ride template, defining a pattern for future rides.
     * This saves the recurrence rule but does not generate the individual concrete rides.
     *
     * @param cycle The CycleDTO defining the recurrence rule.
     * @return The ID of the associated ride template.
     * @throws DataAccessException if a database error occurs.
     */
    long createRecurringRide(CycleDTO cycle) throws DataAccessException;

    /**
     * Finds a recurring ride template by its ID.
     *
     * @param cycleDTO The DTO with the id field filled out.
     * @throws DataAccessException if a database error occurs.
     */
    void findRecurringRideById(CycleDTO cycleDTO) throws DataAccessException;

    // --- Relationship Methods ---

    /**
     * Retrieves all stops for a given ride template, correctly ordered by sequence.
     *
     * @param rideId The ID of the ride template.
     * @return An ordered list of StopDTOs.
     * @throws DataAccessException if a database error occurs.
     */
    List<StopDTO> findStopsByRideId(long rideId) throws DataAccessException;

    /**
     * Finds all bookings made for a specific concrete ride.
     *
     * @param concreteRideId The ID of the concrete ride.
     * @return A list of BookingDTOs for that ride.
     * @throws DataAccessException if a database error occurs.
     */
    List<BookingDTO> findBookingsByConcreteRideId(long concreteRideId) throws DataAccessException;

    /**
     * Finds all offers that have been made using a specific ride template.
     *
     * @param rideId The ID of the ride template.
     * @return A list of all OfferDTOs linked to that ride template.
     * @throws DataAccessException if a database error occurs.
     */
    List<OfferDTO> findOffersByConcreteRideId(long rideId) throws DataAccessException;

    // --- Search Methods ---

    /**
     * Retrieves a list of rides based on flexible search criteria.
     *
     * @param criteria A DTO containing filters, pagination, and sorting information.
     * @return A list of matching RideDTOs.
     * @throws DataAccessException if a database error occurs.
     */
    List<RideDTO> findRidesByCriteria(SearchCriteriaDTO criteria) throws DataAccessException;

    /**
     * Counts the total number of rides matching the given search criteria.
     *
     * @param criteria A DTO containing the filter information.
     * @return The total count of matching entries.
     * @throws DataAccessException if a database error occurs.
     */
    long countRidesByCriteria(SearchCriteriaDTO criteria) throws DataAccessException;
}