/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedsKravcova;

import entitysLishtvan.Tickets;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import manageds.utilLishtvan.JsfUtilLishtvan;
import sessionsShcherbuk.AuthUserLocalShcherbuk;

@Named("userTicketsController")
@RequestScoped
public class UserTicketsControllerKravcova extends TicketsControllerKravcova {
    @EJB
    private sessionsShcherbuk.AuthUserLocalShcherbuk userAuth;
    
    @EJB
    private sessionsShcherbuk.SearchedBeanShcherbuk searchedBean;
    
    private String cityFrom;
    
    private String cityTo;
    
    @Override
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtilLishtvan.getSelectItems(ejbFacade.findPassengersTickets(
                userAuth.getCurrentUser().getPassengersList().get(0).getPassId()), false
        );
    }

    @Override
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtilLishtvan.getSelectItems(ejbFacade.findPassengersTickets(
                userAuth.getCurrentUser().getPassengersList().get(0).getPassId()), true
        );
    }
    
    public String inactivate() {
        prepareEdit();
        try {
            current.setTicketStatus("INACTIVE");
            getFacade().edit(current);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userindex?faces-redirect=true";
    }
    
    
    public String searchFlight(){
        searchedBean.setCityTo(cityTo);
        searchedBean.setCityFrom(cityFrom);
        return "findflight?faces-redirect=true";
    }
        
    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }
    
    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }
    
    public String updateDataUser(){
        prepareList();
        return "";
    }
}
