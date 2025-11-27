package mitfahrboerse.global.dto;

import java.nio.file.Path;
import java.time.Duration;
import java.time.Period;

/**
 * Data Transfer Object for global system settings.
 *
 * @author Anton Hollube
 */
public class SystemSettingsDTO {

    private ImageDTO logo;
    private String enterpriseName;
    private Duration cancelDeadlineHours;
    private Period rideCleanupDays;
    private Period publishingBeforeDrive;
    private String imprint;
    private String privacyPolicy;
    private long settingsId;
    private Path pathToCss;

    public SystemSettingsDTO(ImageDTO logo, String enterpriseName, Duration cancelDeadlineHours, Period rideCleanupDays, Period publishingBeforeDrive, String imprint, String privacyPolicy, long settingsId, Path pathToCss) {
        this.logo = logo;
        this.enterpriseName = enterpriseName;
        this.cancelDeadlineHours = cancelDeadlineHours;
        this.rideCleanupDays = rideCleanupDays;
        this.publishingBeforeDrive = publishingBeforeDrive;
        this.imprint = imprint;
        this.privacyPolicy = privacyPolicy;
        this.settingsId = settingsId;
        this.pathToCss = pathToCss;
    }

    public ImageDTO getLogo() {
        return logo;
    }

    /**
     * Sets the logo that will be displayed on the platform.
     * 
     * @param logo The image logo for the platform.
     */
    public void setLogo(ImageDTO logo) {
        this.logo = logo;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    /**
     * Sets the name of the enterprise to be displayed on the platform.
     * 
     * @param enterpriseName The name of the enterprise.
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Duration getCancelDeadlineHours() {
        return cancelDeadlineHours;
    }

    /**
     * Sets the time before a ride departure when changes can not be made anymore.
     * 
     * @param cancelDeadlineHours The duration before the ride departure.
     */
    public void setCancelDeadlineHours(Duration cancelDeadlineHours) {
        this.cancelDeadlineHours = cancelDeadlineHours;
    }

    public Period getRideCleanupDays() {
        return rideCleanupDays;
    }

    /**
     * Sets the period when rides from the past will be removed from the system.
     * 
     * @param rideCleanupDays The period of the cleanup.
     */
    public void setRideCleanupDays(Period rideCleanupDays) {
        this.rideCleanupDays = rideCleanupDays;
    }

    public Period getPublishingBeforeDrive() {
        return publishingBeforeDrive;
    }

    /**
     * Sets the general time rides will be published before taking place.
     * 
     * @param publishingBeforeDrive The period before publishing.
     */
    public void setPublishingBeforeDrive(Period publishingBeforeDrive) {
        this.publishingBeforeDrive = publishingBeforeDrive;
    }

    public String getImprint() {
        return imprint;
    }

    /**
     * Sets the imprint of the platform.
     * 
     * @param imprint The imprint.
     */
    public void setImprint(String imprint) {
        this.imprint = imprint;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    /**
     * Sets the privacy policy of the platform.
     * 
     * @param privacyPolicy The privacy policy.
     */
    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public long getSettingsId() {
        return settingsId;
    }

    /**
     * Sets the ID for the corresponding settings.
     * 
     * @param settingsId The settings' ID.
     */
    public void setSettingsId(long settingsId) {
        this.settingsId = settingsId;
    }

    public Path getPathToCss() {
        return pathToCss;
    }

    /**
     * Sets the path to the CSS files.
     * 
     * @param pathToCss The file path to the CSS files.
     */
    public void setPathToCss(Path pathToCss) {
        this.pathToCss = pathToCss;
    }
}