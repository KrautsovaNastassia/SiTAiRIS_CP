/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedsKravcova;

import entitysLishtvan.Users;
import java.io.Serializable;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Named(value = "auth")
@SessionScoped
public class AuthKravcova  implements Serializable{
    private String username;
    private String password;
    
    @EJB
    private sessionsShcherbuk.UsersFacade ejbFacade;
    @EJB
    private sessionsShcherbuk.AuthUserLocalShcherbuk userAuth;
    
    public AuthKravcova() {
        
    }
    
    public String login(){
        try {
            Users tempUser = (Users)ejbFacade.find(username);
            if(tempUser.getPassword().equals(password)){
                userAuth.setCurrentUser(tempUser);
                return tempUser.getUserRole().equals(BigInteger.ZERO) ? "userindex?faces-redirect=true" : "index?faces-redirect=true";
            }
        } catch(Exception e){ }
        return "/auth?faces-redirect=true";
    }
    
    public String clearCurrentUser(){
        username = null;
        password = null;
        userAuth.setCurrentUser(null);
        return "auth?faces-redirect=true";
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
