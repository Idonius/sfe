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
  
  import org.w3c.dom.Document;

import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.XAdESSchemas;
import es.mityc.javasign.EnumFormatoFirma;
import es.mityc.javasign.xml.refs.InternObjectToSign;
import es.mityc.javasign.xml.refs.ObjectToSign;
  
  /**
   * <p>
   * Clase de ejemplo para la firma XAdES-BES enveloped de un documento. El
   * ejemplo firmarÃ¡ el recurso indicado en la constante
   * <code>RESOURCE_TO_SIGN</code> y el nombre del fichero resultante serÃ¡ el
   * indicado por la constante <code>SIGN_FILE_NAME</code>.
   * </p>
   * <p>
   * Para realizar la firma se utilizarÃ¡ el almacÃ©n PKCS#12 definido en la
   * constante <code>GenericXMLSignature.PKCS12_FILE</code>, al que se accederÃ¡
   * mediante la password definida en la constante
   * <code>GenericXMLSignature.PKCS12_PASSWORD</code>. El directorio donde quedarÃ¡
   * el archivo XML resultante serÃ¡ el indicado en al constante
   * <code>GenericXMLSignature.OUTPUT_DIRECTORY</code>
   * </p>
   * 
   */
  public class EnvelopedSignature extends GenericXMLSignature {
  
    

	/**
       * Recurso a firmar
       */
      private static String RESOURCE_TO_SIGN = "/examples/ExampleToSign.xml";
  
      /**
       * <p>
       * Fichero donde se desea guardar la firma
       * </p>
       */
      private static String SIGN_FILE_NAME = "Enveloped-Sign.xml";
  
      public EnvelopedSignature(String pathSign, String pass, String pathOut, String pathIn, String fileName) {
  		super(pathSign, pass, pathOut);
  		RESOURCE_TO_SIGN = pathIn;
  		SIGN_FILE_NAME = fileName;
  	}
  
//      public static void main(String[] args) {
//    	  EnvelopedSignature signature = new EnvelopedSignature("//home//jairo//Documentos//Firma//marcelo_xavier_arroyo_arguello.p12", "hEllo6482", "//home//jairo//Documentos//XMLFirmados//", "//home//jairo//Documentos//XMLGenerados//firma.xml", "xmlfirmado.xml");
//    	  					 signature.execute();
//    	    
//      }

      @Override
      protected DataToSign createDataToSign() {
          DataToSign dataToSign = new DataToSign();
          dataToSign.setXadesFormat(EnumFormatoFirma.XAdES_BES);
          dataToSign.setEsquema(XAdESSchemas.XAdES_132);
          dataToSign.setXMLEncoding("UTF-8");
          dataToSign.setEnveloped(true);
          Document docToSign = getDocument(RESOURCE_TO_SIGN);
          dataToSign.setDocument(docToSign);
          dataToSign.addObject(new ObjectToSign(new InternObjectToSign("comprobante"), "archivo firmado", null, "text/xml", null));
          dataToSign.setParentSignNode("comprobante");
          return dataToSign;
      }
  
      @Override
      protected String getSignatureFileName() {
          return SIGN_FILE_NAME;
      }
  }
