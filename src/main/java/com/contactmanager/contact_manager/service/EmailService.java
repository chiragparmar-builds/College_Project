package com.contactmanager.contact_manager.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    public boolean sendEmail(String subject,String message,String to){


        boolean f= false;
        // Address form1 = "waterbottle06032003@gmail.com";
        String form = "waterbottle06032003@gmail.com";

        String host ="smtp.gmail.com";

        Properties properties = System.getProperties();
        // System.out.println(properties);


        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        // Step 1
        Session session = Session.getInstance(properties,new Authenticator() {
           
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("waterbottle06032003@gmail.com", "oxgb tfcn qnks colw");
            }
        });
        // Session session = Session.getInstance(properties);
        
        session.setDebug(true);
        
        // Step 2

        MimeMessage mimeMessage = new MimeMessage(session);
        
        try {
            mimeMessage.setFrom(new InternetAddress(form));
            
            mimeMessage.addRecipient(Message.RecipientType.TO , new InternetAddress(to));

            mimeMessage.setSubject(subject);

            mimeMessage.setText(message);

            // System.out.println("Successfull");

            Transport.send(mimeMessage);

            // System.out.println("Successfull");
            f=true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending email:   " + e.getMessage());
        }

        return f;
    }
}
