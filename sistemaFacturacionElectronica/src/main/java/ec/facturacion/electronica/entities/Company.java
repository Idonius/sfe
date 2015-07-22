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
@Table(name = "COMPANY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByComCode", query = "SELECT c FROM Company c WHERE c.comCode = :comCode"),
    @NamedQuery(name = "Company.findByComDescription", query = "SELECT c FROM Company c WHERE c.comDescription = :comDescription"),
    @NamedQuery(name = "Company.findByComEnabled", query = "SELECT c FROM Company c WHERE c.comEnabled = :comEnabled")})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COM_CODE")
    private Integer comCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COM_DESCRIPTION")
    private String comDescription;
    @Column(name = "COM_ENABLED")
    private Boolean comEnabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comCode")
    private List<User> userList;

    public Company() {
    }

    public Company(Integer comCode) {
        this.comCode = comCode;
    }

    public Company(Integer comCode, String comDescription) {
        this.comCode = comCode;
        this.comDescription = comDescription;
    }

    public Integer getComCode() {
        return comCode;
    }

    public void setComCode(Integer comCode) {
        this.comCode = comCode;
    }

    public String getComDescription() {
        return comDescription;
    }

    public void setComDescription(String comDescription) {
        this.comDescription = comDescription;
    }

    public Boolean getComEnabled() {
        return comEnabled;
    }

    public void setComEnabled(Boolean comEnabled) {
        this.comEnabled = comEnabled;
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
        hash += (comCode != null ? comCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.comCode == null && other.comCode != null) || (this.comCode != null && !this.comCode.equals(other.comCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.Company[ comCode=" + comCode + " ]";
    }
    
}
