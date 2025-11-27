package mitfahrboerse.model.business.service;

import mitfahrboerse.global.dto.PasswordDTO;
import mitfahrboerse.global.dto.PasswordResetDTO;
import mitfahrboerse.global.dto.UserDTO;
import mitfahrboerse.model.business.exception.BusinessException;


/**
 * Handles all business logic related to password management,
 * excluding the initial login check (which is in AuthenticationService).
 * Its purpose is to securely manage password reset requests and changes.
 */
public class PasswordService {

	/**
	 * Private constructor.
	 */
	private PasswordService() {
	}
	

	/**
	 * Initiates a password reset request for a user.
	 * To generate a secure, single-use token,
	 * save its hash to the database, and send an email (via MailService)
	 * to the user with a reset link.
	 *
	 * @param email The email address of the user requesting the reset.
	 * @throws BusinessException If the user is not found or a mail/DB error occurs.
	 */
	public static void requestPasswordReset(UserDTO user) throws BusinessException {
		String email = user.getEmail();

	}
	
	/**
	 * Changes a user's password using a valid reset token.
	 * Validate the provided token, and if valid,
	 * securely hash and update the user's password in the database.
	 *
	 * @param passreset DTO containing the token and the new plain-text password.
	 * @throws BusinessException If the token is invalid (InvalidTokenException) or the DB update fails.
	 */
	public static void changePasswordWithToken(PasswordResetDTO passreset) throws BusinessException {
        // 1. Validiere den Token (TokenService.validateToken)
        // 2. Hashe das neue Passwort (PasswordHasher)
        // 3. Speichere das neue Passwort (ProfileService)
        // 4. Lösche den Token (TokenService.deleteToken)
	}
	
	}
