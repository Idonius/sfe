/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.services;

import ec.facturacion.electronica.entities.Bill;
import ec.facturacion.electronica.entities.Client;
import ec.facturacion.electronica.entities.Identification;
import ec.facturacion.electronica.entities.Iva;
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
public class BillFacade extends AbstractFacade<Bill> {
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BillFacade() {
        super(Bill.class);
    }
    
    public int findByCompany(User user){
		try {
			return findByParameters("from Bill b where b.useCode.comCode.comCode = ?1", user.getComCode().getComCode()).size();
		} catch (Exception e) {
			return -1;
		}
	}
    
    public List<Bill> findByClient(Client cliente){
		try {
			return findByParameters("from Bill b where b.cliCode.cliId = ?1", cliente.getCliId());
		} catch (Exception e) {
			return null;
		}
	}
}
