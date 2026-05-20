package com.infodart.fitzen.util;


import java.util.Properties;
import java.util.Properties;
/*import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;*/
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSender {
	
	final String username = "healthalert@infodartmail.com";
    final String password = "health@123";
	
	public void sendEmail(String to, String subject, String mailBody) {
		
		
 
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.rediffmailpro.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
 
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
 
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            //message.setSubject("Test Email from Rediffmail Pro");
            //message.setText("Hello, this is a test email sent using Rediffmail Pro SMTP.");
            
            message.setSubject("FitZen : Heart Rate Alert");
            message.setText(""+ mailBody);
 
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
}
