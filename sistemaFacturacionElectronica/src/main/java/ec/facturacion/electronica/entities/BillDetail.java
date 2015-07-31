/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JairoCamilo
 */
@Entity
@Table(name = "BILL_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BillDetail.findAll", query = "SELECT b FROM BillDetail b"),
    @NamedQuery(name = "BillDetail.findByBilDetCode", query = "SELECT b FROM BillDetail b WHERE b.bilDetCode = :bilDetCode"),
    @NamedQuery(name = "BillDetail.findByBilDetCant", query = "SELECT b FROM BillDetail b WHERE b.bilDetCant = :bilDetCant")})
public class BillDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BIL_DET_CODE")
    private Integer bilDetCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BIL_DET_CANT")
    private Float bilDetCant;
    @JoinColumn(name = "BIL_CODE", referencedColumnName = "BIL_CODE")
    @ManyToOne(optional = false)
    private Bill bilCode;
    @JoinColumn(name = "PRO_CODE", referencedColumnName = "PRO_CODE")
    @ManyToOne(optional = false)
    private Product proCode;
    private transient Float total;
    
    public BillDetail() {
    }

    public BillDetail(Integer bilDetCode) {
        this.bilDetCode = bilDetCode;
    }

    public Integer getBilDetCode() {
        return bilDetCode;
    }

    public void setBilDetCode(Integer bilDetCode) {
        this.bilDetCode = bilDetCode;
    }

    public Float getBilDetCant() {
        return bilDetCant;
    }

    public void setBilDetCant(Float bilDetCant) {
        this.bilDetCant = bilDetCant;
    }

    public Bill getBilCode() {
        return bilCode;
    }

    public void setBilCode(Bill bilCode) {
        this.bilCode = bilCode;
    }

    public Product getProCode() {
        return proCode;
    }

    public void setProCode(Product proCode) {
        this.proCode = proCode;
    }

    @Transient
    public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (bilDetCode != null ? bilDetCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillDetail)) {
            return false;
        }
        BillDetail other = (BillDetail) object;
        if ((this.bilDetCode == null && other.bilDetCode != null) || (this.bilDetCode != null && !this.bilDetCode.equals(other.bilDetCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.BillDetail[ bilDetCode=" + bilDetCode + " ]";
    }
    
}
