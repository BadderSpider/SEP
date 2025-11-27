package mitfahrboerse.global.dto;

/**
 * Data Transfer Object for encapsulating user registration input. 
 * 
 * @author René Schmaderer
 */
public class RegistrationDTO {

	private String firstName;
	private String lastName;
	private String email;
    private String phoneNumber;
	private AddressDTO address;

    // Only used by the view.
    private String password;
    private String passwordRepeat;

    // Used by the Database
    PasswordDTO passwordDTO;

    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user to be registered.
     * 
     * @param firstName The first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user to be registered.
     * 
     * @param lastName The last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user to be registered.
     * 
     * @param email The email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user to be registered.
     * 
     * @param phoneNumber The phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDTO getAddress() {
        return address;
    }

    /**
     * Sets the address of the user to be registered.
     * 
     * @param address The address.
     */
    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Sets the raw password of the user to be registered.
     * 
     * @param password The password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    /**
     * Sets the repeated password that needs to match the already given password.
     * 
     * @param passwordRepeat The repeated password.
     */
    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public PasswordDTO getPasswordDTO() {
        return passwordDTO;
    }

    public void setPasswordDTO(PasswordDTO passwordDTO) {
        this.passwordDTO = passwordDTO;
    }
}
