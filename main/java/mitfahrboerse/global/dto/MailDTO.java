package mitfahrboerse.global.dto;

/**
 * Data Transfer Object representing a complete email that is ready to be sent.
 * 
 * @author Matthias Schmitt
 */
public class MailDTO {
    private String address;
    private String subject;
    private String message;

    public MailDTO(String address, String subject, String message) {
        this.address = address;
        this.subject = subject;
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Sets the receiver of the email.
     * 
     * @param address The valid email-address of the receiver.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email.
     * 
     * @param subject The subject of the email.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the email that is to be sent.
     * 
     * @param message The email message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
