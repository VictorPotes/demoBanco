package co.edu.icesi.demobanco.logic.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.demobanco.logic.ITiposDocumentosLogic;
import co.edu.icesi.demobanco.modelo.TiposDocumentos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TiposDocumentosLogicTest {

	private static final Logger log = LoggerFactory.getLogger(TiposDocumentosLogicTest.class);
	
	private static final long tdocId = 15;
	
	@Autowired
	private ITiposDocumentosLogic tiposDocumentosLogic;
	
	/**
	 * Método de prueba que se encarga de verificar la creación de un TipoDocumento
	 */
	@Test
	public void aCrearTipoDocumentoTest() {

		log.info("inicio crearTipoDocumentoTest");

		try {
			
			// Instanciar la entidad de TiposDocumentos, ya que se va a persistir un tipoDocumento
			TiposDocumentos tipoDocumento = new TiposDocumentos();
			
			//Le doy los valores al TipoDocumento que estoy instanciando
			tipoDocumento.setTdocCodigo(tdocId);
			tipoDocumento.setTdocNombre("CARNET UNIVERSITARIO");
			
			//Persisto el tipoDocumento que instancié
			tiposDocumentosLogic.save(tipoDocumento);
			
			//Imprimo en consola
			log.info("Se creó el tipoDocumento con Id: " + tdocId + " y nombre: " + tipoDocumento.getTdocNombre());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Método de prueba que se encarga de verificar la consulta de un TipoDocumento
	 */
	@Test
	public void bConsultarTipoDocumentoTest() {
		
		log.info("inicio consultarTipoDocumentoTest");

		try {
			// Se consulta el tipoDocumento
			TiposDocumentos tipoDocumento = tiposDocumentosLogic.findById(tdocId);
			
			// Si existe
			if(tipoDocumento!=null) {
				log.info("Código: " + tipoDocumento.getTdocCodigo());
				log.info("Nombre: " + tipoDocumento.getTdocNombre());
			}
			else {
				log.info("El tipoDocumento con código: " + tdocId+" No existe");

			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Método de prueba que se encarga de verificar la modificación de un TipoDocumento
	 */
	@Test
	public void cModificarTipoDocumentoTest() {

		log.info("inicio modificarTipoDocumentoTest");

		try {
			// Se consulta el tipoDocumento
			TiposDocumentos tipoDocumento = tiposDocumentosLogic.findById(tdocId);
			
			// Si existe le modifico el nombre
			if(tipoDocumento!=null) {
				tipoDocumento.setTdocNombre("CARNET COLEGIAL");
			}
			
			//Actualizo el cambio hecho en el TipoDocumento en la base de datos
			tiposDocumentosLogic.update(tipoDocumento);
			
			//Realizo la consulta para verificar si en realidad si se modificó el TipoDocumento
			TiposDocumentos verificar = tiposDocumentosLogic.findById(tdocId);
			
			//Imprimo en consola
			log.info("Id: " + verificar.getTdocCodigo());
			log.info("Nombre: " + verificar.getTdocNombre());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Método de prueba que se encarga de verificar la eliminación de un TipoDocumento
	 */
	@Test
	public void dEliminarTipoDocumentoTest() {

		log.info("inicio eliminarTipoDocumentoTest");

		try {
			// Se consulta el tipoDocumento
			TiposDocumentos tipoDocumento = tiposDocumentosLogic.findById(tdocId);

			// Si existe se elimina
			tiposDocumentosLogic.delete(tipoDocumento);
			
			//Imprimo en consola
			log.info("Se eliminó el tipoDocumento con Id :" + tdocId+" y nombre: "+tipoDocumento.getTdocNombre());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

}
