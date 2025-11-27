package mitfahrboerse.model.business.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mitfahrboerse.global.dto.LoginDTO;
import mitfahrboerse.global.dto.PasswordDTO;
import mitfahrboerse.global.dto.RegistrationDTO;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.model.business.exception.BusinessException;
import mitfahrboerse.model.business.util.PasswordHasher;
import mitfahrboerse.model.dataaccess.dao.RideDAO;
import mitfahrboerse.model.dataaccess.dao.UserDAO;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;
import mitfahrboerse.model.dataaccess.util.Transaction;
import mitfahrboerse.model.dataaccess.util.TransactionFactory;

/**
 * Handles business logic for user login, registration, and session validation.
 * This class is the central authority for verifying user identity and creating
 * new user accounts, acting as the gateway to protected areas of the application.
 *
 * @author Anton Hollube
 */
@ApplicationScoped
@Named
public class AuthenticationService {



    private AuthenticationService() {

    }


    /**
     * Authenticates a user based on the provided login credentials.
     * Verifies credentials against the database and return the
     * full user profile upon success, which is then used to establish a user session.
     *
     * @param loginDTO The DTO containing email and password.
     * @return The fully populated UserDTO if authentication is successful.
     * @throws BusinessException If authentication fails (e.g., invalid credentials) or a data access error occurs.
     */
    public static UserDTO login(LoginDTO loginDTO) throws BusinessException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(loginDTO.getEmail());
        PasswordDTO passwordDTO = new PasswordDTO();

        try (Transaction transaction = TransactionFactory.getInstance().startTransaction()) {
            UserDAO userDAO = transaction.getDAO(UserDAO.class);
            userDAO.findUserByEmail(userDTO);
            passwordDTO = userDAO.findPasswordInfoByEmail(loginDTO.getEmail()); //todo auch wieder void
            transaction.commit();
        } catch (DataAccessException e) {
            throw new BusinessException(e.getMessage()); //ToDO
        }

        if (PasswordHasher.verifyPassword(loginDTO.getPassword(),passwordDTO.getSalt(),passwordDTO.getPasswordHash())) {
            return userDTO;
        }

        return null;
    }

    /**
     * Registers a new user in the system.
     * This method performs the initial data validation and creation of a new user,
     * including password hashing. It also triggers the necessary email verification process.
     *
     * @param regDTO The DTO containing all required registration data.
     * @return The newly created and persisted UserDTO.
     * @throws BusinessException If the email is already in use (UserExistsException) or a data access error occurs.
     */
    public static UserDTO register(RegistrationDTO regDTO) throws BusinessException {

        return null;
    }

    /**
     * Logs the current user out of the system.
     * To provide a clear entry point for the presentation layer
     * to signal that the user's session should be terminated.
     */
    public static void logout() {
    }
}