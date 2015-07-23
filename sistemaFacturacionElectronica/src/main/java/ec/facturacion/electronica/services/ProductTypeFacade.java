/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.services;

import ec.facturacion.electronica.entities.Product;
import ec.facturacion.electronica.entities.ProductType;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JairoCamilo
 */
@Stateless
public class ProductTypeFacade extends AbstractFacade<ProductType> {
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductTypeFacade() {
        super(ProductType.class);
    }
    
    public List<ProductType> findByEnabled(Boolean enabled){
		try {
			return findByParameters("from ProductType u where u.proTypEnabled= ?1", enabled);
		} catch (Exception e) {
			return null;
		}
	}
}
