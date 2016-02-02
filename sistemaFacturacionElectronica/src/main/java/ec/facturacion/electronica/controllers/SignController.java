package ec.facturacion.electronica.controllers;

import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import ec.facturacion.electronica.controllers.util.JsfUtil;
import ec.facturacion.electronica.entities.Bill;
import ec.facturacion.electronica.firmado.EnvelopedSignature;
import ec.gob.sri.comprobantes.ws.RecepcionComprobantes;
import ec.gob.sri.comprobantes.ws.RecepcionComprobantesService;
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;

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
			Path path = Paths.get("//home//jairo//Documentos//XMLFirmados//xmlFirmadoTest.xml");
			byte[] data = Files.readAllBytes(path);
			URL url = new URL("https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl");

	        // Qualified name of the service:
	        //   1st arg is the service URI
	        //   2nd is the service name published in the WSDL

	        QName qname = new QName("http://ec.gob.sri.ws.recepcion", "RecepcionComprobantesService");

	        // Create, in effect, a factory for the service.

	        Service service = Service.create(url, qname);
	        // Extract the endpoint interface, the service "port".
	        RecepcionComprobantes recepcion = service.getPort(RecepcionComprobantes.class);
	        RespuestaSolicitud respuesta = new RespuestaSolicitud();
			respuesta = recepcion.validarComprobante(data);
			JsfUtil.addSuccessMessage(respuesta.getEstado());
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
