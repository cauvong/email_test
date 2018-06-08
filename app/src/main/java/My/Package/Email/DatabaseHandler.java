/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package My.Package.Email;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Bert
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MessageManager";
    // Contacts table name
    private static final String TABLE_MESSAGE = "messages";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SENDER = "name";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGE_TABLE = "CREATE TABLE " + TABLE_MESSAGE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SENDER + " TEXT,"
                + KEY_SUBJECT + " TEXT," + KEY_CONTENT + " TEXT," + KEY_DATE + " TEXT " + ")";
        db.execSQL(CREATE_MESSAGE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGE);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addMessage(Message mes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //content uit mail halen lukte niet vandaar een dummy string
        String content = "dummy string";

        DateFormat df = new SimpleDateFormat("yy-MM-dd hh:mm:ss.SSS");

        try {
            values.putNull(KEY_ID);
            values.put(KEY_SENDER, InternetAddress.toString(mes.getFrom()));
            values.put(KEY_SUBJECT, mes.getSubject());
            values.put(KEY_CONTENT, content);
            values.put(KEY_DATE, df.format(mes.getSentDate()));
            // Inserting Row
            db.insert(TABLE_MESSAGE, null, values);
        } catch (MessagingException me) {
            Log.v("error", "adding values to database");
        }

        db.close(); // Closing database connection
    }

// Getting single mail
    public Mail getMessage(int id) {
        Mail mail = null;

        String selectQuery = "SELECT * FROM " + TABLE_MESSAGE + "WHERE " + KEY_ID + " = " + Integer.toString(id);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                mail = new Mail(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // return contact list
        return mail;
    }

// Getting All mails
    public List<Mail> getAllMessages() {

        List<Mail> mails = new ArrayList();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MESSAGE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Mail mail = new Mail(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(0));

                mails.add(mail);
            } while (cursor.moveToNext());
        }

        // return contact list
        return mails;
    }

// Deleting single contact
    public void deleteMessage(int id) {
        //String deleteQuery = "DELETE  * FROM " + TABLE_MESSAGE + " WHERE " + KEY_ID + " = " + Integer.toString(id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MESSAGE, KEY_ID + " = " + id, null);
    }

    public String convertStreamToString(InputStream is) {
        Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
        if (scanner.hasNext()) {
            return scanner.next();
        }
        return "";
    }
}
