package co.edu.icesi.demo.dao.test;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demobanco.dao.ITiposDocumentosDAO;
import co.edu.icesi.demobanco.modelo.TiposDocumentos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TiposDocumentosDAOTest {

	private static final Logger log = LoggerFactory.getLogger(TiposDocumentosDAOTest.class);

	private static final long tdocId = 15;

	@Autowired
	private ITiposDocumentosDAO tiposDocumentosDAO;

	/**
	 * Método de prueba que se encarga de verificar la creación de un TipoDocumento
	 */
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aCrearTipoDocumentoTest() {

		log.info("inicio crearTipoDocumentoTest");

		// Se consulta el tipoDocumento
		TiposDocumentos tipoDocumento = tiposDocumentosDAO.findById(tdocId);
		
		//Si ya existe
		assertNull("El tipoDocumento ya existe: ", tipoDocumento);
		
		// si no, instanciar la entidad de TiposDocumentos, ya que se va a persistir un tipoDocumento
		tipoDocumento = new TiposDocumentos();
		
		//Le doy los valores al TipoDocumento que estoy instanciando
		tipoDocumento.setTdocCodigo(tdocId);
		tipoDocumento.setTdocNombre("CARNET UNIVERSITARIO");
		
		//Persisto el tipoDocumento que instancié
		tiposDocumentosDAO.save(tipoDocumento);
		
		//Imprimo en consola
		log.info("Se creó el tipoDocumento con Id: " + tdocId + " y nombre: " + tipoDocumento.getTdocNombre());

	}

	/**
	 * Método de prueba que se encarga de verificar la consulta de un TipoDocumento
	 */
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void bConsultarTipoDocumentoTest() {
		
		log.info("inicio consultarTipoDocumentoTest");

		// Se consulta el tipoDocumento
		TiposDocumentos tipoDocumento = tiposDocumentosDAO.findById(tdocId);
		
		// Si no existe
		assertNotNull("El tipoDocumento NO existe: ", tipoDocumento);
		
		// Si existe
		log.info("Id: " + tipoDocumento.getTdocCodigo());
		log.info("Nombre: " + tipoDocumento.getTdocNombre());

	}

	/**
	 * Método de prueba que se encarga de verificar la modificación de un TipoDocumento
	 */
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cModificarTipoDocumentoTest() {

		log.info("inicio modificarTipoDocumentoTest");

		// Se consulta el tipoDocumento
		TiposDocumentos tipoDocumento = tiposDocumentosDAO.findById(tdocId);
	
		// Si no existe
		assertNotNull("El TipoDocumento NO existe: ", tipoDocumento);
		
		// Si existe le modifico el nombre
		tipoDocumento.setTdocNombre("CARNET COLEGIAL");
		
		//Actualizo el cambio hecho en el TipoDocumento en la base de datos
		tiposDocumentosDAO.update(tipoDocumento);
		
		//Realizo la consulta para verificar si en realidad si se modificó el TipoDocumento
		TiposDocumentos verificar = tiposDocumentosDAO.findById(tdocId);
		
		//Imprimo en consola
		log.info("Id: " + verificar.getTdocCodigo());
		log.info("Nombre: " + verificar.getTdocNombre());

	}

	/**
	 * Método de prueba que se encarga de verificar la eliminación de un TipoDocumento
	 */
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dEliminarTipoDocumentoTest() {

		log.info("inicio eliminarTipoDocumentoTest");

		// Se consulta el cliente
		TiposDocumentos tipoDocumento = tiposDocumentosDAO.findById(tdocId);

		// Si no existe
		assertNotNull("El tipoDocumento NO existe: ", tipoDocumento);

		// Si existe se elimina
		tiposDocumentosDAO.delete(tipoDocumento);
		
		//Imprimo en consola
		log.info("Se eliminó el tipoDocumento con Id :" + tdocId+" y nombre: "+tipoDocumento.getTdocNombre());

	}

}
