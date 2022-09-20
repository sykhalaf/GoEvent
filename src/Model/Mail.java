package Model;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import CrudEvenement.EvenementsController;

public class Mail {

    //SETUP MAIL SERVER PROPERTIES
    //DRAFT AN EMAIL
    //SEND EMAIL
    Session newSession = null;
    MimeMessage mimeMessage = null;

    public static void main(String args[]) throws AddressException, MessagingException, IOException {
        Mail mail = new Mail();
        mail.setupServerProperties();

        mail.sendEmail();
    }

    public void sendEmail() throws MessagingException {
        String fromUser = "manoubi.marwa@esprit.tn";  //Enter sender email id
        String fromUserPassword = "E13627950";  //Enter sender gmail password , this will be authenticated by gmail smtp server
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent!!!");
    }

    public MimeMessage draftEmail(String mail, String nom,String nom_event, String lieux_event ,Integer id_user) throws AddressException, MessagingException, IOException {
        String[] emailReceipients = {mail};  //Enter list of email recepients
        String emailSubject = "pour puls de details";

        mimeMessage = new MimeMessage(newSession);

        for (int i = 0; i < emailReceipients.length; i++) {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
        }
        mimeMessage.setFrom(new InternetAddress("Information sur le lieu"));
        mimeMessage.setSubject(emailSubject);

        // CREATE MIMEMESSAGE 
        // CREATE MESSAGE BODY PARTS 
        // CREATE MESSAGE MULTIPART 
        // ADD MESSAGE BODY PARTS ----> MULTIPART 
        // FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object 
        Multipart emailContent = new MimeMultipart();

        //Text body part
        MimeBodyPart textBodyPart = new MimeBodyPart();

        textBodyPart.setText(" Bonsoir Monsieur/Madame " + nom+ "c'est ou exactement le lieu de cette evenement? " + nom_event +lieux_event +id_user);

        //Attachment body part.
        //Attach body parts
        emailContent.addBodyPart(textBodyPart);

        //Attach multipart to message
        mimeMessage.setContent(emailContent);
        return mimeMessage;
    }

    public void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
    }

    public void draftEmail(Type String) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}