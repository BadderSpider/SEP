package mitfahrboerse.global.dto;

/**
 * Data Transfer Object for encapsulating basic address details such as street and street number.
 *
 * @author Kerstin Arnoczky
 */
public class AddressDTO {

	private String country;
	private String city;
	private String PLZ;
	private String street;
	private String streetNumber;

    public AddressDTO(String country, String town, String PLZ, String street, String streetNumber) {
        this.country = country;
        this.city = town;
        this.PLZ = PLZ;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the address.
     * 
     * @param country The country of the address.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the address.
     * 
     * @param city The city of the address.
     */
    public void setCity(String city) {
        this.city = city;
    }

    public String getPLZ() {
        return PLZ;
    }

    /**
     * Sets the zip code of the address.
     * 
     * @param PLZ The zip code of the address.
     */
    public void setPLZ(String PLZ) {
        this.PLZ = PLZ;
    }

    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of the address.
     * 
     * @param street The street of the address.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Sets the street number of the address.
     * 
     * @param streetNumber The street number of the address.
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}
