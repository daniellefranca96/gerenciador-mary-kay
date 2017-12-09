
package JavaBeans;

import java.awt.Color;

/**
 *
 * @author Danielle S. França
 */
public class Usuario {
    
    private String user;
    private String senha;
    private String email;
    
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    public String getSenha(){
        return senha;
    }
    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
}
