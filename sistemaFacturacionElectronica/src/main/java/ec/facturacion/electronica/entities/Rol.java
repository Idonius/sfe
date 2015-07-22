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
@Table(name = "ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByRolCode", query = "SELECT r FROM Rol r WHERE r.rolCode = :rolCode"),
    @NamedQuery(name = "Rol.findByRolDescription", query = "SELECT r FROM Rol r WHERE r.rolDescription = :rolDescription"),
    @NamedQuery(name = "Rol.findByRolEnabled", query = "SELECT r FROM Rol r WHERE r.rolEnabled = :rolEnabled")})
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROL_CODE")
    private Integer rolCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ROL_DESCRIPTION")
    private String rolDescription;
    @Column(name = "ROL_ENABLED")
    private Boolean rolEnabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolCode")
    private List<User> userList;

    public Rol() {
    }

    public Rol(Integer rolCode) {
        this.rolCode = rolCode;
    }

    public Rol(Integer rolCode, String rolDescription) {
        this.rolCode = rolCode;
        this.rolDescription = rolDescription;
    }

    public Integer getRolCode() {
        return rolCode;
    }

    public void setRolCode(Integer rolCode) {
        this.rolCode = rolCode;
    }

    public String getRolDescription() {
        return rolDescription;
    }

    public void setRolDescription(String rolDescription) {
        this.rolDescription = rolDescription;
    }

    public Boolean getRolEnabled() {
        return rolEnabled;
    }

    public void setRolEnabled(Boolean rolEnabled) {
        this.rolEnabled = rolEnabled;
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
        hash += (rolCode != null ? rolCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolCode == null && other.rolCode != null) || (this.rolCode != null && !this.rolCode.equals(other.rolCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.Rol[ rolCode=" + rolCode + " ]";
    }
    
}
