package ec.facturacion.electronica.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.component.password.Password;

import ec.facturacion.electronica.entities.Client;
import ec.facturacion.electronica.entities.User;
import ec.facturacion.electronica.services.ClientFacade;
import ec.facturacion.electronica.services.UserFacade;

@Named(value = "loginManager")
@SessionScoped
public class LoginSession implements Serializable {

    @EJB
    UserFacade userService;
    @EJB
    ClientFacade clientService;
    
    private String usuario;
    private String contrasenia;
    private User active;
    private Client cliente;
    
    public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}

	public LoginSession() 
    {
        HttpSession miSession=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        miSession.setMaxInactiveInterval(5000);
    }
    
    public String login()
    {
        if(isNumeric(usuario)){
        	 try
             {
                 cliente = clientService.findById(usuario);
                 
                 if(cliente!=null)
                 {
                     if(cliente.getCliId().equals(contrasenia))
                     {
                         HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                         httpSession.setAttribute("cliente", this.cliente);
                         
                         return "/bill/List";
                     }
                 }
                 
                 this.usuario=null;
                 this.contrasenia=null;
                 this.active=null;
                 
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de acceso:", "Usuario o contraseña incorrecto"));
                 
                 return "/index";
                 
             }
             catch(Exception ex)
             {
                 
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
                 
                 return null;
             }
        }else{
        	 try
             {
                
                 
                 active = userService.findByUsername(usuario);
                 
                 if(active!=null)
                 {
                     if(active.getUsePassword().equals(Encrypt.sha512(this.contrasenia)))
                     {
                         HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                         httpSession.setAttribute("active", this.active);
                         
                         return "/admin/index";
                     }
                 }
                 
                 this.usuario=null;
                 this.contrasenia=null;
                 this.active=null;
                 
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de acceso:", "Usuario o contraseña incorrecto"));
                 
                 return "/index";
                 
             }
             catch(Exception ex)
             {
                 
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
                 
                 return null;
             }
        }
       
    }
    
    public String cerrarSesion()
    {
        this.usuario=null;
        this.contrasenia=null;
        this.active = null;
        this.cliente = null;
        
        HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();
        
        return "/index";
    }
    public boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public User getActive() {
		return active;
	}

	public void setActive(User active) {
		this.active = active;
	}

    
}