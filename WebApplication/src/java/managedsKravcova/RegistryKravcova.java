/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedsKravcova;

import entitysLishtvan.Passengers;
import entitysLishtvan.Users;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import manageds.utilLishtvan.JsfUtilLishtvan;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Named(value = "registry")
@SessionScoped
public class RegistryKravcova implements Serializable {
    @EJB
    private sessionsShcherbuk.UsersFacade userFacade;
    
    @EJB
    private sessionsShcherbuk.PassengersFacadeShcherbuk passengerFacade;
    
    @EJB
    private sessionsShcherbuk.AuthUserLocalShcherbuk userAuth;
    
    private String login; 
    private String password; 
    private String confPassword; 
    private String name; 
    private String surname; 
    private String passport; 
    private String country;
    
    private BigDecimal passId;
    
    public RegistryKravcova() {
    }
    
    public String doRegistry(){
        try {
            String tempUserName = ((Users)userFacade.find(login)).getUsername();
        } catch(Exception e){
            if(confPassword.equals(password)){
                try {
                    Users user = new Users(login, password, BigInteger.ZERO);
                    Passengers passenger = new Passengers(passengerFacade.findNextID(), name, surname, country, passport);
                    passenger.setUsername(user); 
                    userFacade.create(user);
                    passengerFacade.create(passenger);
                    List<Passengers> passengers = new ArrayList<Passengers>();
                    passengers.add(passenger);
                    user.setPassengersList(passengers);
                    userAuth.setCurrentUser(user);
                    return "index?faces-redirect=true";
                } catch(Exception ex){
                    ex.printStackTrace();
                    JsfUtilLishtvan.addErrorMessage("Error, data is not unique!");  
                    return "auth?faces-redirect=true";
                }
            }
        }
        JsfUtilLishtvan.addErrorMessage("Error, user already exists!");  
        return "auth?faces-redirect=true";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
