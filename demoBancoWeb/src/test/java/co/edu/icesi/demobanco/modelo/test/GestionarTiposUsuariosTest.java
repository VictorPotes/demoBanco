package co.edu.icesi.demobanco.modelo.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demobanco.modelo.TiposUsuarios;

public class GestionarTiposUsuariosTest {

	private static final Logger log = LoggerFactory.getLogger(GestionarClientesTest.class);
	private static final String persistenceUnit = "demoBanco";
	private static final long tusu_cod = 15;

	@Test
	public void crearTipoUsuarioTest() {
		log.info("inicio crearTipoUsuarioTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el tipoUsuario
		TiposUsuarios tipoUsuario = em.find(TiposUsuarios.class, tusu_cod);
		assertNull("El tipoUsuario ya existe: " , tipoUsuario);
		
		//Instanciar la entidad de TiposUsuarios, ya que se va a persistir un tipoUsuario
		tipoUsuario = new TiposUsuarios();
		tipoUsuario.setTusuCodigo(tusu_cod);
		tipoUsuario.setTusuNombre("ADMINISTRADOR");
		
		em.getTransaction().begin();
		em.persist(tipoUsuario);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
	
	@Test
	public void consultarTipoUsuarioTest() {
		log.info("inicio consultarTipoUsuarioTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el tipoUsuario
		TiposUsuarios tipoUsuario = em.find(TiposUsuarios.class, tusu_cod);
		//Si no existe
		assertNotNull("El TipoUsuario NO existe: " , tipoUsuario);
		//Si existe
		log.info("Código: "+tipoUsuario.getTusuCodigo());
		log.info("Nombre: "+tipoUsuario.getTusuNombre());
		
		em.close();
		emf.close();
		
		
	}
	
	@Test
	public void modificarTipoUsuarioTest() {
		
		log.info("inicio modificarTipoUsuarioTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el tipoDocumento
		TiposUsuarios tipoUsuario = em.find(TiposUsuarios.class, tusu_cod);
		//Si no existe
		assertNotNull("El TipoUsuario NO existe: " , tipoUsuario);
		//Si existe
		tipoUsuario.setTusuNombre("GERENTE");
		
		em.getTransaction().begin();
		em.merge(tipoUsuario);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	@Test
	public void eliminarTipoUsuarioTest() {
		
		log.info("inicio eliminarTipoUsuarioTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el tipoDocumento
		TiposUsuarios tipoUsuario = em.find(TiposUsuarios.class, tusu_cod);
		//Si no existe
		assertNotNull("El TipoUsuario NO existe: " , tipoUsuario);
		//Si existe		
		em.getTransaction().begin();
		em.remove(tipoUsuario);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}

}
