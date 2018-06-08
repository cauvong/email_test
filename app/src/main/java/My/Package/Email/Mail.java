/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package My.Package.Email;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Bert
 */
public class Mail {
    
    private String subject;
    private String sender;
    private String text;
    private Date sent;
    private int id;
    
    public Mail(String sender, String subject, String text, String sent, String id){
        this.sender = sender;
        this.subject = subject;
        this.text = text;
        this.id = Integer.parseInt(id);
        
        DateFormat df = new SimpleDateFormat("yy-MM-dd hh:mm:ss.SSS");
        
        try{
            this.sent = df.parse(sent);
        }
        catch(ParseException pe){
            
        }
    }

    public String getSubject() {
        return subject;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public Date getSent() {
        return sent;
    }
    
    
    
}
