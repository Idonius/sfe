package ec.facturacion.electronica.controllers;

import ec.facturacion.electronica.entities.Product;
import ec.facturacion.electronica.entities.User;
import ec.facturacion.electronica.controllers.util.JsfUtil;
import ec.facturacion.electronica.controllers.util.JsfUtil.PersistAction;
import ec.facturacion.electronica.services.ProductFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "productController")
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private ec.facturacion.electronica.services.ProductFacade ejbFacade;
    private List<Product> items = null;
    private Product selected;
    private User active = null;

    public ProductController() {
    }

    public Product getSelected() {
        return selected;
    }

    public void setSelected(Product selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProductFacade getFacade() {
        return ejbFacade;
    }

    public Product prepareCreate() {
        selected = new Product();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(User user) {
    	active = user;
        persist(PersistAction.CREATE, ResourceBundle.getBundle("ec.facturacion.electronica.util.Bundle").getString("ProductCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update(User user) {
    	active = user;
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("ec.facturacion.electronica.util.Bundle").getString("ProductUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("ec.facturacion.electronica.util.Bundle").getString("ProductDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Product> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                	selected.setCompany(active.getComCode());
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("ec.facturacion.electronica.util.Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("ec.facturacion.electronica.util.Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Product> getItemsAvailableSelectMany() {
        return getFacade().findByEnabled(new Boolean(true));
    }

    public List<Product> getItemsAvailableSelectOne() {
        return getFacade().findByEnabled(new Boolean(true));
    }

    public User getActive() {
		return active;
	}

	public void setActive(User active) {
		this.active = active;
	}

	@FacesConverter(forClass = Product.class)
    public static class ProductControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductController controller = (ProductController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Product) {
                Product o = (Product) object;
                return getStringKey(o.getProCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Product.class.getName()});
                return null;
            }
        }

    }

}
