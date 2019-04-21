/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsShcherbuk;

import entitysLishtvan.Flights;
import entitysLishtvan.Prices;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Stateless
public class PricesFacadeShcherbuk extends AbstractFacadeShcherbuk<Prices> {

    @PersistenceContext(unitName = "WebApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PricesFacadeShcherbuk() {
        super(Prices.class);
    }
    
    public List<Prices> findFlightForUser(String airportTo, String airportFrom){ 
//        System.out.println(airportTo + " !222!222! " + airportFrom);
        List<Prices> prices  = em.createNamedQuery("Prices.findByTravelClass", Prices.class)
                .setParameter("travelClass", "BUSINESS").getResultList();
        List<Flights> flights = em.createNamedQuery("Flights.findCityFromTo", Flights.class)
                .setParameter("airportTo", airportTo).setParameter("airportFrom", airportFrom).getResultList();
        for (int i = 0; i < flights.size(); i++) {
            prices.addAll(flights.get(i).getPricesList());
        }
        return prices;
    }
    
}
