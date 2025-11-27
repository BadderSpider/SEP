package mitfahrboerse.model.presentation.util;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Utility class responsible for verifying data ownership to prevent
 * Insecure Direct Object References.
 *
 * @author René Schmaderer
 */
@RequestScoped
@Named
public class OwnershipChecker {

    @Inject
    private UserSessionBean userSession;

    /**
     * Validates if the current user has ownership rights for the given user ID.
     * Access is granted if the current user's ID matches the requested {@code userId}
     * or if the current user has administrator privileges.
     *
     * @param userId The ID of the user associated with the requested resource.
     * @return {@code true} if the current user is the owner or an admin, {@code false} otherwise.
     */
    public boolean validateOwnership(long userId) {
        return (userId == userSession.getUserId() || userSession.isAdmin());
    }

}