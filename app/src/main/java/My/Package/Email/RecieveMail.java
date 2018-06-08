/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package My.Package.Email;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author Bert
 */
public class RecieveMail {

    private String userName;
    private String password;
    private String receivingHost;
    private DatabaseHandler dbh;
    private Context context;

    public RecieveMail(Context context) {
        Log.v("aangemaakt", "true");
        dbh = new DatabaseHandler(context);
        this.context = context;
        setAccountDetails();
        readGmail();
    }

    private void setAccountDetails() {

        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(context);
        userName = p.getString("username", null);
        password = p.getString("password", null);


    }

    private void readGmail() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        /*this will print subject of all messages in the inbox of sender@gmail.com*/
        this.receivingHost = "imap.gmail.com";//for imap protocol

        Properties props2 = System.getProperties();

        props2.setProperty("mail.store.protocol", "imaps");
        // I used imaps protocol here
        Session session2 = Session.getDefaultInstance(props2, null);

        try {
            Log.v("Mail try", "true");
            Store store = session2.getStore("imaps");
            store.connect(this.receivingHost, this.userName, this.password);

            Folder folder = store.getFolder("Inbox");//get inbox
            folder.open(Folder.READ_ONLY);//open folder only to read

            Message[] message = folder.getMessages();

            Log.v("Mail opgehaald", "true");
            Log.v("Mails", String.valueOf(message.length));

            for (int i = 0; i < 50; i++) {
                //put into database
                Log.v("message", Integer.toString(i));
                dbh.addMessage(message[i]);

            }
            //close connections
            folder.close(true);
            store.close();

        } catch (Exception e) {
            System.out.println(e.toString());
            Log.v("Mail try", "false");
        }

    }
}
