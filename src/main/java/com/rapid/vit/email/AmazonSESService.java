package com.rapid.vit.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmazonSESService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonSESService.class);
    private final SESMailConfig sesMailConfig;

    // Replace sender@example.com with your "From" address.
    // This address must be verified.
     //String FROMNAME = sesMailConfig.getSenderName() ;

    // Replace recipient@example.com with a "To" address. If your account
    // is still in the sandbox, this address must be verified.

    // Replace smtp_username with your Amazon SES SMTP user name.
//    String SMTP_USERNAME = sesMailConfig.getUsername();

    // Replace smtp_password with your Amazon SES SMTP password.
//    String SMTP_PASSWORD = sesMailConfig.getPassword();

    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
//    String CONFIGSET = sesMailConfig.getConfigSet();

    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
//    String HOST = sesMailConfig.getMailHost();

    // The port you will connect to on the Amazon SES SMTP endpoint.
//    int PORT = sesMailConfig.getPort();

    @Autowired
    public AmazonSESService(SESMailConfig sesMailConfig) {
        this.sesMailConfig = sesMailConfig;
    }

    public void sendEmail(String FROM, String FROMNAME, String TO, String SUBJECT, String BODY) throws MessagingException, UnsupportedEncodingException {
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", sesMailConfig.getPort());
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY,"text/html");

        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", sesMailConfig.getConfigSet());

        // Create a transport.

        // Send the message.
        try (Transport transport = session.getTransport()) {// Close and terminate the connection.
            LOGGER.info("Email is sending");

            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(sesMailConfig.getMailHost(), sesMailConfig.getUsername(), sesMailConfig.getPassword());

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            LOGGER.info("Email sent!");
        } catch (Exception ex) {
            LOGGER.error("The email was not sent.");
            LOGGER.error("Error message: " + ex.getMessage());
        }
    }
}
