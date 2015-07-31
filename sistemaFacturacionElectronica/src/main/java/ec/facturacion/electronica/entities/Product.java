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
@Table(name = "PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProCode", query = "SELECT p FROM Product p WHERE p.proCode = :proCode"),
    @NamedQuery(name = "Product.findByProPrincipalCode", query = "SELECT p FROM Product p WHERE p.proPrincipalCode = :proPrincipalCode"),
    @NamedQuery(name = "Product.findByProAuxCode", query = "SELECT p FROM Product p WHERE p.proAuxCode = :proAuxCode"),
    @NamedQuery(name = "Product.findByProName", query = "SELECT p FROM Product p WHERE p.proName = :proName"),
    @NamedQuery(name = "Product.findByProValue", query = "SELECT p FROM Product p WHERE p.proValue = :proValue"),
    @NamedQuery(name = "Product.findByProAttribute1", query = "SELECT p FROM Product p WHERE p.proAttribute1 = :proAttribute1"),
    @NamedQuery(name = "Product.findByProAttribute2", query = "SELECT p FROM Product p WHERE p.proAttribute2 = :proAttribute2"),
    @NamedQuery(name = "Product.findByProAttribute3", query = "SELECT p FROM Product p WHERE p.proAttribute3 = :proAttribute3"),
    @NamedQuery(name = "Product.findByProAttributeDesc1", query = "SELECT p FROM Product p WHERE p.proAttributeDesc1 = :proAttributeDesc1"),
    @NamedQuery(name = "Product.findByProAttributeDesc2", query = "SELECT p FROM Product p WHERE p.proAttributeDesc2 = :proAttributeDesc2"),
    @NamedQuery(name = "Product.findByProAttributeDesc3", query = "SELECT p FROM Product p WHERE p.proAttributeDesc3 = :proAttributeDesc3"),
    @NamedQuery(name = "Product.findByProEnabled", query = "SELECT p FROM Product p WHERE p.proEnabled = :proEnabled")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRO_CODE")
    private Integer proCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PRO_PRINCIPAL_CODE")
    private String proPrincipalCode;
    @Size(max = 255)
    @Column(name = "PRO_AUX_CODE")
    private String proAuxCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PRO_NAME")
    private String proName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRO_VALUE")
    private float proValue;
    @Size(max = 255)
    @Column(name = "PRO_ATTRIBUTE_1")
    private String proAttribute1;
    @Size(max = 255)
    @Column(name = "PRO_ATTRIBUTE_2")
    private String proAttribute2;
    @Size(max = 255)
    @Column(name = "PRO_ATTRIBUTE_3")
    private String proAttribute3;
    @Size(max = 255)
    @Column(name = "PRO_ATTRIBUTE_DESC_1")
    private String proAttributeDesc1;
    @Size(max = 255)
    @Column(name = "PRO_ATTRIBUTE_DESC_2")
    private String proAttributeDesc2;
    @Size(max = 255)
    @Column(name = "PRO_ATTRIBUTE_DESC_3")
    private String proAttributeDesc3;
    @Column(name = "PRO_ENABLED")
    private Boolean proEnabled;
    @JoinColumn(name = "PRO_TYP_CODE", referencedColumnName = "PRO_TYP_CODE")
    @ManyToOne(optional = false)
    private ProductType proTypCode;
    @JoinColumn(name = "ICE_CODE", referencedColumnName = "ICE_CODE")
    @ManyToOne(optional = true)
    private Ice iceCode;
    @JoinColumn(name = "IVA_CODE", referencedColumnName = "IVA_CODE")
    @ManyToOne(optional = true)
    private Iva ivaCode;
    @JoinColumn(name = "IRB_CODE", referencedColumnName = "IRB_CODE")
    @ManyToOne(optional = true)
    private Irbpnr irbCode;
    @JoinColumn(name = "COM_CODE", referencedColumnName = "COM_CODE")
    @ManyToOne(optional = false)
    private Company company;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proCode")
    private List<BillDetail> billDetailList;

    public Product() {
    }

    public Product(Integer proCode) {
        this.proCode = proCode;
    }

    public Product(Integer proCode, String proPrincipalCode, String proName, float proValue) {
        this.proCode = proCode;
        this.proPrincipalCode = proPrincipalCode;
        this.proName = proName;
        this.proValue = proValue;
    }

    public Integer getProCode() {
        return proCode;
    }

    public void setProCode(Integer proCode) {
        this.proCode = proCode;
    }

    public String getProPrincipalCode() {
        return proPrincipalCode;
    }

    public void setProPrincipalCode(String proPrincipalCode) {
        this.proPrincipalCode = proPrincipalCode;
    }

    public String getProAuxCode() {
        return proAuxCode;
    }

    public void setProAuxCode(String proAuxCode) {
        this.proAuxCode = proAuxCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public float getProValue() {
        return proValue;
    }

    public void setProValue(float proValue) {
        this.proValue = proValue;
    }

    public String getProAttribute1() {
        return proAttribute1;
    }

    public void setProAttribute1(String proAttribute1) {
        this.proAttribute1 = proAttribute1;
    }

    public String getProAttribute2() {
        return proAttribute2;
    }

    public void setProAttribute2(String proAttribute2) {
        this.proAttribute2 = proAttribute2;
    }

    public String getProAttribute3() {
        return proAttribute3;
    }

    public void setProAttribute3(String proAttribute3) {
        this.proAttribute3 = proAttribute3;
    }

    public String getProAttributeDesc1() {
        return proAttributeDesc1;
    }

    public void setProAttributeDesc1(String proAttributeDesc1) {
        this.proAttributeDesc1 = proAttributeDesc1;
    }

    public String getProAttributeDesc2() {
        return proAttributeDesc2;
    }

    public void setProAttributeDesc2(String proAttributeDesc2) {
        this.proAttributeDesc2 = proAttributeDesc2;
    }

    public String getProAttributeDesc3() {
        return proAttributeDesc3;
    }

    public void setProAttributeDesc3(String proAttributeDesc3) {
        this.proAttributeDesc3 = proAttributeDesc3;
    }

    public Boolean getProEnabled() {
        return proEnabled;
    }

    public void setProEnabled(Boolean proEnabled) {
        this.proEnabled = proEnabled;
    }

    public ProductType getProTypCode() {
        return proTypCode;
    }

    public void setProTypCode(ProductType proTypCode) {
        this.proTypCode = proTypCode;
    }

    public Ice getIceCode() {
        return iceCode;
    }

    public void setIceCode(Ice iceCode) {
        this.iceCode = iceCode;
    }

    public Iva getIvaCode() {
        return ivaCode;
    }

    public void setIvaCode(Iva ivaCode) {
        this.ivaCode = ivaCode;
    }

    public Irbpnr getIrbCode() {
        return irbCode;
    }

    public void setIrbCode(Irbpnr irbCode) {
        this.irbCode = irbCode;
    }

    @XmlTransient
    public List<BillDetail> getBillDetailList() {
        return billDetailList;
    }

    public void setBillDetailList(List<BillDetail> billDetailList) {
        this.billDetailList = billDetailList;
    }
    
    

    public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (proCode != null ? proCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.proCode == null && other.proCode != null) || (this.proCode != null && !this.proCode.equals(other.proCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.proName;
    }
    
}
