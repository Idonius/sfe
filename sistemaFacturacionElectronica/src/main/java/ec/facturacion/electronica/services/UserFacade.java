/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.services;

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
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findByUsername(String usuario){
		try {
			return findByParameters("from User u where u.useName = ?1 and u.useEnabled = ?2", usuario,true).get(0);
		} catch (Exception e) {
			return null;
		}
	}
    
    public List<User> findByEnabled(Boolean enabled){
		try {
			return findByParameters("from User u where u.useEnabled = ?1", enabled);
		} catch (Exception e) {
			return null;
		}
	}
    
}
