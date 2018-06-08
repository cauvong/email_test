/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package My.Package.Email;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.List;

/**
 *
 * @author Bert
 */
public class Reader extends Activity {

     /**
     * Called when the activity is first created.
     */
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here 
        setContentView(R.layout.read_mail);
        
        Bundle extra = getIntent().getExtras();
        int mail = extra.getInt("mail");
        
        DatabaseHandler dbh = new DatabaseHandler(MainActivity.context);
        List<Mail> mails = dbh.getAllMessages();
        
        EditText sender = (EditText) findViewById(R.id.reader_sender);
        EditText subject = (EditText) findViewById(R.id.reader_subject);
        EditText content = (EditText) findViewById(R.id.reader_message);
        
        sender.setText(mails.get(mail).getSender());
        subject.setText(mails.get(mail).getSubject());
        content.setText(mails.get(mail).getText());

    }
    
    public void email(View view) {
        Intent intent = new Intent(this, Inbox.class);
        startActivity(intent);

    }
}
