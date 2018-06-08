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

/**
 *
 * @author Bert
 */
public class Email extends Activity {

    /**
     * Called when the activity is first created.
     */
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here 
        setContentView(R.layout.write_mail);

    }
    
    public void email(View view) {
        EditText subject = (EditText) findViewById(R.id.subject);
        EditText adress = (EditText) findViewById(R.id.receiver);
        EditText message = (EditText) findViewById(R.id.message);
        
        new SendMailTLS(subject.getEditableText().toString(),adress.getEditableText().toString(), message.getEditableText().toString(), this);
        
        super.finish();

    }
    
}
