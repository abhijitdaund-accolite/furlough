package com.accolite.furlough.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accolite.furlough.utils.Constants;

public class SendJavaMail {

    private final String fromEmail;
    private final String toEmail;
    private final String mailContent;
    private final String mailSubject;
    private final String password;
    private static final Logger logger = LoggerFactory.getLogger(SendJavaMail.class);

    public SendJavaMail(final String password, final String fromEmail, final String toEmail, final String mailSubject,
            final String mailContent) {
        this.password = password;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.mailContent = mailContent;
        this.mailSubject = mailSubject;
    }

    public SendJavaMail(final String toEmail, final StringBuilder finalString) {
        this.password = getPassword();
        this.fromEmail = "vignesh.b@accoliteindia.com";
        this.toEmail = toEmail;
        this.mailContent = String.valueOf(finalString);
        this.mailSubject = "Furlough Leaves Standard Subject Text";
    }

    private String getPassword() {
        return Constants.PASSWORD;
    }

    public void sendJavaMail() {

        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {

            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(mailSubject);
            message.setText(mailContent);

            Transport.send(message);
            logger.info("Email sent successfully to : {}", toEmail);

        } catch (final MessagingException e) {
            logger.error("Failed to send with error message : {}", e.getMessage());
        }
    }

}
