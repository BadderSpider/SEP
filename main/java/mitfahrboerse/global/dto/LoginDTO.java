package mitfahrboerse.global.dto;


/**
 * Data Transfer Object for encapsulating user login input.
 * 
 * @author Kerstin Arnoczky
 */
public class LoginDTO {

    // Only used by the view
	private String email;
	private String password;

    public String getEmail() {
        return email;
    }

    /**
     * Sets the input email.
     * 
     * @param email The input email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Sets the from the input already hashed password.
     * 
     * @param password The hashed password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
