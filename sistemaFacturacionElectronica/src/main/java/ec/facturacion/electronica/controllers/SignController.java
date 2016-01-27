package ec.facturacion.electronica.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.facturacion.electronica.controllers.util.JsfUtil;
import ec.facturacion.electronica.entities.Bill;
import ec.facturacion.electronica.firmado.EnvelopedSignature;

@ManagedBean(name = "signController")
@SessionScoped
public class SignController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 522688264455572968L;
	private Bill bill = new Bill();
	
	public SignController() {
	}
	
	public void signXML(){
		try{
			EnvelopedSignature signature = new EnvelopedSignature("//home//jairo//Documentos//Firma//marcelo_xavier_arroyo_arguello.p12", "hEllo6482", "//home//jairo//Documentos//XMLFirmados//", bill.getXml(), bill.getAccessKey()+".xml");
			signature.execute();
			JsfUtil.addSuccessMessage("Se firmo correctamente el archivo XML");
		}catch(Exception e){
			JsfUtil.addErrorMessage("Error al firmar el archivo XML");
		}
	}
	
	public void sendXML(){
		try{
			EnvelopedSignature signature = new EnvelopedSignature("//home//jairo//Documentos//Firma//marcelo_xavier_arroyo_arguello.p12", "hEllo6482", "//home//jairo//Documentos//XMLFirmados//", bill.getXml(), bill.getAccessKey()+".xml");
			signature.execute();
			JsfUtil.addSuccessMessage("Se firmo correctamente el archivo XML");
		}catch(Exception e){
			JsfUtil.addErrorMessage("Error al firmar el archivo XML");
		}
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
}
