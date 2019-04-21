/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsShcherbuk;

import entitysLishtvan.Passengers;
import entitysLishtvan.Users;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Stateless
public class PassengersFacadeShcherbuk extends AbstractFacadeShcherbuk<Passengers> {
    @EJB
    private sessionsShcherbuk.UsersFacade ejbFacade; 
    
    @PersistenceContext(unitName = "WebApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PassengersFacadeShcherbuk() {
        super(Passengers.class);
    }
    
    public BigDecimal findNextID(){ 
        return em.createNamedQuery("Passengers.findLastPassId", Passengers.class)
                .getResultList().get(0).getPassId().add(BigDecimal.ONE);
    }

    public Passengers getPassengerByUser(Users username){ 
        return em.createNamedQuery("Passengers.findByUsername", Passengers.class)
                .setParameter("username", username).getResultList().get(0);
    }
    
}

