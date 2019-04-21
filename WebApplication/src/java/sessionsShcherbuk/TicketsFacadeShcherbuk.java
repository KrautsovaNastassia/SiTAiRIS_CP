/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsShcherbuk;

import entitysLishtvan.Passengers;
import entitysLishtvan.Tickets;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Stateless
public class TicketsFacadeShcherbuk extends AbstractFacadeShcherbuk<Tickets> {

    @PersistenceContext(unitName = "WebApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketsFacadeShcherbuk() {
        super(Tickets.class);
    }
    
    public List<Tickets> findPassengersTickets(BigDecimal passId){ 
        return em.createNamedQuery("Tickets.findByPassId", Tickets.class)
                .setParameter("passId", passId).getResultList();
    }
    
}
