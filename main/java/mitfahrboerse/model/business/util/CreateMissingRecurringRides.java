package mitfahrboerse.model.business.util;

import mitfahrboerse.model.business.exception.BusinessException;
import mitfahrboerse.model.dataaccess.dao.RideDAO;
import mitfahrboerse.model.dataaccess.util.Transaction;
import mitfahrboerse.model.dataaccess.util.TransactionFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Contains the logic to create new instances of recurring rides.
 * This is an ApplicationScoped CDI-Bean used by the MaintenanceService.
 *
 * @author Anton Hollube
 */
@ApplicationScoped
public class CreateMissingRecurringRides {

	//TransactionFactory @Inject was removed because TransactionFactory is Singleton

    /**
     * Creates new instances of recurring rides.
     *
     * @throws BusinessException If a data access error occurs during ride creation.
     */
    public void createMissingRecurringRides() throws BusinessException {
        
        try (Transaction transaction = TransactionFactory.getInstance().startTransaction()) {

            transaction.commit();
        } catch (Exception e) {
            throw new BusinessException("Fehler", e);
        }
    }
}