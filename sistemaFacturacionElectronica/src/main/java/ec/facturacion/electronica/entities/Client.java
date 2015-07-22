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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 * @author JairoCamilo
 */
@Entity
@Table(name = "CLIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByCliCode", query = "SELECT c FROM Client c WHERE c.cliCode = :cliCode"),
    @NamedQuery(name = "Client.findByCliFirstName", query = "SELECT c FROM Client c WHERE c.cliFirstName = :cliFirstName"),
    @NamedQuery(name = "Client.findByCliLastName", query = "SELECT c FROM Client c WHERE c.cliLastName = :cliLastName"),
    @NamedQuery(name = "Client.findByCliSocialReason", query = "SELECT c FROM Client c WHERE c.cliSocialReason = :cliSocialReason"),
    @NamedQuery(name = "Client.findByCliId", query = "SELECT c FROM Client c WHERE c.cliId = :cliId"),
    @NamedQuery(name = "Client.findByCliPhone", query = "SELECT c FROM Client c WHERE c.cliPhone = :cliPhone"),
    @NamedQuery(name = "Client.findByCliExt", query = "SELECT c FROM Client c WHERE c.cliExt = :cliExt"),
    @NamedQuery(name = "Client.findByCliCellPhone", query = "SELECT c FROM Client c WHERE c.cliCellPhone = :cliCellPhone"),
    @NamedQuery(name = "Client.findByCliEmail", query = "SELECT c FROM Client c WHERE c.cliEmail = :cliEmail"),
    @NamedQuery(name = "Client.findByCliEnabled", query = "SELECT c FROM Client c WHERE c.cliEnabled = :cliEnabled")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLI_CODE")
    private Integer cliCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CLI_FIRST_NAME")
    private String cliFirstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CLI_LAST_NAME")
    private String cliLastName;
    @Size(max = 255)
    @Column(name = "CLI_SOCIAL_REASON")
    private String cliSocialReason;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CLI_ID")
    private String cliId;
    @Lob
    @Size(max = 65535)
    @Column(name = "CLI_ADDR")
    private String cliAddr;
    @Size(max = 255)
    @Column(name = "CLI_PHONE")
    private String cliPhone;
    @Size(max = 255)
    @Column(name = "CLI_EXT")
    private String cliExt;
    @Size(max = 255)
    @Column(name = "CLI_CELL_PHONE")
    private String cliCellPhone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CLI_EMAIL")
    private String cliEmail;
    @Column(name = "CLI_ENABLED")
    private Boolean cliEnabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliCode")
    private List<Bill> billList;
    @JoinColumn(name = "CLI_TYP_CODE", referencedColumnName = "CLI_TYP_CODE")
    @ManyToOne(optional = false)
    private ClientType cliTypCode;
    @JoinColumn(name = "IDE_CODE", referencedColumnName = "IDE_CODE")
    @ManyToOne(optional = false)
    private Identification ideCode;

    public Client() {
    }

    public Client(Integer cliCode) {
        this.cliCode = cliCode;
    }

    public Client(Integer cliCode, String cliFirstName, String cliLastName, String cliId, String cliEmail) {
        this.cliCode = cliCode;
        this.cliFirstName = cliFirstName;
        this.cliLastName = cliLastName;
        this.cliId = cliId;
        this.cliEmail = cliEmail;
    }

    public Integer getCliCode() {
        return cliCode;
    }

    public void setCliCode(Integer cliCode) {
        this.cliCode = cliCode;
    }

    public String getCliFirstName() {
        return cliFirstName;
    }

    public void setCliFirstName(String cliFirstName) {
        this.cliFirstName = cliFirstName;
    }

    public String getCliLastName() {
        return cliLastName;
    }

    public void setCliLastName(String cliLastName) {
        this.cliLastName = cliLastName;
    }

    public String getCliSocialReason() {
        return cliSocialReason;
    }

    public void setCliSocialReason(String cliSocialReason) {
        this.cliSocialReason = cliSocialReason;
    }

    public String getCliId() {
        return cliId;
    }

    public void setCliId(String cliId) {
        this.cliId = cliId;
    }

    public String getCliAddr() {
        return cliAddr;
    }

    public void setCliAddr(String cliAddr) {
        this.cliAddr = cliAddr;
    }

    public String getCliPhone() {
        return cliPhone;
    }

    public void setCliPhone(String cliPhone) {
        this.cliPhone = cliPhone;
    }

    public String getCliExt() {
        return cliExt;
    }

    public void setCliExt(String cliExt) {
        this.cliExt = cliExt;
    }

    public String getCliCellPhone() {
        return cliCellPhone;
    }

    public void setCliCellPhone(String cliCellPhone) {
        this.cliCellPhone = cliCellPhone;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public Boolean getCliEnabled() {
        return cliEnabled;
    }

    public void setCliEnabled(Boolean cliEnabled) {
        this.cliEnabled = cliEnabled;
    }

    @XmlTransient
    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public ClientType getCliTypCode() {
        return cliTypCode;
    }

    public void setCliTypCode(ClientType cliTypCode) {
        this.cliTypCode = cliTypCode;
    }

    public Identification getIdeCode() {
        return ideCode;
    }

    public void setIdeCode(Identification ideCode) {
        this.ideCode = ideCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliCode != null ? cliCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.cliCode == null && other.cliCode != null) || (this.cliCode != null && !this.cliCode.equals(other.cliCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.Client[ cliCode=" + cliCode + " ]";
    }
    
}
