package mitfahrboerse.model.business.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import mitfahrboerse.global.dto.MailDTO;
import mitfahrboerse.model.business.exception.BusinessException;
import mitfahrboerse.model.business.util.MailConfig;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Infrastructure service for email sending.
 *
 * @author Matthias Schmitt
 */
@ApplicationScoped
public class MailService {

    private MailConfig mailConfig;
    private Session mailSession;

    private MailService() {
    }

    /**
     * Initializes the mail service configuration and session.
     */
    @PostConstruct
    public void init() {

        this.mailConfig = null; //toDo load config

        if (this.mailConfig == null) {
            System.err.println("ERROR: MailConfig could not be loaded.");
            return;
        }

        Properties props = mailConfig.getMailSessionProperties();

        this.mailSession = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfig.getUser(), mailConfig.getPassword());
            }
        });
    }

    /**
     * Sends a pre-formatted email.
     *
     * @param mailDto The DTO containing recipient, subject, and body.
     * @throws BusinessException If the email cannot be sent.
     */
    public void sendMail(MailDTO mailDto) throws BusinessException {
        if (this.mailSession == null) {
            throw new BusinessException("MailService is not available (not initialized).");
        }

        try {
            Message message = new MimeMessage(this.mailSession);
            message.setFrom(new InternetAddress(mailConfig.getFromAddress(), mailConfig.getDisplayName()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDto.getAddress()));
            message.setSubject(mailDto.getSubject());
            message.setText(mailDto.getMessage());

            Transport.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            //toDo logging
            throw new BusinessException("ERROR in sendMail()",e);
        }
    }
}