/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.facturacion.electronica.firmado;

/**
 *
 * @author aaguerra
 */

import java.security.cert.X509Certificate;

import es.mityc.javasign.pkstore.IPassStoreKS;

public class PassStoreKS implements IPassStoreKS {
	/** Contrase�a de acceso al almac�n. */
  	private transient String password;
  	
  	/**
  	 * <p>Crea una instancia con la contrase�a que se utilizar� con el almac�n relacionado.</p>
  	 * @param pass Contrase�a del almac�n
  	 */
  	public PassStoreKS(final String pass) {
  		this.password = new String(pass);
  	}
  
  	/**
  	 * <p>Devuelve la contrase�a configurada para este almac�n.</p>
  	 * @param certificate No se utiliza
  	 * @param alias no se utiliza
  	 * @return contrase�a configurada para este almac�n
  	 * @see es.mityc.javasign.pkstore.IPassStoreKS#getPassword(java.security.cert.X509Certificate, java.lang.String)
  	 */
  	public char[] getPassword( X509Certificate certificate, String alias) {
  		return password.toCharArray();
  	}

}