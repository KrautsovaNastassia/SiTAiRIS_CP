/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedsKravcova;

import entitysLishtvan.Flights;
import entitysLishtvan.Passengers;
import entitysLishtvan.Prices;
import entitysLishtvan.Tickets;
import entitysLishtvan.Users;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import manageds.utilLishtvan.JsfUtilLishtvan;
import sessionsShcherbuk.SearchedBeanShcherbuk;


@Named("flightsForUserController")
@RequestScoped
public class FlightsForUserControllerKravcova extends PricesControllerKravcova {
    @EJB
    private sessionsShcherbuk.SearchedBeanShcherbuk searchedBean;

    @EJB
    private sessionsShcherbuk.TicketsFacadeShcherbuk ticketsFacade;
    
    @EJB
    private sessionsShcherbuk.PassengersFacadeShcherbuk passengersFacade;
    
    @EJB
    private sessionsShcherbuk.AuthUserLocalShcherbuk userAuth;

    
    public String addAirFrom(){
        return searchedBean.getCityFrom();
    } 
    
    public String addAirTo(){
        return searchedBean.getCityTo();
    }
    
    public String getTicket(){
        prepareEdit();
        try { 
            Users user = userAuth.getCurrentUser();//.getUsername()
            Passengers passenger = passengersFacade.getPassengerByUser(user);
            Prices currentPrice = current;
            if(isHaveTicket(currentPrice.getFlightCode(), currentPrice.getTravelClass())){
                System.out.println(":::3 - " + userAuth.getCurrentUser());
                System.out.println(":::3 - " + userAuth.getCurrentUser().getPassengersList().size());

                String code = passenger.getPassId().toString() + currentPrice.getPriceId().toString() + 
                         + userAuth.getCurrentUser().getPassengersList().get(0).getTicketsList().size();
                Tickets newTicket = new Tickets(code, "ACTIVE", passenger, currentPrice);
                ticketsFacade.create(newTicket);
                userAuth.getCurrentUser().getPassengersList().get(0).getTicketsList().add(newTicket);
                return "userindex?faces-redirect=true";
            } else {
                JsfUtilLishtvan.addErrorMessage("Unfortunately, the tickets ran out!");                
            }   
        } catch (Exception e) {
            JsfUtilLishtvan.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        } 
        return null;
    }
    
    private boolean isHaveTicket(Flights flight, String travelClass){
        int quantity;
        int quantityPaid;
        if(travelClass.equals("BUSINESS")){
            quantity = Integer.valueOf(flight.getNumOfBusiness().toString());
        } else if(travelClass.equals(" ECONOMY")) {
            quantity = Integer.valueOf(flight.getNumOfEconomy().toString());
        } else {
            quantity = Integer.valueOf(flight.getNumOfFirst().toString());  
        }
        quantityPaid = countTickets(flight.getPricesList(), travelClass); 
        return quantityPaid < quantity;
    }
    
    private int countTickets(List<Prices> prices, String travelClass){
        int count = 0;
        for (int i = 0; i < prices.size(); i++) {
            if(travelClass.equals("BUSINESS")){
                if(prices.get(i).getTravelClass().equals("BUSINESS")){
                    count += findActiveQuantity(prices.get(i).getTicketsList());
                }
            } else if(travelClass.equals("ECONOMY")) {
                if(prices.get(i).getTravelClass().equals("ECONOMY")){
                    count += findActiveQuantity(prices.get(i).getTicketsList());
                }
            } else {
                if(prices.get(i).getTravelClass().equals("FIRST")){
                    count += findActiveQuantity(prices.get(i).getTicketsList());
                }
            }
        }  
        return count;
    }
    
    private int findActiveQuantity(List<Tickets> tickets){
        int quantity = 0;
        for (int i = 0; i < tickets.size(); i++) {
            if(tickets.get(i).getTicketStatus().equals("ACTIVE")){
                quantity++;
            }
        }
        return quantity;
    }
}
