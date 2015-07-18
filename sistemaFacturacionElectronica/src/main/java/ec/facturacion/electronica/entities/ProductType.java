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
@Table(name = "product_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductType.findAll", query = "SELECT p FROM ProductType p"),
    @NamedQuery(name = "ProductType.findByProTypCode", query = "SELECT p FROM ProductType p WHERE p.proTypCode = :proTypCode"),
    @NamedQuery(name = "ProductType.findByProTypDesription", query = "SELECT p FROM ProductType p WHERE p.proTypDesription = :proTypDesription"),
    @NamedQuery(name = "ProductType.findByProTypEnabled", query = "SELECT p FROM ProductType p WHERE p.proTypEnabled = :proTypEnabled")})
public class ProductType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRO_TYP_CODE")
    private Integer proTypCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PRO_TYP_DESRIPTION")
    private String proTypDesription;
    @Column(name = "PRO_TYP_ENABLED")
    private Boolean proTypEnabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proTypCode")
    private List<Product> productList;

    public ProductType() {
    }

    public ProductType(Integer proTypCode) {
        this.proTypCode = proTypCode;
    }

    public ProductType(Integer proTypCode, String proTypDesription) {
        this.proTypCode = proTypCode;
        this.proTypDesription = proTypDesription;
    }

    public Integer getProTypCode() {
        return proTypCode;
    }

    public void setProTypCode(Integer proTypCode) {
        this.proTypCode = proTypCode;
    }

    public String getProTypDesription() {
        return proTypDesription;
    }

    public void setProTypDesription(String proTypDesription) {
        this.proTypDesription = proTypDesription;
    }

    public Boolean getProTypEnabled() {
        return proTypEnabled;
    }

    public void setProTypEnabled(Boolean proTypEnabled) {
        this.proTypEnabled = proTypEnabled;
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
        hash += (proTypCode != null ? proTypCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductType)) {
            return false;
        }
        ProductType other = (ProductType) object;
        if ((this.proTypCode == null && other.proTypCode != null) || (this.proTypCode != null && !this.proTypCode.equals(other.proTypCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.ProductType[ proTypCode=" + proTypCode + " ]";
    }
    
}
