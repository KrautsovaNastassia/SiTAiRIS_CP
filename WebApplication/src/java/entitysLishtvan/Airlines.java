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
@Table(name = "AIRLINES", catalog = "", schema = "ROOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Airlines.findAll", query = "SELECT a FROM Airlines a")
    , @NamedQuery(name = "Airlines.findByAirlinesIcaoCode", query = "SELECT a FROM Airlines a WHERE a.airlinesIcaoCode = :airlinesIcaoCode")
    , @NamedQuery(name = "Airlines.findByAirlineName", query = "SELECT a FROM Airlines a WHERE a.airlineName = :airlineName")
    , @NamedQuery(name = "Airlines.findByAirlineCallSign", query = "SELECT a FROM Airlines a WHERE a.airlineCallSign = :airlineCallSign")
    , @NamedQuery(name = "Airlines.findByAirlineCountry", query = "SELECT a FROM Airlines a WHERE a.airlineCountry = :airlineCountry")})
public class Airlines implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AIRLINES_ICAO_CODE", nullable = false, length = 5)
    private String airlinesIcaoCode;
    
    @Basic(optional = false)
    @Column(name = "AIRLINE_NAME", nullable = false, length = 40)
    private String airlineName;
    
    @Basic(optional = false)
    @Column(name = "AIRLINE_CALL_SIGN", nullable = false, length = 30)
    private String airlineCallSign;
    
    @Basic(optional = false)
    @Column(name = "AIRLINE_COUNTRY", nullable = false, length = 40)
    private String airlineCountry;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airlineCode")
    private List<Flights> flightsList;

    public Airlines() {
    }

    public Airlines(String airlinesIcaoCode) {
        this.airlinesIcaoCode = airlinesIcaoCode;
    }

    public Airlines(String airlinesIcaoCode, String airlineName, String airlineCallSign, String airlineCountry) {
        this.airlinesIcaoCode = airlinesIcaoCode;
        this.airlineName = airlineName;
        this.airlineCallSign = airlineCallSign;
        this.airlineCountry = airlineCountry;
    }

    public String getAirlinesIcaoCode() {
        return airlinesIcaoCode;
    }

    public void setAirlinesIcaoCode(String airlinesIcaoCode) {
        this.airlinesIcaoCode = airlinesIcaoCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineCallSign() {
        return airlineCallSign;
    }

    public void setAirlineCallSign(String airlineCallSign) {
        this.airlineCallSign = airlineCallSign;
    }

    public String getAirlineCountry() {
        return airlineCountry;
    }

    public void setAirlineCountry(String airlineCountry) {
        this.airlineCountry = airlineCountry;
    }

    @XmlTransient
    public List<Flights> getFlightsList() {
        return flightsList;
    }

    public void setFlightsList(List<Flights> flightsList) {
        this.flightsList = flightsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (airlinesIcaoCode != null ? airlinesIcaoCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Airlines)) {
            return false;
        }
        Airlines other = (Airlines) object;
        if ((this.airlinesIcaoCode == null && other.airlinesIcaoCode != null) || (this.airlinesIcaoCode != null && !this.airlinesIcaoCode.equals(other.airlinesIcaoCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return airlineName;
    }
    
}
