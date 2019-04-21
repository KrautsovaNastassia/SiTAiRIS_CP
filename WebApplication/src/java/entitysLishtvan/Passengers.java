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
@Table(name = "PASSENGERS", catalog = "", schema = "ROOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Passengers.findAll", query = "SELECT p FROM Passengers p")
    , @NamedQuery(name = "Passengers.findByPassId", query = "SELECT p FROM Passengers p WHERE p.passId = :passId")
    , @NamedQuery(name = "Passengers.findByFirstName", query = "SELECT p FROM Passengers p WHERE p.firstName = :firstName")
    , @NamedQuery(name = "Passengers.findByLastName", query = "SELECT p FROM Passengers p WHERE p.lastName = :lastName")
    , @NamedQuery(name = "Passengers.findByCountry", query = "SELECT p FROM Passengers p WHERE p.country = :country")
    , @NamedQuery(name = "Passengers.findByPassportNum", query = "SELECT p FROM Passengers p WHERE p.passportNum = :passportNum")
    , @NamedQuery(name = "Passengers.findByUsername", query = "SELECT p FROM Passengers p WHERE p.username = :username")
    , @NamedQuery(name = "Passengers.findLastPassId", query = "SELECT p FROM Passengers p ORDER BY p.passId DESC")})
public class Passengers implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PASS_ID", nullable = false, precision = 22, scale = 0)
    private BigDecimal passId;
    
    @Basic(optional = false)
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
    
    @Basic(optional = false)
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;
    
    @Basic(optional = false)
    @Column(name = "COUNTRY", nullable = false, length = 40)
    private String country;
    
    @Basic(optional = false)
    @Column(name = "PASSPORT_NUM", nullable = false, length = 10)
    private String passportNum;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "passId")
    private List<Tickets> ticketsList;
    
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME", nullable = false)
    @ManyToOne(optional = false)
    private Users username;

    public Passengers() {
    }

    public Passengers(BigDecimal passId) {
        this.passId = passId;
    }

    public Passengers(BigDecimal passId, String firstName, String lastName, String country, String passportNum) {
        this.passId = passId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.passportNum = passportNum;
    }

    public BigDecimal getPassId() {
        return passId;
    }

    public void setPassId(BigDecimal passId) {
        this.passId = passId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    @XmlTransient
    public List<Tickets> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Tickets> ticketsList) {
        this.ticketsList = ticketsList;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passId != null ? passId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passengers)) {
            return false;
        }
        Passengers other = (Passengers) object;
        if ((this.passId == null && other.passId != null) || (this.passId != null && !this.passId.equals(other.passId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.lastName;
    }
    
}
