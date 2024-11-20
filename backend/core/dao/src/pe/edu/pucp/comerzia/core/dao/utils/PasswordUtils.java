package pe.edu.pucp.comerzia.core.dao.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

  private static final int SALT_LENGTH = 16; // Salt length in bytes

  /**
   * Hashes a password with a generated salt.
   *
   * @param password The plain text password to hash
   * @return A combined string of the salt and hashed password, separated by a colon
   * @throws NoSuchAlgorithmException If SHA-256 is not available
   */
  public static String hashPassword(String password)
    throws NoSuchAlgorithmException {
    // Generate a random salt
    byte[] salt = generateSalt();
    String saltBase64 = Base64.getEncoder().encodeToString(salt);

    // Hash the password with the salt
    String hashedPassword = hashPasswordWithSalt(password, salt);

    // Combine the salt and the hashed password
    return saltBase64 + ":" + hashedPassword;
  }

  /**
   * Verifies a password against a stored hash.
   *
   * @param password       The plain text password to verify
   * @param storedHashSalt The stored salt and hash combined string (format: salt:hash)
   * @return True if the password matches the stored hash, false otherwise
   * @throws NoSuchAlgorithmException If SHA-256 is not available
   */
  public static boolean verifyPassword(String password, String storedHashSalt)
    throws NoSuchAlgorithmException {
    // Split the stored string into salt and hash
    String[] parts = storedHashSalt.split(":");
    if (parts.length != 2) {
      throw new IllegalArgumentException(
        "Invalid stored hash format. Expected 'salt:hash'."
      );
    }

    String saltBase64 = parts[0];
    String storedHash = parts[1];

    // Decode the salt and hash the password
    byte[] salt = Base64.getDecoder().decode(saltBase64);
    String hashedPassword = hashPasswordWithSalt(password, salt);

    // Compare the hashes
    return hashedPassword.equals(storedHash);
  }

  // Helper method to hash a password using SHA-256 with the provided salt
  private static String hashPasswordWithSalt(String password, byte[] salt)
    throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.update(salt); // Add the salt
    byte[] hashedBytes = digest.digest(password.getBytes());
    return Base64.getEncoder().encodeToString(hashedBytes);
  }

  // Helper method to generate a random salt
  private static byte[] generateSalt() {
    byte[] salt = new byte[SALT_LENGTH];
    new SecureRandom().nextBytes(salt);
    return salt;
  }
}
