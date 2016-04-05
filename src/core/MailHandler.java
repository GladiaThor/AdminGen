/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Thor
 */
public class MailHandler {

    Person recipient, sender;
    String senderPassword, subject, senderUsername;
    Properties props;
    MimeBodyPart body;
    ArrayList<MimeBodyPart> attachments = new ArrayList();

    /**
     * THis initiates a handler for smtp.gmail.com on port 587
     */
    void MialHandler() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

    }

    /**
     * Sets the sender and recipients by using Person objects
     *
     * @param sender the sender person
     * @param recipient the recipient person
     * @param senderPassword the senders email password
     */
    public void setDetails(Person sender, Person recipient, String senderPassword) {
        this.senderPassword = senderPassword;
        this.sender = sender;
        this.recipient = recipient;
        senderUsername = sender.getMail().split("@")[0]; //because gmail uisernames are the same as teh email without gmail.com

    }

    public void createMessage(String subject, String greeting, String body, String farewell) throws MessagingException {
        this.subject = subject; //Set email subject
        ArrayList<String> bodyAL = new ArrayList();
        //Add greetings message (Default is Hej + first name
        if (greeting.equals("def")) {
            bodyAL.add("Hej " + recipient.getFirstName() + ", \n \n");
        } else {
            bodyAL.add(greeting);
        }

        //Add body
        bodyAL.add(body);

        //Create default farewell msg
        if (farewell.equals("def")) {
            bodyAL.add("MVH\n"
                    + sender.getFirstName() + " " + sender.getSurName() + "\n"
                    + sender.getPos() + ", " + sender.getSek() + "\n"
                    + sender.getTel() + "\n"
                    + sender.getTel());
        } else {
            bodyAL.add(farewell);
        }
        String bodifier = new String();
        for (int i = 0; i < bodyAL.size(); i++) {
            bodifier = bodifier + bodyAL.get(i);

        }
        this.body.setText(bodifier);
    }

    /**
     * Used for adding more than 1 attachmetn
     *
     * @param filepaths An arraylist of all attachmetns filepaths
     * @throws MessagingException
     */
    public void addAttachments(ArrayList<String> filepaths) throws MessagingException {
        for (int i = 0; i < filepaths.size(); i++) {
            MimeBodyPart body = new MimeBodyPart(); //Create a MimeBody
            DataSource attachmentSource = new FileDataSource(filepaths.get(i)); //datasource from filepath 1
            body.setDataHandler(new DataHandler(attachmentSource)); //Create datahandler from datasource
            String[] fullpath = filepaths.get(i).split(Pattern.quote(System.getProperty("file.separator"))); //get the full path as seperate strings
            body.setFileName(fullpath[fullpath.length - 1]); //get thhe name of the file
            attachments.add(body); //add to attachments AL
        } //and repeat untill no more filepaths left
    }

    /**
     * Used for adding 1 attachment
     *
     * @param filepath
     * @throws MessagingException
     */
    public void addAttachment(String filepath) throws MessagingException {
        MimeBodyPart body = new MimeBodyPart(); //Create a MimeBody
        DataSource attachmentSource = new FileDataSource(filepath); //datasource from filepath 1
        body.setDataHandler(new DataHandler(attachmentSource)); //Create datahandler from datasource
        String[] fullpath = filepath.split(Pattern.quote(System.getProperty("file.separator"))); //get the full path as seperate strings
        body.setFileName(fullpath[fullpath.length - 1]); //get thhe name of the file
        attachments.add(body); //add to attachments AL
    }

    /**
     * Creates a msg object and sends it
     *
     * @throws MessagingException
     */
    public void sendMail() throws MessagingException {
        // Create a Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderUsername, senderPassword);
                    }
                });

        //Ceata a multipart as the tempolate for the email
        Multipart multipart = new MimeMultipart();

        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        //Create mail header 
        // Set From:
        message.setFrom(new InternetAddress(sender.getMail()));

        // Set To: 
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient.getMail()));

        // Set Subject:
        message.setSubject(subject);

        //Add body to teh template multipart
        multipart.addBodyPart(body);

        for (int i = 0; i < attachments.size(); i++) { //Adding all the atachments to the multipart
            multipart.addBodyPart(attachments.get(i));
        }

        //Finally set the content if the message to the multipart
        message.setContent(multipart);
        // Send message
        Transport.send(message);

        System.out.println("Sent message successfully.");

    }

    public static void mailSend(String recipent, String sender, final String senderUsername, final String senderPassword, String subject, String body) {
        // Recipient's email ID needs to be mentioned.

        // Assuming you are sending email through gmail
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderUsername, senderPassword);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(sender));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipent));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(body);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void mailSendWithAttachment(String recipient, String sender, final String senderUserName, final String userPassword, String messageSubject, String messageBodyText, String attachmentLocation) {

        // Set host stmp 
        String host = "smtp.gmail.com";

        //Set properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Create datasource from file
        DataSource attachmentSource = new FileDataSource(attachmentLocation);

        // Create a Session object.
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderUserName, userPassword);
                    }
                });

        //Ceata a multipart as the tempolate for the email
        Multipart multipart = new MimeMultipart();

        //Create a bodypart with which to set email parts into the multi part
        BodyPart body = new MimeBodyPart();

        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        try {
            //Create mail header 
            // Set From:
            message.setFrom(new InternetAddress(sender));

            // Set To: 
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));

            // Set Subject:
            message.setSubject(messageSubject);

            //Add text to the body
            body.setText(messageBodyText);

            //Add body to teh template multipart
            multipart.addBodyPart(body);

            //Change the bodypart to the attachment
            body = new MimeBodyPart();
            body.setDataHandler(new DataHandler(attachmentSource));
            body.setFileName(attachmentLocation);

            //Now add the new part to the multipart
            multipart.addBodyPart(body);

            //Finally set the content if the message to the multipart
            message.setContent(multipart);
            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
