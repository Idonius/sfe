package ec.facturacion.electronica.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
import ec.facturacion.electronica.util.LoginSession;

@ManagedBean(name = "billListController")
@SessionScoped
public class BillListController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5226882634455572968L;
	@EJB
	private BillFacade ejbBillFacade;
	@EJB
	private BillDetailFacade ejbBillDetailFacade;

    @ManagedProperty(value="#{loginManager}")
    private LoginSession loginBean;
    
	private Bill bill = new Bill();
	private List<Bill> lstBill = new ArrayList<Bill>();
	
	public BillListController() {
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		bill.setBillDetailList(ejbBillDetailFacade.findByBill(bill));
		this.bill = bill;
	}

	public List<Bill> getLstBill() {
		if(loginBean.getCliente() != null){
			lstBill = ejbBillFacade.findByClient(loginBean.getCliente());
		}else{
			lstBill=ejbBillFacade.findAll();
		}
		return lstBill;
	}

	public void setLstBill(List<Bill> lstBill) {
		this.lstBill = lstBill;
	}

	public void setLoginBean(LoginSession loginBean) {
		this.loginBean = loginBean;
	}
	
	
}
