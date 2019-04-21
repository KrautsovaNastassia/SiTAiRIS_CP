/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsShcherbuk;

import entitysLishtvan.Airports;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Stateless
public class AirportsFacadeShcherbuk extends AbstractFacadeShcherbuk<Airports> {

    @PersistenceContext(unitName = "WebApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AirportsFacadeShcherbuk() {
        super(Airports.class);
    }
    
}
