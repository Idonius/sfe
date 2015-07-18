package ec.facturacion.electronica.controllers;

import ec.facturacion.electronica.entities.Identification;
import ec.facturacion.electronica.controllers.util.JsfUtil;
import ec.facturacion.electronica.controllers.util.JsfUtil.PersistAction;
import ec.facturacion.electronica.services.IdentificationFacade;

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

@ManagedBean(name = "identificationController")
@SessionScoped
public class IdentificationController implements Serializable {

    @EJB
    private ec.facturacion.electronica.services.IdentificationFacade ejbFacade;
    private List<Identification> items = null;
    private Identification selected;

    public IdentificationController() {
    }

    public Identification getSelected() {
        return selected;
    }

    public void setSelected(Identification selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IdentificationFacade getFacade() {
        return ejbFacade;
    }

    public Identification prepareCreate() {
        selected = new Identification();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("ec.facturacion.electronica.util.Bundle").getString("IdentificationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("ec.facturacion.electronica.util.Bundle").getString("IdentificationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("ec.facturacion.electronica.util.Bundle").getString("IdentificationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Identification> getItems() {
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

    public List<Identification> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Identification> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Identification.class)
    public static class IdentificationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IdentificationController controller = (IdentificationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "identificationController");
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
            if (object instanceof Identification) {
                Identification o = (Identification) object;
                return getStringKey(o.getIdeCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Identification.class.getName()});
                return null;
            }
        }

    }

}
