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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 *
 * @author Bert
 */
public class Settings extends Activity {

    /**
     * Called when the activity is first created.
     */
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here 
        setContentView(R.layout.settings);

        Bundle extra = getIntent().getExtras();
        Boolean firstRun = extra.getBoolean("firstRun");

        if (firstRun) {
            Log.v("firstRun", "true");
            TextView tv = (TextView) findViewById(R.id.settings_text);
            tv.setText(R.string.settings_firstrun);
            Button terug = (Button) findViewById(R.id.settings_terug);
            terug.setVisibility(View.GONE);
            Button save = (Button) findViewById(R.id.settings_edit);
            save.setText("Save");
            
        } else {
            Log.v("firstRun", "false");
            SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(this);
            String emailadress = p.getString("username", null);
            String password = p.getString("password", null);

            EditText email = (EditText) findViewById(R.id.settings_email);
            EditText pass = (EditText) findViewById(R.id.settings_password);
            
            email.setText(emailadress);
            pass.setText(password);
        }
        

    }

    public void terug(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void edit(View view) {
        EditText email = (EditText) findViewById(R.id.settings_email);
        String emailadress = (String) email.getText().toString();

        EditText pass = (EditText) findViewById(R.id.settings_password);
        String password = (String) pass.getText().toString();

        EmailPass ep = new EmailPass(emailadress, password);

        changePassword(ep);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = sp.edit();
        editor.putString("username", emailadress);
        editor.putString("password", password);
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void changePassword(EmailPass ep) {
        FileOutputStream fos;

        try {
            fos = openFileOutput("EmailObject", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(ep);
            os.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
