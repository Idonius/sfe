/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.services;

import ec.facturacion.electronica.entities.Client;
import ec.facturacion.electronica.entities.ClientType;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JairoCamilo
 */
@Stateless
public class ClientTypeFacade extends AbstractFacade<ClientType> {
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientTypeFacade() {
        super(ClientType.class);
    }
    
    public List<ClientType> findByEnabled(Boolean enabled){
		try {
			return findByParameters("from ClientType u where u.cliTypEnabled  = ?1", enabled);
		} catch (Exception e) {
			return null;
		}
	}
    
}
