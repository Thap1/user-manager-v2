package rmhub.user.management.service.email;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import rmhub.user.management.model.EngineeringBureau;
import rmhub.user.management.model.Role;

public class SendEmail {
  public void sendPassword(String toEmail, String password) throws AddressException, MessagingException, IOException {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
       protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication("lelinh25071991@gmail.com", "lehong123");
       }
    });
    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress("lelinh25071991@gmail.com", false));

    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
    msg.setSubject("RMHUB send your new password");
    msg.setContent("Changing password success. Your new password: " + password , "text/html");
    msg.setSentDate(new Date());
//    String url="http://localhost:8080/#/activateuser/5575/958104f7-557e-4703-bcf7-55c9e37b7ad7";
//    String content="<a href='"+url+"'>"+url+"</a>";
//    msg.setContent("Click link after to get password"+" "+content,"text/html; charset=utf-8");

    // sends the e-mail
//    Transport.send(msg);


//    MimeBodyPart messageBodyPart = new MimeBodyPart();
//    messageBodyPart.setContent("Tutorials point email", "text/html");

//    Multipart multipart = new MimeMultipart();
//    multipart.addBodyPart(messageBodyPart);
//    MimeBodyPart attachPart = new MimeBodyPart();
//
//    attachPart.attachFile("/var/tmp/image19.png");
//    multipart.addBodyPart(attachPart);
//    msg.setContent(multipart);
    Transport.send(msg); 
  }
  
  public void sendLink(String email, String token) throws AddressException, MessagingException {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
       protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication("lelinh25071991@gmail.com", "lehong123");
       }
    });
    
    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress("lelinh25071991@gmail.com", false));

    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
    msg.setSubject("RMHUB send your new password");
  String url="http://localhost:8080/api/v1/reset-pw/" + token;
  String content="<a href='"+url+"'>"+url+"</a>";
  msg.setContent("Click link after to get password"+" "+content,"text/html; charset=utf-8");
    msg.setSentDate(new Date());
//    String url="http://localhost:8080/#/activateuser/5575/958104f7-557e-4703-bcf7-55c9e37b7ad7";
//    String content="<a href='"+url+"'>"+url+"</a>";
//    msg.setContent("Click link after to get password"+" "+content,"text/html; charset=utf-8");

    // sends the e-mail
//    Transport.send(msg);


//    MimeBodyPart messageBodyPart = new MimeBodyPart();
//    messageBodyPart.setContent("Tutorials point email", "text/html");

//    Multipart multipart = new MimeMultipart();
//    multipart.addBodyPart(messageBodyPart);
//    MimeBodyPart attachPart = new MimeBodyPart();
//
//    attachPart.attachFile("/var/tmp/image19.png");
//    multipart.addBodyPart(attachPart);
//    msg.setContent(multipart);
    Transport.send(msg); 
  }
}
