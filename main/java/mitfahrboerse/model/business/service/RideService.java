package mitfahrboerse.model.business.service;

import jakarta.enterprise.inject.spi.CDI;
import mitfahrboerse.global.dto.BookingDTO;
import mitfahrboerse.global.dto.MailDTO;
import mitfahrboerse.global.dto.RideDTO;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.global.dto.SearchCriteriaDTO;
import mitfahrboerse.model.business.exception.BusinessException;
import mitfahrboerse.model.dataaccess.dao.RideDAO;
import mitfahrboerse.model.dataaccess.dao.UserDAO;
import mitfahrboerse.model.dataaccess.util.Transaction;
import mitfahrboerse.model.dataaccess.util.TransactionFactory;

import java.util.List;

/**
 * Handles all business logic for creating, editing, and deleting rides.
 * This service manages the "driver-side" of the market, allowing users
 * to offer rides, update their details, and manage the ride's lifecycle
 * (e.g., cancelling it).
 *
 * @author Anton Hollube
 */
public class RideService {

    private RideService() {
    }

    /**
     * Creates a new ride offer.
     * To allow a driver to validate and persist a new
     * ride (both the template and any initial concrete rides) to the database.
     *
     * @param rideDto The DTO containing all data for the new ride.
     * @return The newly created and persisted RideDTO.
     * @throws BusinessException If validation fails (ValidationException) or a data access error occurs.
     */
    public static long createRide(RideDTO rideDto) throws BusinessException {
		return 0;
//        try (Transaction transaction = TransactionFactory.getInstance().startTransaction()) {
//           return transaction.getDAO(RideDAO.class).createRide(rideDto);
//        }
    }

    /**
     * Updates an existing ride offer.
     * To allow a driver to change the details of a
     * ride they have already created (e.g., price, time). It also
     * triggers notifications to already-booked passengers.
     *
     * @param rideDto The DTO containing the updated ride data.
     * @return The persisted RideDTO with updated fields.
     * @throws BusinessException If the ride cannot be updated (OperationNotAllowedException) or a data access error occurs.
     */
    public static RideDTO updateRide(RideDTO rideDto) throws BusinessException {
        return null;
    }

    /**
     * Deletes an existing ride offer.
     * To completely remove a ride template and all
     * its associated concrete rides, also used when a ride has no
     * bookings or is being removed entirely.
     *
     * @param rideDto DTO containing the rideId of the ride to delete.
     * @throws BusinessException If the ride cannot be deleted (e.g., has active bookings) (OperationNotAllowedException).
     */
    public static void deleteRide(RideDTO rideDto) throws BusinessException {
    }

    /**
     * Cancels a planned ride.
     * To mark a ride as 'CANCELLED'. This is used
     * when a ride cannot take place (e.g., driver is sick) but has existing
     * bookings. It preserves the record and notifies passengers.
     *
     * @param rideDto DTO containing the rideId of the ride to cancel.
     * @throws BusinessException If the ride cannot be cancelled (e.g., deadline passed) (OperationNotAllowedException).
     */
    public static void cancelRide(RideDTO rideDto) throws BusinessException {
    }

    /**
     * Returns the RideDTO with the given ID.
     * To load the raw data for a ride into the
     * RideFormBean when a user wants to *edit* an existing ride.
     *
     * @param rideID The ID of the ride
     * @return The RideDTO with the given ID.
     */
    public static RideDTO getRideByID(RideDTO rideDto) throws BusinessException {
    	long rideID = rideDto.getRideId();
        try (Transaction transaction = TransactionFactory.getInstance().startTransaction()) {
            RideDAO rideDAO = transaction.getDAO(RideDAO.class);
  //         rideDto = rideDAO.findConcreteRideById(rideID); 
            transaction.commit();
        }

        return rideDto;
    }

    /**
     * Sends update emails to all passengers of a specific ride.
     *
     * @param rideDTO The ride for which to send updates.
     * @throws BusinessException If the database query fails.
     */
    public static void sendUpdateMailToPassengers(RideDTO rideDTO) throws BusinessException {
        try (Transaction transaction = TransactionFactory.getInstance().startTransaction()) {
            RideDAO rideDAO = transaction.getDAO(RideDAO.class);
            List<BookingDTO> bookingDTOList = rideDAO.findBookingsByConcreteRideId(rideDTO.getRideId());

            UserDAO userDAO = transaction.getDAO(UserDAO.class);

            MailService mailService = CDI.current().select(MailService.class).get();

            for (BookingDTO bookingdto : bookingDTOList) {
                try {
                    UserDTO user = new UserDTO();
                    user.setUserId(bookingdto.getPassenger());
                    userDAO.findUserById(user);
                    mailService.sendMail(new MailDTO(user.getEmail(), "Update zur Fahrt", "Die Fahrt hat sich geändert."));
                } catch (BusinessException e) {
                    System.err.println("Failed to send update mail to passenger: " + bookingdto.getPassenger());
                }
            }
            transaction.commit();
        } catch (Exception e) {
            throw new BusinessException("Error sending update mails", e);
        }
    }

    /**
     * Searches for rides based on the given filter, pagination, and sorting criteria.
     * Also updates the totalElements count in the searchCriteria for pagination.
     *
     * @param searchCriteria The DTO containing filters, pagination and sorting.
     * @return A list of RideDTOs matching the criteria.
     * @throws BusinessException If a database error occurs.
     * @autor Matthias Schmitt
     */
    public static List<RideDTO> searchRides(SearchCriteriaDTO searchCriteria) throws BusinessException {
        try (Transaction transaction = TransactionFactory.getInstance().startTransaction()) {
            RideDAO rideDAO = transaction.getDAO(RideDAO.class);

            List<RideDTO> rides = rideDAO.findRidesByCriteria(searchCriteria);

            long totalCount = rideDAO.countRidesByCriteria(searchCriteria);

            if (searchCriteria.getPagination() != null) {
                searchCriteria.getPagination().setTotalElements(totalCount);
            }

            transaction.commit();
            return rides;
        } catch (Exception e) {
            throw new BusinessException("Fehler bei der Suche nach Fahrten.", e);
        }
    }
}