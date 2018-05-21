package co.edu.icesi.demobanco.modelo.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.TiposDocumentos;

public class GestionarClientesTest {
	
	private static final Logger log = LoggerFactory.getLogger(GestionarClientesTest.class);
	private static final String persistenceUnit = "demoBanco";
	private static final Long cliId = 99999L;
	private static final Long tidoId = 10L;
	
	
	@Test
	public void crearClienteTest() {
		log.info("inicio crearClienteTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el cliente
		Clientes cliente = em.find(Clientes.class, cliId);
		assertNull("El cliente ya existe: " , cliente);
		
		//Instanciar la entidad de clientes, ya que se va a persistir un cliente
		cliente = new Clientes();
		cliente.setCliDireccion("Avd siempre viva 123");
		cliente.setCliId(cliId);
		cliente.setCliMail("homerojsimpson@gmail.com");
		cliente.setCliNombre("Homer J Simpson");
		cliente.setCliTelefono("123456789");
		
		
		//Se consulta el tipo de documento
		TiposDocumentos tiposDocumentos = em.find(TiposDocumentos.class, tidoId);
		assertNotNull("El tipo de documento NO existe", tiposDocumentos);
		cliente.setTiposDocumentos(tiposDocumentos);
		
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
	
	@Test
	public void consultarClienteTest() {
		log.info("inicio consultarClienteTest");
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el cliente
		Clientes cliente = em.find(Clientes.class, cliId);
		
		//Si no existe
		assertNotNull("El cliente NO existe: " , cliente);
		
		//Si existe
		log.info("Id: "+cliente.getCliId());
		log.info("Nombre: "+cliente.getCliNombre());
		
		em.close();
		emf.close();
	}
	
	@Test
	public void modificarClienteTest() {
		
		log.info("inicio modificarClienteTest");
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el cliente
		Clientes cliente = em.find(Clientes.class, cliId);
		
		//Si no existe
		assertNotNull("El cliente NO existe: " , cliente);
		
		//Si existe
		cliente.setCliDireccion("Carrera 123");
		
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	@Test
	public void eliminarClienteTest() {
		
		log.info("inicio eliminarClienteTest");
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta el cliente
		Clientes cliente = em.find(Clientes.class, cliId);
		
		//Si no existe
		assertNotNull("El cliente NO existe: " , cliente);
		
		//Si existe		
		em.getTransaction().begin();
		em.remove(cliente);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}

}
