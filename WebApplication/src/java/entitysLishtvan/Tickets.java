/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysLishtvan;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Entity
@Table(name = "TICKETS", catalog = "", schema = "ROOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t")
    , @NamedQuery(name = "Tickets.findByTicketCode", query = "SELECT t FROM Tickets t WHERE t.ticketCode = :ticketCode")
    , @NamedQuery(name = "Tickets.findByTicketStatus", query = "SELECT t FROM Tickets t WHERE t.ticketStatus = :ticketStatus")
    , @NamedQuery(name = "Tickets.findByPassId", query = "SELECT t FROM Tickets t WHERE t.passId = :passId")})
public class Tickets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TICKET_CODE", nullable = false, length = 30)
    private String ticketCode;
    
    @Basic(optional = false)
    @Column(name = "TICKET_STATUS", nullable = false, length = 30)
    private String ticketStatus;
    
    @JoinColumn(name = "PASS_ID", referencedColumnName = "PASS_ID", nullable = false)
    @ManyToOne(optional = false)
    private Passengers passId;
    
    @JoinColumn(name = "PRICE_ID", referencedColumnName = "PRICE_ID", nullable = false)
    @ManyToOne(optional = false)
    private Prices priceId;

    public Tickets() {
    }

    public Tickets(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Tickets(String ticketCode, String ticketStatus) {
        this.ticketCode = ticketCode;
        this.ticketStatus = ticketStatus;
    }

    public Tickets(String ticketCode, String ticketStatus, Passengers passId, Prices priceId) {
        this.ticketCode = ticketCode;
        this.ticketStatus = ticketStatus;
        this.passId = passId;
        this.priceId = priceId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Passengers getPassId() {
        return passId;
    }

    public void setPassId(Passengers passId) {
        this.passId = passId;
    }

    public Prices getPriceId() {
        return priceId;
    }

    public void setPriceId(Prices priceId) {
        this.priceId = priceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketCode != null ? ticketCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tickets)) {
            return false;
        }
        Tickets other = (Tickets) object;
        if ((this.ticketCode == null && other.ticketCode != null) || (this.ticketCode != null && !this.ticketCode.equals(other.ticketCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ticketCode;
    }
    
}
