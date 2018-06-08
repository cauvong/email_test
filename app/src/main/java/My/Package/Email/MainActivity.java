package My.Package.Email;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;

public class MainActivity extends Activity {

    private RecieveMail rm;
    public static Context context;
    
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.v("lookin for file", "true");
        //File f = new File(
        //        "/data/data/My.Package.Email/shared_prefs/My.Package.Email_preferences.xml");
        if (new File("/data/data/My.Package.Email/shared_prefs/My.Package.Email_preferences.xml").exists()) {
            Log.d("TAG", "SharedPreferences Name_of_your_preference : exist");
            context = this;
            rm = new RecieveMail(this);
            
        } else {
            Log.d("TAG", "Setup default preferences");
            Intent intent = new Intent(this, Settings.class);
            intent.putExtra("firstRun", true);
            startActivity(intent);
        }
    }

    /**
     * opent schrijf een email
     *
     * @param view
     */
    public void email(View view) {
        Intent intent = new Intent(this, Email.class);
        startActivity(intent);

    }

    public void inbox(View view) {
        Intent intent = new Intent(this, Inbox.class);
        startActivity(intent);

    }

    public void contacts(View view) {
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);

    }

    public void settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("firstRun", false);
        startActivity(intent);

    }
}
