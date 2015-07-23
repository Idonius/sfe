/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.services;

import ec.facturacion.electronica.entities.Company;
import ec.facturacion.electronica.entities.EmissionType;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JairoCamilo
 */
@Stateless
public class EmissionTypeFacade extends AbstractFacade<EmissionType> {
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmissionTypeFacade() {
        super(EmissionType.class);
    }
    
    public List<EmissionType> findByEnabled(Boolean enabled){
		try {
			return findByParameters("from EmissionType u where u.emiTypeEnabled  = ?1", enabled);
		} catch (Exception e) {
			return null;
		}
	}
    
}
