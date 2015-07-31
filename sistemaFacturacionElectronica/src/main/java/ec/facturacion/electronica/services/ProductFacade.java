/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.services;

import ec.facturacion.electronica.entities.Iva;
import ec.facturacion.electronica.entities.Product;
import ec.facturacion.electronica.entities.User;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JairoCamilo
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    public List<Product> findByEnabled(Boolean enabled){
		try {
			return findByParameters("from Product u where u.proEnabled = ?1", enabled);
		} catch (Exception e) {
			return null;
		}
	}
    
    public List<Product> findByNameAndCodeAux(Boolean enabled, String name, String code, User user){
		try {
			if(!name.isEmpty() && !code.isEmpty()){
				return findByParameters("from Product u where u.proEnabled = ?1 and u.proName like ?2 and u.proPrincipalCode like ?3 and u.company.comCode = ?4", enabled, name, code, user.getComCode().getComCode());
			}else if(name.isEmpty()){
				return findByParameters("from Product u where u.proEnabled = ?1 and u.proPrincipalCode like ?2 and u.company.comCode = ?3", enabled, code, user.getComCode().getComCode());
			}else{
				return findByParameters("from Product u where u.proEnabled = ?1 and u.proName like ?2 and u.company.comCode = ?3", enabled, name, user.getComCode().getComCode());
			}
		} catch (Exception e) {
			return null;
		}
	}
    
}
