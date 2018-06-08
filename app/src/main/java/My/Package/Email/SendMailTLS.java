/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package My.Package.Email;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Bert
 */
public class SendMailTLS {

    public SendMailTLS(String subject, String adress, String content, Context context) {

        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        final String emailadress = p.getString("username", null);
        final String password = p.getString("password", null);


        Log.v("wachtwoord opgehaald", "true");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailadress, password);
            }
        });

        Log.v("wachtwoord auth", "true");

        try {

            Message message = new MimeMessage(session);
            Log.v("object gemaakt", "true");
            message.setFrom(new InternetAddress(emailadress));
            Log.v("waar gaat het mis, na", "eigen adres");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(adress));
            Log.v("waar gaat het mis, na", "ander adres");
            message.setSubject(subject);
            Log.v("waar gaat het mis, na", "onderwerp");
            message.setText(content);

            Log.v("bericht gemaakt", "true");
            Transport.send(message);


        } catch (MessagingException e) {
            Log.v("bericht gemaakt", "false");
            //throw new RuntimeException(e);
        }
    }
}
