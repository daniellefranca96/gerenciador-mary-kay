/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author Danielle S. França
 */
public class SimpleAuth extends Authenticator {     
    public String username = null;     
    public String password = null;     
    
    
    public SimpleAuth(String user, String pwd) {     
        username = user;     
        password = pwd;     
    }     
    
    protected PasswordAuthentication getPasswordAuthentication() {     
        return new PasswordAuthentication (username,password);   
    }     
    
}
