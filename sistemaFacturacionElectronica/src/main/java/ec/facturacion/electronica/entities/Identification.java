/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author JairoCamilo
 */
@Entity
@Table(name = "IDENTIFICATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Identification.findAll", query = "SELECT i FROM Identification i"),
    @NamedQuery(name = "Identification.findByIdeCode", query = "SELECT i FROM Identification i WHERE i.ideCode = :ideCode"),
    @NamedQuery(name = "Identification.findByIdeDescription", query = "SELECT i FROM Identification i WHERE i.ideDescription = :ideDescription"),
    @NamedQuery(name = "Identification.findByIdeEnabled", query = "SELECT i FROM Identification i WHERE i.ideEnabled = :ideEnabled")})
public class Identification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDE_CODE")
    private Integer ideCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "IDE_DESCRIPTION")
    private String ideDescription;
    @Column(name = "IDE_ENABLED")
    private Boolean ideEnabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ideCode")
    private List<Client> clientList;

    public Identification() {
    }

    public Identification(Integer ideCode) {
        this.ideCode = ideCode;
    }

    public Identification(Integer ideCode, String ideDescription) {
        this.ideCode = ideCode;
        this.ideDescription = ideDescription;
    }

    public Integer getIdeCode() {
        return ideCode;
    }

    public void setIdeCode(Integer ideCode) {
        this.ideCode = ideCode;
    }

    public String getIdeDescription() {
        return ideDescription;
    }

    public void setIdeDescription(String ideDescription) {
        this.ideDescription = ideDescription;
    }

    public Boolean getIdeEnabled() {
        return ideEnabled;
    }

    public void setIdeEnabled(Boolean ideEnabled) {
        this.ideEnabled = ideEnabled;
    }

    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideCode != null ? ideCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identification)) {
            return false;
        }
        Identification other = (Identification) object;
        if ((this.ideCode == null && other.ideCode != null) || (this.ideCode != null && !this.ideCode.equals(other.ideCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.Identification[ ideCode=" + ideCode + " ]";
    }
    
}
