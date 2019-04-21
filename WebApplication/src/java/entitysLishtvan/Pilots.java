/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysLishtvan;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "PILOTS", catalog = "", schema = "ROOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pilots.findAll", query = "SELECT p FROM Pilots p")
    , @NamedQuery(name = "Pilots.findByPilotId", query = "SELECT p FROM Pilots p WHERE p.pilotId = :pilotId")
    , @NamedQuery(name = "Pilots.findByName", query = "SELECT p FROM Pilots p WHERE p.name = :name")
    , @NamedQuery(name = "Pilots.findBySurname", query = "SELECT p FROM Pilots p WHERE p.surname = :surname")})
public class Pilots implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PILOT_ID", nullable = false, precision = 22, scale = 0)
    private BigDecimal pilotId;
    
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;
    
    @Basic(optional = false)
    @Column(name = "SURNAME", nullable = false, length = 30)
    private String surname;
    
    @OneToMany(mappedBy = "copilotId")
    private List<Flights> flightsList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pilotId")
    private List<Flights> flightsList1;

    public Pilots() {
    }

    public Pilots(BigDecimal pilotId) {
        this.pilotId = pilotId;
    }

    public Pilots(BigDecimal pilotId, String name, String surname) {
        this.pilotId = pilotId;
        this.name = name;
        this.surname = surname;
    }

    public BigDecimal getPilotId() {
        return pilotId;
    }

    public void setPilotId(BigDecimal pilotId) {
        this.pilotId = pilotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        hash += (pilotId != null ? pilotId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pilots)) {
            return false;
        }
        Pilots other = (Pilots) object;
        if ((this.pilotId == null && other.pilotId != null) || (this.pilotId != null && !this.pilotId.equals(other.pilotId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.surname;
    }
    
}
