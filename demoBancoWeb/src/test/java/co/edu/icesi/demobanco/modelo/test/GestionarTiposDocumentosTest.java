package co.edu.icesi.demobanco.modelo.test;

import static org.junit.Assert.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demobanco.modelo.TiposDocumentos;

public class GestionarTiposDocumentosTest {

	private static final Logger log = LoggerFactory.getLogger(GestionarClientesTest.class);
	private static final String persistenceUnit = "demoBanco";
	private static final long tdoc_cod = 15;

	@Test
	public void crearTipoDocumentoTest() {
		log.info("inicio crearTipoDocumentoTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el tipoDocumento
		TiposDocumentos tipoDocumento = em.find(TiposDocumentos.class, tdoc_cod);
		assertNull("El tipoDocumento ya existe: " , tipoDocumento);
		
		//Instanciar la entidad de TiposDocumentos, ya que se va a persistir un tipoDocumento
		tipoDocumento = new TiposDocumentos();
		tipoDocumento.setTdocCodigo(tdoc_cod);
		tipoDocumento.setTdocNombre("CARNET UNIVERSITARIO");
		
		em.getTransaction().begin();
		em.persist(tipoDocumento);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
	
	@Test
	public void consultarTipoDocumentoTest() {
		log.info("inicio consultarTipoDocumentoTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el tipoDocumento
		TiposDocumentos tipoDocumento = em.find(TiposDocumentos.class, tdoc_cod);
		//Si no existe
		assertNotNull("El TipoDocumento NO existe: " , tipoDocumento);
		//Si existe
		log.info("Código: "+tipoDocumento.getTdocCodigo());
		log.info("Nombre: "+tipoDocumento.getTdocNombre());
		
		em.close();
		emf.close();
		
		
	}
	
	@Test
	public void modificarTipoDocumentoTest() {
		
		log.info("inicio modificarTipoDocumentoTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el tipoDocumento
		TiposDocumentos tipoDocumento = em.find(TiposDocumentos.class, tdoc_cod);
		//Si no existe
		assertNotNull("El TipoDocumento NO existe: " , tipoDocumento);
		//Si existe
		tipoDocumento.setTdocNombre("CARNET COLEGIAL");
		
		em.getTransaction().begin();
		em.merge(tipoDocumento);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	@Test
	public void eliminarTipoDocumentoTest() {
		
		log.info("inicio eliminarTipoDocumentoTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el tipoDocumento
		TiposDocumentos tipoDocumento = em.find(TiposDocumentos.class, tdoc_cod);
		//Si no existe
		assertNotNull("El TipoDocumento NO existe: " , tipoDocumento);
		//Si existe
		
		em.getTransaction().begin();
		em.remove(tipoDocumento);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}

}
