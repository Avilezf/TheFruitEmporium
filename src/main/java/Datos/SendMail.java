package Datos;

import Dominio.Pedido;
import com.sun.mail.util.MailSSLSocketFactory;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import net.sf.jasperreports.engine.JRException;

public class SendMail {

    static String pdf = "";

    public static void sendValidator(String to, Pedido pedido) throws Exception {

        // Recipient's email ID needs to be mentioned.
        //to = "TheFruitEmporiumCo@gmail.com";
        // Sender's email ID needs to be mentioned
        String from = "TheFruitEmporiumCo@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host); // 
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");

        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.socketFactory", sf);

        // Get the Session object.// and pass 
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("TheFruitEmporiumCo@gmail.com", "llanosperez");
            }

        });

        Message message = prepareMessage(session, from, to);

        pdf = Reporte.generarReporte(pedido);
        MimeBodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText("Hola!  \n \nAcabamos de validar tu pedido gracias por preferir The Fruit Emporium, un unos momentos estaremos haciendo su pedido para enviarlo. \n \nLe adjuntamos así mismo la factura. \n \nPara más información o soporte acerca de su pedido, contacte al +57 300 401 9873. \n \nMuchas Gracias!");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = pdf;
        String fileName = "Factura.pdf";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart1);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("sending...");
        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");
        
        sendPedido("TheFruitEmporiumCo@gmail.com", pedido);

    }

    public static void sendSended(String to) throws Exception {

        // Recipient's email ID needs to be mentioned.
        //to = "TheFruitEmporiumCo@gmail.com";
        // Sender's email ID needs to be mentioned
        String from = "TheFruitEmporiumCo@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host); // 
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");

        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.socketFactory", sf);

        // Get the Session object.// and pass 
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("TheFruitEmporiumCo@gmail.com", "llanosperez");
            }

        });

        Message message = prepareMessage2(session, from, to);
        System.out.println("sending...");
        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");

    }

    private static Message prepareMessage(Session session, String from, String to) {
        session.setDebug(true);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Su pedido ya ha sido valido!");
            message.setText("Hola!  \n \nAcabamos de validar tu pedido gracias por preferir The Fruit Emporium, un unos momentos estaremos haciendo su pedido para enviarlo. \n \nPara más información o soporte acerca de su pedido, contacte al +57 300 401 9873. \n \nMuchas Gracias!");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static Message prepareMessage2(Session session, String from, String to) {
        session.setDebug(true);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Su pedido ya ha sido enviado!");
            message.setText("Hola!  \n \nSu pedido ya está listo para enviar, muy pronto nuestro personal lo llevará a su domicilio. \n \nPara más información o soporte acerca de su pedido, contacte al +57 300 401 9873. \n \n \nMuchas Gracias!");
//            try {
//  //Reporte factura
//                File f =new File("H:\\pepipost_tutorials\\javaemail1.PNG");
//
//                attachmentPart.attachFile(f);
//                textPart.setText("This is text");
//                multipart.addBodyPart(textPart);
//                multipart.addBodyPart(attachmentPart);
//
//            } catch (IOException e) {
//
//                e.printStackTrace();
//
//            }
            return message;
        } catch (Exception ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void sendPedido(String to, Pedido pedido) throws MessagingException, JRException, SQLException {
        // Recipient's email ID needs to be mentioned.
        //to = "TheFruitEmporiumCo@gmail.com";
        // Sender's email ID needs to be mentioned
        String from = "TheFruitEmporiumCo@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host); // 
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");

        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.socketFactory", sf);

        // Get the Session object.// and pass 
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("TheFruitEmporiumCo@gmail.com", "llanosperez");
            }

        });

        Message message = prepareMessage3(session, from, to, pedido);

        MimeBodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText("Hola!  \n \nAdjuntamos aqui la factura del pedido "+pedido.getIdPedido()+"\n \nMuchas Gracias!  \n \nMateo 6:34: \"Por lo tanto, no se angustien por el mañana, el cual tendrá sus propios afanes. Cada día tiene ya sus problemas.\" ");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = pdf;
        String fileName = "Factura.pdf";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart1);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("sending...");
        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");

    }

    private static Message prepareMessage3(Session session, String from, String to, Pedido pedido) {
        session.setDebug(true);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Se ha enviado un pedido " + pedido.getIdPedido());
            message.setText("Hola!  \n \nSe ha enviado un nuevo pedido = " + pedido.getIdPedido() + "\n \nhttp://35.209.198.99:8080/TheFruitEmporium/AdminController?accion=pedidos \n \nMuchas Gracias!");
//            try {
//  //Reporte factura
//                File f =new File("H:\\pepipost_tutorials\\javaemail1.PNG");
//
//                attachmentPart.attachFile(f);
//                textPart.setText("This is text");
//                multipart.addBodyPart(textPart);
//                multipart.addBodyPart(attachmentPart);
//
//            } catch (IOException e) {
//
//                e.printStackTrace();
//
//            }
            return message;
        } catch (Exception ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
