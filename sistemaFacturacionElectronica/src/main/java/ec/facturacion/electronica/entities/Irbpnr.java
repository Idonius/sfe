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
@Table(name = "irbpnr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Irbpnr.findAll", query = "SELECT i FROM Irbpnr i"),
    @NamedQuery(name = "Irbpnr.findByIrbCode", query = "SELECT i FROM Irbpnr i WHERE i.irbCode = :irbCode"),
    @NamedQuery(name = "Irbpnr.findByIrbDescription", query = "SELECT i FROM Irbpnr i WHERE i.irbDescription = :irbDescription"),
    @NamedQuery(name = "Irbpnr.findByIrbValue", query = "SELECT i FROM Irbpnr i WHERE i.irbValue = :irbValue")})
public class Irbpnr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IRB_CODE")
    private Integer irbCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "IRB_DESCRIPTION")
    private String irbDescription;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "IRB_VALUE")
    private Float irbValue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "irbCode")
    private List<Product> productList;

    public Irbpnr() {
    }

    public Irbpnr(Integer irbCode) {
        this.irbCode = irbCode;
    }

    public Irbpnr(Integer irbCode, String irbDescription) {
        this.irbCode = irbCode;
        this.irbDescription = irbDescription;
    }

    public Integer getIrbCode() {
        return irbCode;
    }

    public void setIrbCode(Integer irbCode) {
        this.irbCode = irbCode;
    }

    public String getIrbDescription() {
        return irbDescription;
    }

    public void setIrbDescription(String irbDescription) {
        this.irbDescription = irbDescription;
    }

    public Float getIrbValue() {
        return irbValue;
    }

    public void setIrbValue(Float irbValue) {
        this.irbValue = irbValue;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (irbCode != null ? irbCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Irbpnr)) {
            return false;
        }
        Irbpnr other = (Irbpnr) object;
        if ((this.irbCode == null && other.irbCode != null) || (this.irbCode != null && !this.irbCode.equals(other.irbCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.Irbpnr[ irbCode=" + irbCode + " ]";
    }
    
}
