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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JairoCamilo
 */
@Entity
@Table(name = "IVA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Iva.findAll", query = "SELECT i FROM Iva i"),
    @NamedQuery(name = "Iva.findByIvaCode", query = "SELECT i FROM Iva i WHERE i.ivaCode = :ivaCode"),
    @NamedQuery(name = "Iva.findByIvaValue", query = "SELECT i FROM Iva i WHERE i.ivaValue = :ivaValue"),
    @NamedQuery(name = "Iva.findByIvaEnabled", query = "SELECT i FROM Iva i WHERE i.ivaEnabled = :ivaEnabled")})
public class Iva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IVA_CODE")
    private Integer ivaCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA_VALUE")
    private float ivaValue;
    @Column(name = "IVA_ENABLED")
    private Boolean ivaEnabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ivaCode")
    private List<Product> productList;

    public Iva() {
    }

    public Iva(Integer ivaCode) {
        this.ivaCode = ivaCode;
    }

    public Iva(Integer ivaCode, float ivaValue) {
        this.ivaCode = ivaCode;
        this.ivaValue = ivaValue;
    }

    public Integer getIvaCode() {
        return ivaCode;
    }

    public void setIvaCode(Integer ivaCode) {
        this.ivaCode = ivaCode;
    }

    public float getIvaValue() {
        return ivaValue;
    }

    public void setIvaValue(float ivaValue) {
        this.ivaValue = ivaValue;
    }

    public Boolean getIvaEnabled() {
        return ivaEnabled;
    }

    public void setIvaEnabled(Boolean ivaEnabled) {
        this.ivaEnabled = ivaEnabled;
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
        hash += (ivaCode != null ? ivaCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Iva)) {
            return false;
        }
        Iva other = (Iva) object;
        if ((this.ivaCode == null && other.ivaCode != null) || (this.ivaCode != null && !this.ivaCode.equals(other.ivaCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.Iva[ ivaCode=" + ivaCode + " ]";
    }
    
}
