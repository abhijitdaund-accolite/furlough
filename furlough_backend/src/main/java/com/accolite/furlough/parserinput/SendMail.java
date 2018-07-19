package com.accolite.furlough.parserinput;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class SendMail {

    public static final String API = "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";
    private final String fromEmail;
    private final String toEmail;
    private final String mailContent;
    private final String mailSubject;
    private final static Logger logger = LoggerFactory.getLogger(SendMail.class);

    public SendMail(final String toEmail, final String mailContent) {
        this.fromEmail = "raunak.maheshwari@accoliteindia.com";
        this.toEmail = toEmail;
        this.mailContent = mailContent;
        this.mailSubject = "Furlough Leaves Standard Subject Text";
    }

    public SendMail(final String fromEmail, final String toEmail, final String mailSubject, final String mailContent) {
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.mailContent = mailContent;
        this.mailSubject = mailSubject;
    }

    public void sendMail() throws IOException {
        final Email from = new Email(fromEmail);
        final String subject = mailSubject;
        final Email to = new Email(toEmail);
        final Content content = new Content("text/plain", mailContent);
        final Mail mail = new Mail(from, subject, to, content);

        final SendGrid sg = new SendGrid(API);
        final Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            final Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (final IOException ex) {
            logger.error("Failed with error message : " + ex.getMessage());
            // throw ex;
        }
    }

    // public static void main(final String[] args) throws IOException {
    // final SendMail m = new SendMail("raunak.maheshwari@accoliteindia.com",
    // "finalString");
    // m.sendMail();
    // }
}
