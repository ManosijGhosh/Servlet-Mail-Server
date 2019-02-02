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
public class User {
    String name, username;
    
    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }
    
    public String getName() {
        return name;
    }
    
    public String getUsername() {
        return username;
    }
}
