/**
  * Copyright 2013 Ministerio de Industria, EnergÃ­a y Turismo
  *
  * Este fichero es parte de "Componentes de Firma XAdES 1.1.7".
  *
  * Licencia con arreglo a la EUPL, VersiÃ³n 1.1 o â€“en cuanto sean aprobadas por la ComisiÃ³n Europeaâ€“ versiones posteriores de la EUPL (la Licencia);
  * Solo podrÃ¡ usarse esta obra si se respeta la Licencia.
  *
  * Puede obtenerse una copia de la Licencia en:
  *
  * http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
  *
  * Salvo cuando lo exija la legislaciÃ³n aplicable o se acuerde por escrito, el programa distribuido con arreglo a la Licencia se distribuye Â«TAL CUALÂ»,
  * SIN GARANTÃ�AS NI CONDICIONES DE NINGÃšN TIPO, ni expresas ni implÃ­citas.
  * VÃ©ase la Licencia en el idioma concreto que rige los permisos y limitaciones que establece la Licencia.
  */
package ec.facturacion.electronica.firmado;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import es.mityc.firmaJava.libreria.utilidades.UtilidadTratarNodo;
import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.FirmaXML;
//import es.mityc.javasign.issues.PassStoreKS;
import es.mityc.javasign.pkstore.CertStoreException;
import es.mityc.javasign.pkstore.IPKStoreManager;
import es.mityc.javasign.pkstore.keystore.KSStore;
 
 
 /**
  * <p>
  * Clase base que deberÃ­an extender los diferentes ejemplos para realizar firmas
  * XML.
  * </p>
  * 
  */
 public abstract class GenericXMLSignature {
 
     /**
      * <p>
      * AlmacÃ©n PKCS12 con el que se desea realizar la firma
      * </p>
      */
     public static String PKCS12_RESOURCE = "C:\\firmas\\marcelo_xavier_arroyo_arguello.p12";
 
     /**
      * <p>
      * ConstraseÃ±a de acceso a la clave privada del usuario
      * </p>
      */
     public static String PKCS12_PASSWORD = "hEllo6482";
 
     /**
      * <p>
      * Directorio donde se almacenarÃ¡ el resultado de la firma
      * </p>
      */
     public static String OUTPUT_DIRECTORY = "C:\\firmas\\";
     
     
 

	public GenericXMLSignature(String pathSign, String pass, String pathOut) {
		PKCS12_RESOURCE = pathSign;
		PKCS12_PASSWORD = pass;
		OUTPUT_DIRECTORY = pathOut;
	}

	/**
      * <p>
      * EjecuciÃ³n del ejemplo. La ejecuciÃ³n consistirÃ¡ en la firma de los datos
      * creados por el mÃ©todo abstracto <code>createDataToSign</code> mediante el
      * certificado declarado en la constante <code>PKCS12_FILE</code>. El
      * resultado del proceso de firma serÃ¡ almacenado en un fichero XML en el
      * directorio correspondiente a la constante <code>OUTPUT_DIRECTORY</code>
      * del usuario bajo el nombre devuelto por el mÃ©todo abstracto
      * <code>getSignFileName</code>
      * </p>
      */
     public void execute() {
 
         // Obtencion del gestor de claves
         IPKStoreManager storeManager = getPKStoreManager();
         if (storeManager == null) {
             System.err.println("El gestor de claves no se ha obtenido correctamente.");
             return;
         }
 
         // Obtencion del certificado para firmar. Utilizaremos el primer
         // certificado del almacen.
         X509Certificate certificate = getFirstCertificate(storeManager);
         if (certificate == null) {
             System.err.println("No existe ningÃºn certificado para firmar.");
             return;
         }
 
         // ObtenciÃ³n de la clave privada asociada al certificado
         PrivateKey privateKey;
         try {
             privateKey = storeManager.getPrivateKey(certificate);
         } catch (CertStoreException e) {
             System.err.println("Error al acceder al almacÃ©n.");
             return;
         }
 
         // ObtenciÃ³n del provider encargado de las labores criptogrÃ¡ficas
         Provider provider = storeManager.getProvider(certificate);
 
         /*
          * CreaciÃ³n del objeto que contiene tanto los datos a firmar como la
          * configuraciÃ³n del tipo de firma
          */
         DataToSign dataToSign = createDataToSign();
 
         /*
          * CreaciÃ³n del objeto encargado de realizar la firma
          */
         FirmaXML firma = new FirmaXML();
 
         // Firmamos el documento
         Document docSigned = null;
         try {
             Object[] res = firma.signFile(certificate, dataToSign, privateKey, provider);
             docSigned = (Document) res[0];
         } catch (Exception ex) {
             System.err.println("Error realizando la firma");
             ex.printStackTrace();
             return;
         }
 
         // Guardamos la firma a un fichero en el home del usuario
         String filePath = OUTPUT_DIRECTORY + getSignatureFileName();
         System.out.println("Firma salvada en en: " + filePath);
         saveDocumentToFile(docSigned, filePath);
     }
 
     /**
      * <p>
      * Crea el objeto DataToSign que contiene toda la informaciÃ³n de la firma
      * que se desea realizar. Todas las implementaciones deberÃ¡n proporcionar
      * una implementaciÃ³n de este mÃ©todo
      * </p>
      * 
      * @return El objeto DataToSign que contiene toda la informaciÃ³n de la firma
      *         a realizar
      */
     protected abstract DataToSign createDataToSign();
 
     /**
      * <p>
      * Nombre del fichero donde se desea guardar la firma generada. Todas las
      * implementaciones deberÃ¡n proporcionar este nombre.
      * </p>
      * 
      * @return El nombre donde se desea guardar la firma generada
      */
     protected abstract String getSignatureFileName();
 
     /**
      * <p>
      * Escribe el documento a un fichero.
      * </p>
      * 
      * @param document
      *            El documento a imprmir
      * @param pathfile
      *            El path del fichero donde se quiere escribir.
      */
     private void saveDocumentToFile(Document document, String pathfile) {
         try {
             FileOutputStream fos = new FileOutputStream(pathfile);
             UtilidadTratarNodo.saveDocumentToOutputStream(document, fos, true);
         } catch (FileNotFoundException e) {
             System.err.println("Error al salvar el documento");
             e.printStackTrace();
             System.exit(-1);
         }
     }
 
     /**
      * <p>
      * Escribe el documento a un fichero. Esta implementacion es insegura ya que
      * dependiendo del gestor de transformadas el contenido podrÃ­a ser alterado,
      * con lo que el XML escrito no serÃ­a correcto desde el punto de vista de
      * validez de la firma.
      * </p>
      * 
      * @param document
      *            El documento a imprmir
      * @param pathfile
      *            El path del fichero donde se quiere escribir.
      */
     @SuppressWarnings("unused")
     private void saveDocumentToFileUnsafeMode(Document document, String pathfile) {
         TransformerFactory tfactory = TransformerFactory.newInstance();
         Transformer serializer;
         try {
             serializer = tfactory.newTransformer();
 
             serializer.transform(new DOMSource(document), new StreamResult(new File(pathfile)));
         } catch (TransformerException e) {
             System.err.println("Error al salvar el documento");
             e.printStackTrace();
             System.exit(-1);
         }
     }
 
     /**
      * <p>
      * Devuelve el <code>Document</code> correspondiente al
      * <code>resource</code> pasado como parÃ¡metro
      * </p>
      * 
      * @param resource
      *            El recurso que se desea obtener
      * @return El <code>Document</code> asociado al <code>resource</code>
      */
     protected Document getDocument(String resource) {
         Document doc = null;
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         dbf.setNamespaceAware(true);
         try {
        	 File initialFile = new File(resource);
             InputStream is = FileUtils.openInputStream(initialFile);
             doc = dbf.newDocumentBuilder().parse(is);
         } catch (ParserConfigurationException ex) {
             System.err.println("Error al parsear el documento");
             ex.printStackTrace();
             System.exit(-1);
         } catch (SAXException ex) {
             System.err.println("Error al parsear el documento");
             ex.printStackTrace();
             System.exit(-1);
         } catch (IOException ex) {
             System.err.println("Error al parsear el documento");
             ex.printStackTrace();
             System.exit(-1);
         } catch (IllegalArgumentException ex) {
             System.err.println("Error al parsear el documento");
             ex.printStackTrace();
             System.exit(-1);
         }
         return doc;
     }
 
     /**
      * <p>
      * Devuelve el contenido del documento XML
      * correspondiente al <code>resource</code> pasado como parÃ¡metro
      * </p> como un <code>String</code>
      * 
      * @param resource
      *            El recurso que se desea obtener
      * @return El contenido del documento XML como un <code>String</code>
      */
     protected String getDocumentAsString(String resource) {
         Document doc = getDocument(resource);
         TransformerFactory tfactory = TransformerFactory.newInstance();
         Transformer serializer;
         StringWriter stringWriter = new StringWriter();
         try {
             serializer = tfactory.newTransformer();
             serializer.transform(new DOMSource(doc), new StreamResult(stringWriter));
         } catch (TransformerException e) {
             System.err.println("Error al imprimir el documento");
             e.printStackTrace();
             System.exit(-1);
         }
 
         return stringWriter.toString();
     }
 
     /**
      * <p>
      * Devuelve el gestor de claves que se va a utilizar
      * </p>
      * 
      * @return El gestor de claves que se va a utilizar</p>
      */
     private IPKStoreManager getPKStoreManager() {
         IPKStoreManager storeManager = null;
         try {
             KeyStore ks = KeyStore.getInstance("PKCS12");
             File initialFile = new File(PKCS12_RESOURCE);
             InputStream targetStream = FileUtils.openInputStream(initialFile);
             ks.load(targetStream, PKCS12_PASSWORD.toCharArray());
             storeManager = new KSStore(ks, new PassStoreKS(PKCS12_PASSWORD));
         } catch (KeyStoreException ex) {
             System.err.println("No se puede generar KeyStore PKCS12");
             ex.printStackTrace();
             System.exit(-1);
         } catch (NoSuchAlgorithmException ex) {
             System.err.println("No se puede generar KeyStore PKCS12");
             ex.printStackTrace();
             System.exit(-1);
         } catch (CertificateException ex) {
             System.err.println("No se puede generar KeyStore PKCS12");
             ex.printStackTrace();
             System.exit(-1);
         } catch (IOException ex) {
             System.err.println("No se puede generar KeyStore PKCS12");
             ex.printStackTrace();
             System.exit(-1);
         }
         return storeManager;
     }
 
     /**
      * <p>
      * Recupera el primero de los certificados del almacÃ©n.
      * </p>
      * 
      * @param storeManager
      *            Interfaz de acceso al almacÃ©n
      * @return Primer certificado disponible en el almacÃ©n
      */
     private X509Certificate getFirstCertificate(
             final IPKStoreManager storeManager) {
         List<X509Certificate> certs = null;
         try {
             certs = storeManager.getSignCertificates();
         } catch (CertStoreException ex) {
             System.err.println("Fallo obteniendo listado de certificados");
             System.exit(-1);
         }
         if ((certs == null) || (certs.size() == 0)) {
             System.err.println("Lista de certificados vacÃ­a");
             System.exit(-1);
         }
 
         X509Certificate certificate = certs.get(0);
         return certificate;
     }
 
 }
