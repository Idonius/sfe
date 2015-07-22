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
@Table(name = "EMISSION_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmissionType.findAll", query = "SELECT e FROM EmissionType e"),
    @NamedQuery(name = "EmissionType.findByEmiTypCode", query = "SELECT e FROM EmissionType e WHERE e.emiTypCode = :emiTypCode"),
    @NamedQuery(name = "EmissionType.findByEmiTypDescription", query = "SELECT e FROM EmissionType e WHERE e.emiTypDescription = :emiTypDescription"),
    @NamedQuery(name = "EmissionType.findByEmiTypeEnabled", query = "SELECT e FROM EmissionType e WHERE e.emiTypeEnabled = :emiTypeEnabled")})
public class EmissionType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EMI_TYP_CODE")
    private Integer emiTypCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMI_TYP_DESCRIPTION")
    private String emiTypDescription;
    @Column(name = "EMI_TYPE_ENABLED")
    private Boolean emiTypeEnabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emiTypCode")
    private List<User> userList;

    public EmissionType() {
    }

    public EmissionType(Integer emiTypCode) {
        this.emiTypCode = emiTypCode;
    }

    public EmissionType(Integer emiTypCode, String emiTypDescription) {
        this.emiTypCode = emiTypCode;
        this.emiTypDescription = emiTypDescription;
    }

    public Integer getEmiTypCode() {
        return emiTypCode;
    }

    public void setEmiTypCode(Integer emiTypCode) {
        this.emiTypCode = emiTypCode;
    }

    public String getEmiTypDescription() {
        return emiTypDescription;
    }

    public void setEmiTypDescription(String emiTypDescription) {
        this.emiTypDescription = emiTypDescription;
    }

    public Boolean getEmiTypeEnabled() {
        return emiTypeEnabled;
    }

    public void setEmiTypeEnabled(Boolean emiTypeEnabled) {
        this.emiTypeEnabled = emiTypeEnabled;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emiTypCode != null ? emiTypCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmissionType)) {
            return false;
        }
        EmissionType other = (EmissionType) object;
        if ((this.emiTypCode == null && other.emiTypCode != null) || (this.emiTypCode != null && !this.emiTypCode.equals(other.emiTypCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.EmissionType[ emiTypCode=" + emiTypCode + " ]";
    }
    
}
