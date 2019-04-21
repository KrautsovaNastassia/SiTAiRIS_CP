/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysLishtvan;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KravcovaSherbukLishtvan
 */
@Entity
@Table(name = "FLIGHTS", catalog = "", schema = "ROOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flights.findAll", query = "SELECT f FROM Flights f")
    , @NamedQuery(name = "Flights.findByFlightCode", query = "SELECT f FROM Flights f WHERE f.flightCode = :flightCode")
    , @NamedQuery(name = "Flights.findByFlightDistance", query = "SELECT f FROM Flights f WHERE f.flightDistance = :flightDistance")
    , @NamedQuery(name = "Flights.findByFDate", query = "SELECT f FROM Flights f WHERE f.fDate = :fDate")
    , @NamedQuery(name = "Flights.findByTDate", query = "SELECT f FROM Flights f WHERE f.tDate = :tDate")
    , @NamedQuery(name = "Flights.findByNumOfEconomy", query = "SELECT f FROM Flights f WHERE f.numOfEconomy = :numOfEconomy")
    , @NamedQuery(name = "Flights.findByNumOfBusiness", query = "SELECT f FROM Flights f WHERE f.numOfBusiness = :numOfBusiness")
    , @NamedQuery(name = "Flights.findByNumOfFirst", query = "SELECT f FROM Flights f WHERE f.numOfFirst = :numOfFirst")
    , @NamedQuery(name = "Flights.findCityFromTo", 
        query = "SELECT f FROM Flights f WHERE f.airportTo = :airportTo AND f.airportFrom = :airportFrom ORDER BY f.fDate")})
public class Flights implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FLIGHT_CODE", nullable = false, length = 30)
    private String flightCode;
    
    @Basic(optional = false)
    @Column(name = "FLIGHT_DISTANCE", nullable = false)
    private BigInteger flightDistance;
    
    @Basic(optional = false)
    @Column(name = "F_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDate;
    
    @Basic(optional = false)
    @Column(name = "T_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tDate;
    
    @Basic(optional = false)
    @Column(name = "NUM_OF_ECONOMY", nullable = false)
    private BigInteger numOfEconomy;
    
    @Basic(optional = false)
    @Column(name = "NUM_OF_BUSINESS", nullable = false)
    private BigInteger numOfBusiness;
    
    @Basic(optional = false)
    @Column(name = "NUM_OF_FIRST", nullable = false)
    private BigInteger numOfFirst;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flightCode")
    private List<Prices> pricesList;
    
    @JoinColumn(name = "AIRLINE_CODE", referencedColumnName = "AIRLINES_ICAO_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Airlines airlineCode;
    
    @JoinColumn(name = "AIRPORT_TO", referencedColumnName = "AIRPORT_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Airports airportTo;
    
    @JoinColumn(name = "AIRPORT_FROM", referencedColumnName = "AIRPORT_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Airports airportFrom;
    
    @JoinColumn(name = "COPILOT_ID", referencedColumnName = "PILOT_ID")
    @ManyToOne
    private Pilots copilotId;
    
    @JoinColumn(name = "PILOT_ID", referencedColumnName = "PILOT_ID", nullable = false)
    @ManyToOne(optional = false)
    private Pilots pilotId;

    public Flights() {
    }

    public Flights(String flightCode) {
        this.flightCode = flightCode;
    }

    public Flights(String flightCode, BigInteger flightDistance, Date fDate, Date tDate, BigInteger numOfEconomy, BigInteger numOfBusiness, BigInteger numOfFirst) {
        this.flightCode = flightCode;
        this.flightDistance = flightDistance;
        this.fDate = fDate;
        this.tDate = tDate;
        this.numOfEconomy = numOfEconomy;
        this.numOfBusiness = numOfBusiness;
        this.numOfFirst = numOfFirst;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public BigInteger getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(BigInteger flightDistance) {
        this.flightDistance = flightDistance;
    }

    public Date getFDate() {
        return fDate;
    }

    public void setFDate(Date fDate) {
        this.fDate = fDate;
    }

    public Date getTDate() {
        return tDate;
    }

    public void setTDate(Date tDate) {
        this.tDate = tDate;
    }

    public BigInteger getNumOfEconomy() {
        return numOfEconomy;
    }

    public void setNumOfEconomy(BigInteger numOfEconomy) {
        this.numOfEconomy = numOfEconomy;
    }

    public BigInteger getNumOfBusiness() {
        return numOfBusiness;
    }

    public void setNumOfBusiness(BigInteger numOfBusiness) {
        this.numOfBusiness = numOfBusiness;
    }

    public BigInteger getNumOfFirst() {
        return numOfFirst;
    }

    public void setNumOfFirst(BigInteger numOfFirst) {
        this.numOfFirst = numOfFirst;
    }

    @XmlTransient
    public List<Prices> getPricesList() {
        return pricesList;
    }

    public void setPricesList(List<Prices> pricesList) {
        this.pricesList = pricesList;
    }

    public Airlines getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(Airlines airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Airports getAirportTo() {
        return airportTo;
    }

    public void setAirportTo(Airports airportTo) {
        this.airportTo = airportTo;
    }

    public Airports getAirportFrom() {
        return airportFrom;
    }

    public void setAirportFrom(Airports airportFrom) {
        this.airportFrom = airportFrom;
    }

    public Pilots getCopilotId() {
        return copilotId;
    }

    public void setCopilotId(Pilots copilotId) {
        this.copilotId = copilotId;
    }

    public Pilots getPilotId() {
        return pilotId;
    }

    public void setPilotId(Pilots pilotId) {
        this.pilotId = pilotId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightCode != null ? flightCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flights)) {
            return false;
        }
        Flights other = (Flights) object;
        if ((this.flightCode == null && other.flightCode != null) || (this.flightCode != null && !this.flightCode.equals(other.flightCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.flightCode;
    }
    
}
