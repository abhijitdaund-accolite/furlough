package com.accolite.furlough.parserinput;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendJavaMail {

    private final String fromEmail;
    private final String toEmail;
    private final String mailContent;
    private final String mailSubject;
    private final String password;

    public SendJavaMail(final String password, final String fromEmail, final String toEmail, final String mailSubject,
            final String mailContent) {
        this.password = password;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.mailContent = mailContent;
        this.mailSubject = mailSubject;
    }

    public void sendJavaMail() {

        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

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

            System.out.println("Sent the email Successfully!");

        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
