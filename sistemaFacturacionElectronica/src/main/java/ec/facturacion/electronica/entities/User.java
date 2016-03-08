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
@Table(name = "USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUseCode", query = "SELECT u FROM User u WHERE u.useCode = :useCode"),
    @NamedQuery(name = "User.findByUseName", query = "SELECT u FROM User u WHERE u.useName = :useName"),
    @NamedQuery(name = "User.findByUsePassword", query = "SELECT u FROM User u WHERE u.usePassword = :usePassword"),
    @NamedQuery(name = "User.findByUseRuc", query = "SELECT u FROM User u WHERE u.useRuc = :useRuc"),
    @NamedQuery(name = "User.findByUseFirstName", query = "SELECT u FROM User u WHERE u.useFirstName = :useFirstName"),
    @NamedQuery(name = "User.findByUseLastName", query = "SELECT u FROM User u WHERE u.useLastName = :useLastName"),
    @NamedQuery(name = "User.findByUseComName", query = "SELECT u FROM User u WHERE u.useComName = :useComName"),
    @NamedQuery(name = "User.findByUseLocalCode", query = "SELECT u FROM User u WHERE u.useLocalCode = :useLocalCode"),
    @NamedQuery(name = "User.findByUseResolution", query = "SELECT u FROM User u WHERE u.useResolution = :useResolution"),
    @NamedQuery(name = "User.findByUseAccounting", query = "SELECT u FROM User u WHERE u.useAccounting = :useAccounting"),
    @NamedQuery(name = "User.findByUseImage", query = "SELECT u FROM User u WHERE u.useImage = :useImage"),
    @NamedQuery(name = "User.findByUseEmissionPoint", query = "SELECT u FROM User u WHERE u.useEmissionPoint = :useEmissionPoint"),
    @NamedQuery(name = "User.findByUseEnabled", query = "SELECT u FROM User u WHERE u.useEnabled = :useEnabled")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USE_CODE")
    private Integer useCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USE_NAME")
    private String useName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USE_PASSWORD")
    private String usePassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USE_RUC")
    private String useRuc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USE_FIRST_NAME")
    private String useFirstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USE_LAST_NAME")
    private String useLastName;
    @Size(max = 255)
    @Column(name = "USE_COM_NAME")
    private String useComName;
    @Lob
    @Size(max = 65535)
    @Column(name = "USE_PRINCIPAL_ADDR")
    private String usePrincipalAddr;
    @Lob
    @Size(max = 65535)
    @Column(name = "USE_LOCAL_ADDR")
    private String useLocalAddr;
    @Size(max = 255)
    @Column(name = "USE_LOCAL_CODE")
    private String useLocalCode;
    @Size(max = 255)
    @Column(name = "USE_RESOLUTION")
    private String useResolution;
    @Column(name = "USE_ACCOUNTING")
    private Boolean useAccounting;
    @Size(max = 255)
    @Column(name = "USE_IMAGE")
    private String useImage;
    @Size(max = 255)
    @Column(name = "USE_EMISSION_POINT")
    private String useEmissionPoint;
    @Column(name = "USE_ENABLED")
    private Boolean useEnabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "useCode")
    private List<Bill> billList;
    @JoinColumn(name = "COM_CODE", referencedColumnName = "COM_CODE")
    @ManyToOne(optional = false)
    private Company comCode;
    @JoinColumn(name = "ROL_CODE", referencedColumnName = "ROL_CODE")
    @ManyToOne(optional = false)
    private Rol rolCode;
    @JoinColumn(name = "EMI_TYP_CODE", referencedColumnName = "EMI_TYP_CODE")
    @ManyToOne(optional = false)
    private EmissionType emiTypCode;
    @Size(max = 255)
    @Column(name = "USE_PATH_KEY")
    private String usePathkey;
    @Size(max = 45)
    @Column(name = "USE_PASS_KEY")
    private String usePasskey;
    
    public User() {
    }

    public User(Integer useCode) {
        this.useCode = useCode;
    }

    public User(Integer useCode, String useName, String usePassword, String useRuc, String useFirstName, String useLastName) {
        this.useCode = useCode;
        this.useName = useName;
        this.usePassword = usePassword;
        this.useRuc = useRuc;
        this.useFirstName = useFirstName;
        this.useLastName = useLastName;
    }

    public Integer getUseCode() {
        return useCode;
    }

    public void setUseCode(Integer useCode) {
        this.useCode = useCode;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getUsePassword() {
        return usePassword;
    }

    public void setUsePassword(String usePassword) {
        this.usePassword = usePassword;
    }

    public String getUseRuc() {
        return useRuc;
    }

    public void setUseRuc(String useRuc) {
        this.useRuc = useRuc;
    }

    public String getUseFirstName() {
        return useFirstName;
    }

    public void setUseFirstName(String useFirstName) {
        this.useFirstName = useFirstName;
    }

    public String getUseLastName() {
        return useLastName;
    }

    public void setUseLastName(String useLastName) {
        this.useLastName = useLastName;
    }

    public String getUseComName() {
        return useComName;
    }

    public void setUseComName(String useComName) {
        this.useComName = useComName;
    }

    public String getUsePrincipalAddr() {
        return usePrincipalAddr;
    }

    public void setUsePrincipalAddr(String usePrincipalAddr) {
        this.usePrincipalAddr = usePrincipalAddr;
    }

    public String getUseLocalAddr() {
        return useLocalAddr;
    }

    public void setUseLocalAddr(String useLocalAddr) {
        this.useLocalAddr = useLocalAddr;
    }

    public String getUseLocalCode() {
        return useLocalCode;
    }

    public void setUseLocalCode(String useLocalCode) {
        this.useLocalCode = useLocalCode;
    }

    public String getUseResolution() {
        return useResolution;
    }

    public void setUseResolution(String useResolution) {
        this.useResolution = useResolution;
    }

    public Boolean getUseAccounting() {
        return useAccounting;
    }

    public void setUseAccounting(Boolean useAccounting) {
        this.useAccounting = useAccounting;
    }

    public String getUseImage() {
        return useImage;
    }

    public void setUseImage(String useImage) {
        this.useImage = useImage;
    }

    public String getUseEmissionPoint() {
        return useEmissionPoint;
    }

    public void setUseEmissionPoint(String useEmissionPoint) {
        this.useEmissionPoint = useEmissionPoint;
    }

    public Boolean getUseEnabled() {
        return useEnabled;
    }

    public void setUseEnabled(Boolean useEnabled) {
        this.useEnabled = useEnabled;
    }

    @XmlTransient
    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public Company getComCode() {
        return comCode;
    }

    public void setComCode(Company comCode) {
        this.comCode = comCode;
    }

    public Rol getRolCode() {
        return rolCode;
    }

    public void setRolCode(Rol rolCode) {
        this.rolCode = rolCode;
    }

    public EmissionType getEmiTypCode() {
        return emiTypCode;
    }

    public void setEmiTypCode(EmissionType emiTypCode) {
        this.emiTypCode = emiTypCode;
    }

    public String getUsePathkey() {
		return usePathkey;
	}

	public void setUsePathkey(String usePathkey) {
		this.usePathkey = usePathkey;
	}

	public String getUsePasskey() {
		return usePasskey;
	}

	public void setUsePasskey(String usePasskey) {
		this.usePasskey = usePasskey;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (useCode != null ? useCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.useCode == null && other.useCode != null) || (this.useCode != null && !this.useCode.equals(other.useCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.useFirstName + " " + this.useLastName;
    }
    
}
