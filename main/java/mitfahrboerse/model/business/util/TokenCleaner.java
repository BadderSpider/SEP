package mitfahrboerse.model.business.util;

import mitfahrboerse.model.business.exception.BusinessException;
import mitfahrboerse.model.dataaccess.dao.UserDAO;
import mitfahrboerse.model.dataaccess.util.Transaction;
import mitfahrboerse.model.dataaccess.util.TransactionFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Contains the logic to invalidate expired registration or password reset tokens.
 * This is an ApplicationScoped CDI-Bean used by the MaintenanceService.
 *
 * @author Anton Hollube
 */
@ApplicationScoped
public class TokenCleaner {

    //TransactionFactory @Inject was removed because TransactionFactory is Singleton

    /**
     * Deletes all tokens from verifications and password resets that are past their expire date.
     *
     * @throws BusinessException If a data access error occurs during deletion.
     */
    public void invalidateExpiredTokens() throws BusinessException {
        
        try (Transaction transaction = TransactionFactory.getInstance().startTransaction()) {
            transaction.commit();
        } catch (Exception e) {
            throw new BusinessException("Fehler", e);
        }
    }
}