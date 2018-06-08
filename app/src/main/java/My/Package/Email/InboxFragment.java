/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package My.Package.Email;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import java.util.List;

/**
 *
 * @author Bert
 */
public class InboxFragment extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
                
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.inbox_fragment, container, false);
        updateMails(v);
        return v;
    }
    
    public void updateMails(View v){
        DatabaseHandler dbh = new DatabaseHandler(MainActivity.context);
        List<Mail> mails = dbh.getAllMessages();
        Log.v("mails", mails.get(0).getSubject());
        Button bt = (Button) v.findViewById(R.id.inbox_mail1);
        bt.setText(mails.get(0).getSubject() + "\n" + mails.get(0).getSender());
        bt.setTag(0);
        bt = (Button) v.findViewById(R.id.inbox_mail2);
        bt.setText(mails.get(1).getSubject() + "\n" + mails.get(1).getSender());
        bt.setTag(1);
        bt = (Button) v.findViewById(R.id.inbox_mail3);
        bt.setText(mails.get(2).getSubject() + "\n" + mails.get(2).getSender());
        bt.setTag(2);
        bt = (Button) v.findViewById(R.id.inbox_mail4);
        bt.setText(mails.get(3).getSubject() + "\n" + mails.get(3).getSender());
        bt.setTag(3);
        bt = (Button) v.findViewById(R.id.inbox_mail5);
        bt.setText(mails.get(4).getSubject() + "\n" + mails.get(4).getSender());
        bt.setTag(4);
        bt = (Button) v.findViewById(R.id.inbox_mail6);
        bt.setText(mails.get(5).getSubject() + "\n" + mails.get(5).getSender());
        bt.setTag(5);
        bt = (Button) v.findViewById(R.id.inbox_mail7);
        bt.setText(mails.get(6).getSubject() + "\n" + mails.get(6).getSender());
        bt.setTag(6);
        bt = (Button) v.findViewById(R.id.inbox_mail8);
        bt.setText(mails.get(7).getSubject() + "\n" + mails.get(7).getSender());
        bt.setTag(7);
        bt = (Button) v.findViewById(R.id.inbox_mail9);
        bt.setText(mails.get(8).getSubject() + "\n" + mails.get(8).getSender());
        bt.setTag(8);
        bt = (Button) v.findViewById(R.id.inbox_mail10);
        bt.setText(mails.get(9).getSubject() + "\n" + mails.get(9).getSender());
        bt.setTag(9);        
    }
}
