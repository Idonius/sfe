/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.facturacion.electronica.services;

import ec.facturacion.electronica.entities.Bill;
import ec.facturacion.electronica.entities.BillDetail;
import ec.facturacion.electronica.entities.Identification;
import ec.facturacion.electronica.entities.Iva;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JairoCamilo
 */
@Stateless
public class BillDetailFacade extends AbstractFacade<BillDetail> {
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BillDetailFacade() {
        super(BillDetail.class);
    }
    
}
