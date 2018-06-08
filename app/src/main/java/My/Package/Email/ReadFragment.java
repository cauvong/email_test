/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package My.Package.Email;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import java.util.List;

/**
 *
 * @author Bert
 */
public class ReadFragment extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    private View v;
    private DatabaseHandler dbh;
    private List<Mail> mails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        dbh = new DatabaseHandler(MainActivity.context);
        mails = dbh.getAllMessages();
        
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.read_mail, container, false);
        return v;
    }
    
    public void selectMail(int i){
        EditText sender = (EditText) v.findViewById(R.id.reader_sender);
        EditText subject = (EditText) v.findViewById(R.id.reader_subject);
        EditText content = (EditText) v.findViewById(R.id.reader_message);
        
        sender.setText(mails.get(i).getSender());
        subject.setText(mails.get(i).getSubject());
        content.setText(mails.get(i).getText());

    }
}