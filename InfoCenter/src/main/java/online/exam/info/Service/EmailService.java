package online.exam.info.Service;

import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
@Component
public class EmailService {
    public static final String myEmailAccount = "s402621342@126.com";
    public static final String myEmailPassword = "njusoftware1403";
    public static final String myEmailSMTPHost = "smtp.126.com";

    public boolean sendmessage(String email,String title,String content){

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        Session session= Session.getInstance(props);
        try {
            Transport transport = session.getTransport();
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmailAccount, "在线考试系统", "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email, email, "UTF-8"));
            message.setSubject(title, "UTF-8");
            message.setContent(content, "text/html;charset=UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();
            transport.connect(myEmailAccount, myEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
