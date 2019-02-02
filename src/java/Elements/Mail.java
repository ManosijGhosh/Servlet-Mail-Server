/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

/**
 *
 * @author MANOSIJ
 */
public class Mail {
    int id;
    boolean isRead;
    String receiver, sender, message;
    
    public Mail(int id) {
        this.id = id;
        isRead = false;
    }
    
    public void setReceiver(String receiver){
        this.receiver = receiver;
    }
    
    public void setSender(String sender){
        this.sender = sender;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getReceiver(){
        return this.receiver;
    }
    
    public String getSender(){
        return this.sender;
    }
    
    public boolean isRead() {
        return this.isRead;
    }
    
    public void markRead() {
        this.isRead = true;
    }
}
