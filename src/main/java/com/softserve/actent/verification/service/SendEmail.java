package com.softserve.actent.verification.service;

import com.softserve.actent.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Slf4j
@Service
public class SendEmail {

    public boolean sendSimpleEmail(String email, User user){

        String confirmUrl = "http://localhost/confirm?login=" + user.getLogin() + "&uuid=" + user.getUuid();

        final Properties properties = new Properties();
        try {
            properties.load(SendEmail.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException e) {
            log.error("Cant download main.properties file");
        }

        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("onlyTwo2111@gmail.com", "qwert1234asd");
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            String to = email;

            InternetAddress[] address = InternetAddress.parse(to, true);
            message.setRecipients(Message.RecipientType.TO, address);
            message.setFrom();
            message.setSubject("Verification mail");
            message.setSentDate(new Date());
            message.setText("For being an activated user in Actent community and have all advantages of our service, " +
                    "you need to click on the link below. \n \n" + confirmUrl + "\n\nWish you success!\nSincerely yourth, Actent's team");

            Transport.send(message);
            log.info("Message successfully sent");
            return true;
        }catch (MessagingException mex){
            log.error("Unable to send an email" + mex);
            return false;
        }
    }

}
