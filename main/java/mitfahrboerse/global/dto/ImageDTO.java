package mitfahrboerse.global.dto;

/**
 * Data Transfer Object for image data (logos, profile pictures).
 * 
 * @author Anton Hollube
 */
public class ImageDTO {

    private long userId;
    private String mimetype;
    private byte[] data; 

    public ImageDTO(long userId, String mimetype, byte[] data) {
        this.userId = userId;
        this.mimetype = mimetype;
        this.data = data;
    }

    public long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user the image belongs to.
     * 
     * @param userId The valid user ID.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMimetype() {
        return mimetype;
    }

    /**
     * Sets the mimetype of the image.
     * 
     * @param mimetype The mimetype of the image.
     */
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public byte[] getData() {
        return data;
    }

    /**
     * Sets the image data.
     * 
     * @param data The data of the image.
     */
    public void setData(byte[] data) {
        this.data = data;
    }
}