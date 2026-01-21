package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendEmail {

    public void mandarCorreo() {
        // El correo gmail de envío
        String correoEnvia = "josearanda9000@gmail.com";
        // La contraseña google
        String claveCorreo = "mbgn mzpp phoj knlf";

        // Destinatarios del correo
        String[] destinatarios = {
                "benje1612@gmail.com",
                "josearanda9000@gmail.com"
        };

        // Asunto y cuerpo del mensaje
        String asunto = "Saludos desde Java";
        String cuerpoMensaje = "Hola,\n\nEste es un correo enviado desde una aplicación Java utilizando JavaMail.\n\n¡Saludos!";


        // La configuración para enviar correo
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties. put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Crear la sesión con autenticación
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEnvia, claveCorreo);
            }
        });

        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);

            // Agregar quien envía el correo
            mimeMessage. setFrom(new InternetAddress(correoEnvia));

            // Los destinatarios
            InternetAddress[] internetAddresses = new InternetAddress[destinatarios. length];
            for (int i = 0; i < destinatarios.length; i++) {
                internetAddresses[i] = new InternetAddress(destinatarios[i]);
            }

            // Agregar los destinatarios al mensaje
            mimeMessage. setRecipients(Message.RecipientType.TO, internetAddresses);

            // Agregar el asunto al correo
            mimeMessage.setSubject(asunto, "UTF-8");

            // Crear la parte del mensaje
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText(cuerpoMensaje, "UTF-8");

            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport. send(mimeMessage);

            System.out.println(" Correo enviado exitosamente desde " + correoEnvia);
            System.out.println(" Destinatarios: ");
            for (String dest : destinatarios) {
                System.out.println("   - " + dest);
            }

        } catch (MessagingException ex) {
            System.err.println(" Error al enviar el correo:");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SendEmail correoTexto = new SendEmail();
        correoTexto.mandarCorreo();
    }
}