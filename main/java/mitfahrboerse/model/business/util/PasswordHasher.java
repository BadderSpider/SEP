package mitfahrboerse.model.business.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * Utility for securely hashing and verifying user passwords.
 *
 * @author René Schmaderer
 */

//todo aufräumen und verstehen

public class PasswordHasher {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    /**
     * Hashes a plain-text password using the provided salt.
     *
     * @param password The plain-text password.
     * @param salt The Base64 encoded salt string.
     * @return The secure hash as a Base64 String.
     */
    public static String hashPassword(String password, String salt) {
        if (password == null || salt == null) {
            throw new IllegalArgumentException("Password and Salt must not be null");
        }

        try {
            char[] passwordChars = password.toCharArray();
            byte[] saltBytes = Base64.getDecoder().decode(salt);

            PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);

            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }

    /**
     * Verifies a plain-text password against a stored hash.
     *
     * @param password The plain-text password to check.
     * @param salt The stored salt (Base64) from the database.
     * @param storedHash The stored hash (Base64) to compare against.
     * @return true if the password matches the hash, false otherwise.
     */
    public static boolean verifyPassword(String password, String salt, String storedHash) {
        if (password == null || salt == null || storedHash == null) {
            return false;
        }

        String newHash = hashPassword(password, salt);

        return MessageDigest.isEqual(storedHash.getBytes(), newHash.getBytes());
    }

    /**
     * Generates a new Salt.
     * @return Base64 String Salt
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

}