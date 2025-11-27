package mitfahrboerse.model.business.util;

import mitfahrboerse.model.business.exception.BusinessException;
import mitfahrboerse.model.dataaccess.dao.RideDAO; 
import mitfahrboerse.model.dataaccess.util.Transaction;
import mitfahrboerse.model.dataaccess.util.TransactionFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Contains the logic to delete past rides from the database.
 * This is an ApplicationScoped CDI-Bean used by the MaintenanceService.
 *
 * @author Anton Hollube
 */
@ApplicationScoped
public class RideCleaner {

	//TransactionFactory @Inject was removed because TransactionFactory is Singleton

    /**
     * Deletes past rides from the database.
     *
     * @throws BusinessException If a data access error occurs during deletion.
     */
    public void cleanRides() throws BusinessException {
        try (Transaction transaction = TransactionFactory.getInstance().startTransaction()) {
            transaction.commit();
        } catch (Exception e) {
            throw new BusinessException("Fehler", e);
        }
    }
}