package mitfahrboerse.model.dataaccess.util;

import java.util.Properties;

/**
 * Holds configuration parameters for the SMTP Mail server.
 *
 * @author Matthias Schmitt
 */
public final class MailConfig {

    private final String smtpHost;
    private final String smtpPort;
    private final boolean smtpAuth;
    private final boolean sslEnable;
    private final boolean startTlsEnable;
    private final String socketFactoryClass;
    private final String transportProtocol;
    private final boolean debug;

    private final String user;
    private final String password;
    private final String fromAddress;
    private final String displayName;

    public MailConfig(String smtpHost, String smtpPort, boolean smtpAuth,
                      boolean sslEnable, boolean startTlsEnable,
                      String socketFactoryClass, String transportProtocol, boolean debug,
                      String user, String password, String fromAddress, String displayName) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.smtpAuth = smtpAuth;
        this.sslEnable = sslEnable;
        this.startTlsEnable = startTlsEnable;
        this.socketFactoryClass = socketFactoryClass;
        this.transportProtocol = transportProtocol;
        this.debug = debug;
        this.user = user;
        this.password = password;
        this.fromAddress = fromAddress;
        this.displayName = displayName;
    }

    /**
     * Creates and returns a java.util.Properties object.
     * @return A Properties object with all settings.
     */
    public Properties getMailSessionProperties() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", this.transportProtocol);
        props.put("mail.smtp.host", this.smtpHost);
        props.put("mail.smtp.port", this.smtpPort);
        props.put("mail.smtp.auth", String.valueOf(this.smtpAuth));

        props.put("mail.smtp.ssl.enable", String.valueOf(this.sslEnable));
        props.put("mail.smtp.starttls.enable", String.valueOf(this.startTlsEnable));

        if (this.sslEnable && this.socketFactoryClass != null && !this.socketFactoryClass.isEmpty()) {
            props.put("mail.smtp.socketFactory.port", this.smtpPort);
            props.put("mail.smtp.socketFactory.class", this.socketFactoryClass);
        }

        props.put("mail.debug", String.valueOf(this.debug)); //Zur Entwicklung, kann später auf false sein toDo

        return props;
    }

    public String getUser() { return user; }
    public String getPassword() { return password; }
    public String getFromAddress() { return fromAddress; }
    public String getDisplayName() { return displayName; }
}