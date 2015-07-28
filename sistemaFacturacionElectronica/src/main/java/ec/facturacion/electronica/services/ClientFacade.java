/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.services;

import ec.facturacion.electronica.entities.Client;
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
public class ClientFacade extends AbstractFacade<Client> {
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }
    
    public List<Client> findByEnabled(Boolean enabled){
		try {
			return findByParameters("from Client u where u.cliEnabled = ?1", enabled);
		} catch (Exception e) {
			return null;
		}
	}
    
    public Client findById(String id){
		try {
			return findByParameters("from Client u where u.cliEnabled = ?1 and u.cliId = ?2", true, id).get(0);
		}catch (Exception e) {
			return null;
		}
	}
}
