package ec.facturacion.electronica.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

import ec.facturacion.electronica.controllers.util.JsfUtil;
import ec.facturacion.electronica.entities.Bill;
import ec.facturacion.electronica.entities.BillDetail;
import ec.facturacion.electronica.entities.Client;
import ec.facturacion.electronica.entities.Product;
import ec.facturacion.electronica.entities.User;
import ec.facturacion.electronica.services.BillDetailFacade;
import ec.facturacion.electronica.services.BillFacade;
import ec.facturacion.electronica.services.ClientFacade;
import ec.facturacion.electronica.services.ProductFacade;

@ManagedBean(name = "billerController")
@SessionScoped
public class BillerController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5226882634455572968L;
	@EJB
	private BillFacade ejbBillFacade;
	@EJB
	private ClientFacade ejbClientFacade;
	@EJB
	private ProductFacade ejbProductFacade;
	@EJB
	private BillDetailFacade ejbDBillDetailFacace;
	
	private Bill bill = new Bill();
	private Client client = new Client();
	private String idClient = new String();
	private String nombreProducto = "";
	private String codigoProducto = "";
	private List<Product> lstProducts = new ArrayList<Product>();
	private User active;
	private List<Product> lstSelectedProducts = new ArrayList<Product>();
	private List<BillDetail> lstBillDetail = new ArrayList<BillDetail>();
	private Float total  = 0F;
	private Date dateNow;
	private String claveAccceso;

	public BillerController() {
	}
	
	public void init(User user){
		active = user;
		bill = new Bill();
		client = new Client();
		idClient = "";
		bill.setBilDate(new Date());
		bill.setUseCode(active);
		nombreProducto = "";
		codigoProducto = "";
		lstProducts = new ArrayList<Product>();
		lstSelectedProducts = new ArrayList<Product>();
		lstBillDetail = new ArrayList<BillDetail>();
		total = 0F;
		DateFormat df = new SimpleDateFormat("ddMMyyyy");
		Date today = Calendar.getInstance().getTime();        
		String reportDate = df.format(today);

		claveAccceso = reportDate+"01"+active.getUseRuc()+"1"+String.format("%03d", Integer.valueOf(active.getUseLocalCode()))+String.format("%03d", Integer.valueOf(active.getUseEmissionPoint()))+String.format("%09d", showBillNumber())+String.format("%08d", ejbBillFacade.count())+"1";
		claveAccceso = claveAccceso + JsfUtil.obtenerSumaPorDigitos(JsfUtil.invertirCadena(claveAccceso));
	}
	
	public void update(){
		try{
			if(client != null && client.getCliCode() != null){
				ejbClientFacade.merge(client);
			}else{
				ejbClientFacade.persist(client);
			}
			bill.setCliCode(client);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('ClientEditDialog').hide();");
		}catch (Exception ex){
			JsfUtil.addErrorMessage(ex,"No pudo ser asignado el cliente");
		}
	}
	
	public void saveBill(){
		if(lstBillDetail != null && !lstBillDetail.isEmpty()){
			bill.setBilTotal(total);
			bill.setBillNumber(String.format("%09d", showBillNumber()));		
			updateClaveAcceso();
			bill.setAccessKey(claveAccceso);
			try {
				ejbBillFacade.persist(bill);
				for(BillDetail billDetail: lstBillDetail){
					ejbDBillDetailFacace.persist(billDetail);
				}
				JsfUtil.addSuccessMessage("La Factura: " + bill.getBillNumber() + " fue guardada correctamente.");
				init(bill.getUseCode());
			} catch (Exception e) {
				JsfUtil.addErrorMessage("La factura: " + bill.getBillNumber() + " no pudo ser guardada correctamente.");
			}
		}else{
			JsfUtil.addSuccessMessage("Debe añadir un producto como minimo");
		}
		
	}
	
	public int showBillNumber(){
		return ejbBillFacade.findByCompany(active)+1;
	}
	
	public void editClient(){
		if(bill.getCliCode() != null){
			client = bill.getCliCode();
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('ClientEditDialog').show();");
		}else{
			JsfUtil.addErrorMessage("No existe un cliente asignado a la Factura");
		}
	}
	
	public void findClient(){
		client = new Client();
		client = ejbClientFacade.findById(idClient);
		if(client == null){
			client = new Client();
			client.setCliEnabled(new Boolean(true));
			client.setCliId(idClient);
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('ClientEditDialog').show();");
	}
	
	public void findProduct(){
		if(!nombreProducto.isEmpty()){
			nombreProducto = "%" + nombreProducto + "%";
		}
		if(!codigoProducto.isEmpty()){
			codigoProducto = "%" + codigoProducto + "%";
		}
		lstProducts = ejbProductFacade.findByNameAndCodeAux(true, nombreProducto, codigoProducto, active);
		lstProducts.size();
	}
	
	public void openProductSearch(){
		nombreProducto = "";
		codigoProducto = "";
		lstProducts = new ArrayList<Product>();
		lstSelectedProducts = new ArrayList<Product>();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('productDialog').show();");
	}
	
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        lstBillDetail.get(event.getRowIndex()).setTotal(lstBillDetail.get(event.getRowIndex()).getProCode().getProValue() * (Float) newValue);
        total = 0F;
        for(BillDetail bill: lstBillDetail){
        	total += bill.getTotal();
        }
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
	
	public void addProduct(){
		try{
			if(lstSelectedProducts != null && !lstSelectedProducts.isEmpty()){
				for(Product prod:lstSelectedProducts){
					BillDetail billDetail = new BillDetail();
					billDetail.setBilCode(bill);
					billDetail.setProCode(prod);
					billDetail.setBilDetCant(1F);
					billDetail.setTotal(billDetail.getProCode().getProValue());
					lstBillDetail.add(billDetail);
				}
				total = 0F;
		        for(BillDetail bill: lstBillDetail){
		        	total += bill.getTotal();
		        }
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('productDialog').hide();");
			}
		}catch(Exception ex){
			JsfUtil.addErrorMessage(ex,"No se pudo asignar los productos seleccionados");
		}
		
	}
	
	public void updateClaveAcceso(){
		DateFormat df = new SimpleDateFormat("ddMMyyyy");
		Date today = bill.getBilDate();
		String reportDate = df.format(today);
		claveAccceso = "";
		claveAccceso = reportDate+"01"+active.getUseRuc()+"1"+String.format("%03d", Integer.valueOf(active.getUseLocalCode()))+String.format("%03d", Integer.valueOf(active.getUseEmissionPoint()))+String.format("%09d", showBillNumber())+String.format("%08d", ejbBillFacade.count())+"1";
		claveAccceso = claveAccceso + JsfUtil.obtenerSumaPorDigitos(JsfUtil.invertirCadena(claveAccceso));
	}

	public void generateXml(Bill bill){
		try {
			Element factura = new Element("factura");
			factura.setAttribute(new Attribute("id", "comprobante"));
			factura.setAttribute(new Attribute("version", "1.1.0"));
			Document doc = new Document(factura);
			doc.setRootElement(factura);

			Element infoTributaria = new Element("infoTributaria");
			infoTributaria.addContent(new Element("ambiente").setText("1"));
			infoTributaria.addContent(new Element("tipoEmision").setText("1"));
			infoTributaria.addContent(new Element("razonSocial").setText(bill.getUseCode().getUseFirstName()+ " "+bill.getUseCode().getUseLastName()));
			infoTributaria.addContent(new Element("nombreComercial").setText(bill.getUseCode().getUseComName()));
			infoTributaria.addContent(new Element("ruc").setText(bill.getUseCode().getUseRuc()));
			infoTributaria.addContent(new Element("claveAcceso").setText(bill.getAccessKey()));
			infoTributaria.addContent(new Element("codDoc").setText("01"));
			infoTributaria.addContent(new Element("estab").setText(String.format("%03d", Integer.valueOf(bill.getUseCode().getUseEmissionPoint()))));
			infoTributaria.addContent(new Element("ptoEmi").setText(String.format("%03d", Integer.valueOf(bill.getUseCode().getUseLocalCode()))));
			infoTributaria.addContent(new Element("secuencial").setText(bill.getBillNumber()));
			infoTributaria.addContent(new Element("dirMatriz").setText(bill.getUseCode().getUsePrincipalAddr()));
			doc.getRootElement().addContent(infoTributaria);

			Element infoFactura = new Element("infoFactura");
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String reportDate = df.format(bill.getBilDate());
			infoFactura.addContent(new Element("fechaEmision").setText(reportDate));
			infoFactura.addContent(new Element("dirEstablecimiento").setText(bill.getUseCode().getUseLocalAddr()));
			if(bill.getUseCode().getUseResolution() != null && !bill.getUseCode().getUseResolution().isEmpty()){
				infoFactura.addContent(new Element("contribuyenteEspecial").setText(bill.getUseCode().getUseResolution()));
			}
			if(bill.getUseCode().getUseAccounting()){
				infoFactura.addContent(new Element("obligadoContabilidad").setText("SI"));
			}else{
				infoFactura.addContent(new Element("obligadoContabilidad").setText("NO"));
			}
			infoFactura.addContent(new Element("tipoIdentificacionComprador").setText(bill.getCliCode().getIdeCode().getIdeCodeExt()));
			infoFactura.addContent(new Element("razonSocialComprador").setText(bill.getCliCode().getCliSocialReason()));
			infoFactura.addContent(new Element("identificacionComprador").setText(bill.getCliCode().getCliId()));
			infoFactura.addContent(new Element("totalSinImpuestos").setText(bill.getBilTotal().toString()));
			doc.getRootElement().addContent(infoFactura);

			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("//home//jairo//Documentos//file.xml"));

			System.out.println("File Saved!");
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  }
	}
	
	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public List<Product> getLstProducts() {
		return lstProducts;
	}

	public void setLstProducts(List<Product> lstProducts) {
		this.lstProducts = lstProducts;
	}

	public User getActive() {
		return active;
	}

	public void setActive(User active) {
		this.active = active;
	}

	public List<Product> getLstSelectedProducts() {
		return lstSelectedProducts;
	}

	public void setLstSelectedProducts(List<Product> lstSelectedProducts) {
		this.lstSelectedProducts = lstSelectedProducts;
	}

	public List<BillDetail> getLstBillDetail() {
		return lstBillDetail;
	}

	public void setLstBillDetail(List<BillDetail> lstBillDetail) {
		this.lstBillDetail = lstBillDetail;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Date getDateNow() {
		dateNow = new Date();
		return dateNow;
	}

	public void setDateNow(Date dateNow) {
		this.dateNow = dateNow;
	}

	public String getClaveAccceso() {
		return claveAccceso;
	}

	public void setClaveAccceso(String claveAccceso) {
		this.claveAccceso = claveAccceso;
	}
	
	
}
