/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JairoCamilo
 */
@Entity
@Table(name = "BILL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
    @NamedQuery(name = "Bill.findByBilCode", query = "SELECT b FROM Bill b WHERE b.bilCode = :bilCode"),
    @NamedQuery(name = "Bill.findByBilDate", query = "SELECT b FROM Bill b WHERE b.bilDate = :bilDate"),
    @NamedQuery(name = "Bill.findByBillGuideRemission", query = "SELECT b FROM Bill b WHERE b.billGuideRemission = :billGuideRemission"),
    @NamedQuery(name = "Bill.findByBilDiscount", query = "SELECT b FROM Bill b WHERE b.bilDiscount = :bilDiscount"),
    @NamedQuery(name = "Bill.findByBilTip", query = "SELECT b FROM Bill b WHERE b.bilTip = :bilTip"),
    @NamedQuery(name = "Bill.findByBilTipValue", query = "SELECT b FROM Bill b WHERE b.bilTipValue = :bilTipValue"),
    @NamedQuery(name = "Bill.findByBilTotal", query = "SELECT b FROM Bill b WHERE b.bilTotal = :bilTotal")})
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BIL_CODE")
    private Integer bilCode;
    @Column(name = "BIL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bilDate;
    @Size(max = 255)
    @Column(name = "BILL_GUIDE_REMISSION")
    private String billGuideRemission;
    @Size(max = 255)
    @Column(name = "BILL_NUMBER")
    private String billNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BIL_DISCOUNT")
    private Float bilDiscount;
    @Column(name = "BIL_TIP")
    private Boolean bilTip;
    @Column(name = "BIL_TIP_VALUE")
    private Float bilTipValue;
    @Column(name = "BIL_TOTAL")
    private Float bilTotal;
    @JoinColumn(name = "CLI_CODE", referencedColumnName = "CLI_CODE")
    @ManyToOne(optional = false)
    private Client cliCode;
    @JoinColumn(name = "USE_CODE", referencedColumnName = "USE_CODE")
    @ManyToOne(optional = false)
    private User useCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bilCode")
    private List<BillDetail> billDetailList;
    @Size(max = 49)
    @Column(name = "BILL_ACCESS_KEY")
    private String accessKey;
    @Size(max = 255)
    @Column(name = "BILL_XML")
    private String xml;

    public Bill() {
    }

    public Bill(Integer bilCode) {
        this.bilCode = bilCode;
    }

    public Integer getBilCode() {
        return bilCode;
    }

    public void setBilCode(Integer bilCode) {
        this.bilCode = bilCode;
    }

    public Date getBilDate() {
        return bilDate;
    }

    public void setBilDate(Date bilDate) {
        this.bilDate = bilDate;
    }

    public String getBillGuideRemission() {
        return billGuideRemission;
    }

    public void setBillGuideRemission(String billGuideRemission) {
        this.billGuideRemission = billGuideRemission;
    }

    public Float getBilDiscount() {
        return bilDiscount;
    }

    public void setBilDiscount(Float bilDiscount) {
        this.bilDiscount = bilDiscount;
    }

    public Boolean getBilTip() {
        return bilTip;
    }

    public void setBilTip(Boolean bilTip) {
        this.bilTip = bilTip;
    }

    public Float getBilTipValue() {
        return bilTipValue;
    }

    public void setBilTipValue(Float bilTipValue) {
        this.bilTipValue = bilTipValue;
    }

    public Float getBilTotal() {
        return bilTotal;
    }

    public void setBilTotal(Float bilTotal) {
        this.bilTotal = bilTotal;
    }

    public Client getCliCode() {
        return cliCode;
    }

    public void setCliCode(Client cliCode) {
        this.cliCode = cliCode;
    }

    public User getUseCode() {
        return useCode;
    }

    public void setUseCode(User useCode) {
        this.useCode = useCode;
    }

    @XmlTransient
    public List<BillDetail> getBillDetailList() {
        return billDetailList;
    }

    public void setBillDetailList(List<BillDetail> billDetailList) {
        this.billDetailList = billDetailList;
    }
    
    public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (bilCode != null ? bilCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.bilCode == null && other.bilCode != null) || (this.bilCode != null && !this.bilCode.equals(other.bilCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.facturacion.electronica.entities.Bill[ bilCode=" + bilCode + " ]";
    }

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
    
}
