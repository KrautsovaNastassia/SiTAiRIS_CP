/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysLishtvan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Entity
@Table(name = "PRICES", catalog = "", schema = "ROOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prices.findAll", query = "SELECT p FROM Prices p")
    , @NamedQuery(name = "Prices.findByPriceId", query = "SELECT p FROM Prices p WHERE p.priceId = :priceId")
    , @NamedQuery(name = "Prices.findByTravelClass", query = "SELECT p FROM Prices p WHERE p.travelClass = :travelClass")
    , @NamedQuery(name = "Prices.findByTicketPrice", query = "SELECT p FROM Prices p WHERE p.ticketPrice = :ticketPrice")})
public class Prices implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PRICE_ID", nullable = false, precision = 22, scale = 0)
    private BigDecimal priceId;
    
    @Basic(optional = false)
    @Column(name = "TRAVEL_CLASS", nullable = false, length = 15)
    private String travelClass;
    
    @Basic(optional = false)
    @Column(name = "TICKET_PRICE", nullable = false)
    private BigInteger ticketPrice;
    
    @JoinColumn(name = "FLIGHT_CODE", referencedColumnName = "FLIGHT_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Flights flightCode;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceId")
    private List<Tickets> ticketsList;

    public Prices() {
    }

    public Prices(BigDecimal priceId) {
        this.priceId = priceId;
    }

    public Prices(BigDecimal priceId, String travelClass, BigInteger ticketPrice) {
        this.priceId = priceId;
        this.travelClass = travelClass;
        this.ticketPrice = ticketPrice;
    }

    public BigDecimal getPriceId() {
        return priceId;
    }

    public void setPriceId(BigDecimal priceId) {
        this.priceId = priceId;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public BigInteger getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigInteger ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Flights getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(Flights flightCode) {
        this.flightCode = flightCode;
    }

    @XmlTransient
    public List<Tickets> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Tickets> ticketsList) {
        this.ticketsList = ticketsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (priceId != null ? priceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prices)) {
            return false;
        }
        Prices other = (Prices) object;
        if ((this.priceId == null && other.priceId != null) || (this.priceId != null && !this.priceId.equals(other.priceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.flightCode.getAirportFrom() + " - " + this.flightCode.getAirportTo()
                + ". " + this.travelClass + ": " + this.ticketPrice + " руб.";
    }
    
}
