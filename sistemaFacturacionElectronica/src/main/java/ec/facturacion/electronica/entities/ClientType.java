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
@Table(name = "client_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientType.findAll", query = "SELECT c FROM ClientType c"),
    @NamedQuery(name = "ClientType.findByCliTypCode", query = "SELECT c FROM ClientType c WHERE c.cliTypCode = :cliTypCode"),
    @NamedQuery(name = "ClientType.findByCliTypDescription", query = "SELECT c FROM ClientType c WHERE c.cliTypDescription = :cliTypDescription"),
    @NamedQuery(name = "ClientType.findByCliTypEnabled", query = "SELECT c FROM ClientType c WHERE c.cliTypEnabled = :cliTypEnabled")})
public class ClientType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLI_TYP_CODE")
    private Integer cliTypCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CLI_TYP_DESCRIPTION")
    private String cliTypDescription;
    @Column(name = "CLI_TYP_ENABLED")
    private Boolean cliTypEnabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliTypCode")
    private List<Client> clientList;

    public ClientType() {
    }

    public ClientType(Integer cliTypCode) {
        this.cliTypCode = cliTypCode;
    }

    public ClientType(Integer cliTypCode, String cliTypDescription) {
        this.cliTypCode = cliTypCode;
        this.cliTypDescription = cliTypDescription;
    }

    public Integer getCliTypCode() {
        return cliTypCode;
    }

    public void setCliTypCode(Integer cliTypCode) {
        this.cliTypCode = cliTypCode;
    }

    public String getCliTypDescription() {
        return cliTypDescription;
    }

    public void setCliTypDescription(String cliTypDescription) {
        this.cliTypDescription = cliTypDescription;
    }

    public Boolean getCliTypEnabled() {
        return cliTypEnabled;
    }

    public void setCliTypEnabled(Boolean cliTypEnabled) {
        this.cliTypEnabled = cliTypEnabled;
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
        hash += (cliTypCode != null ? cliTypCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientType)) {
            return false;
        }
        ClientType other = (ClientType) object;
        if ((this.cliTypCode == null && other.cliTypCode != null) || (this.cliTypCode != null && !this.cliTypCode.equals(other.cliTypCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.ClientType[ cliTypCode=" + cliTypCode + " ]";
    }
    
}
