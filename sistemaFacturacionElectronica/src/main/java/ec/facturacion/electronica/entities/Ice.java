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
@Table(name = "ICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ice.findAll", query = "SELECT i FROM Ice i"),
    @NamedQuery(name = "Ice.findByIceCode", query = "SELECT i FROM Ice i WHERE i.iceCode = :iceCode"),
    @NamedQuery(name = "Ice.findByIceDescription", query = "SELECT i FROM Ice i WHERE i.iceDescription = :iceDescription"),
    @NamedQuery(name = "Ice.findByIceValue", query = "SELECT i FROM Ice i WHERE i.iceValue = :iceValue")})
public class Ice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ICE_CODE")
    private Integer iceCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ICE_DESCRIPTION")
    private String iceDescription;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ICE_VALUE")
    private Float iceValue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iceCode")
    private List<Product> productList;

    public Ice() {
    }

    public Ice(Integer iceCode) {
        this.iceCode = iceCode;
    }

    public Ice(Integer iceCode, String iceDescription) {
        this.iceCode = iceCode;
        this.iceDescription = iceDescription;
    }

    public Integer getIceCode() {
        return iceCode;
    }

    public void setIceCode(Integer iceCode) {
        this.iceCode = iceCode;
    }

    public String getIceDescription() {
        return iceDescription;
    }

    public void setIceDescription(String iceDescription) {
        this.iceDescription = iceDescription;
    }

    public Float getIceValue() {
        return iceValue;
    }

    public void setIceValue(Float iceValue) {
        this.iceValue = iceValue;
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
        hash += (iceCode != null ? iceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ice)) {
            return false;
        }
        Ice other = (Ice) object;
        if ((this.iceCode == null && other.iceCode != null) || (this.iceCode != null && !this.iceCode.equals(other.iceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.Ice[ iceCode=" + iceCode + " ]";
    }
    
}
