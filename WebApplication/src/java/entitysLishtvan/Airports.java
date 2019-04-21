/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysLishtvan;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "AIRPORTS", catalog = "", schema = "ROOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Airports.findAll", query = "SELECT a FROM Airports a")
    , @NamedQuery(name = "Airports.findByAirportCode", query = "SELECT a FROM Airports a WHERE a.airportCode = :airportCode")
    , @NamedQuery(name = "Airports.findByAirportName", query = "SELECT a FROM Airports a WHERE a.airportName = :airportName")
    , @NamedQuery(name = "Airports.findByAirportLocation", query = "SELECT a FROM Airports a WHERE a.airportLocation = :airportLocation")})
public class Airports implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "AIRPORT_CODE", nullable = false, length = 10)
    private String airportCode;
    
    @Basic(optional = false)
    @Column(name = "AIRPORT_NAME", nullable = false, length = 50)
    private String airportName;
    
    @Basic(optional = false)
    @Column(name = "AIRPORT_LOCATION", nullable = false, length = 60)
    private String airportLocation;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airportTo")
    private List<Flights> flightsList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airportFrom")
    private List<Flights> flightsList1;

    public Airports() {
    }

    public Airports(String airportCode) {
        this.airportCode = airportCode;
    }

    public Airports(String airportCode, String airportName, String airportLocation) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.airportLocation = airportLocation;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportLocation() {
        return airportLocation;
    }

    public void setAirportLocation(String airportLocation) {
        this.airportLocation = airportLocation;
    }

    @XmlTransient
    public List<Flights> getFlightsList() {
        return flightsList;
    }

    public void setFlightsList(List<Flights> flightsList) {
        this.flightsList = flightsList;
    }

    @XmlTransient
    public List<Flights> getFlightsList1() {
        return flightsList1;
    }

    public void setFlightsList1(List<Flights> flightsList1) {
        this.flightsList1 = flightsList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (airportCode != null ? airportCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Airports)) {
            return false;
        }
        Airports other = (Airports) object;
        if ((this.airportCode == null && other.airportCode != null) || (this.airportCode != null && !this.airportCode.equals(other.airportCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.airportName;
    }
    
}
