package co.edu.icesi.demobanco.modelo.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.Cuentas;

public class GestionarCuentasTest {
	
	private static final Logger log = LoggerFactory.getLogger(GestionarClientesTest.class);
	private static final String persistenceUnit = "demoBanco";
	private static final String cueNum = "5008-5305-0080";
	private static final Long cliId = 801234L;

	@Test
	public void crearCuentaTest() {
		log.info("inicio crearCuentaTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta la cuenta
		Cuentas cuenta = em.find(Cuentas.class, cueNum);
		assertNull("La cuenta ya existe: " , cuenta);
		
		//Instanciar la entidad de cuentas, ya que se va a persistir una cuenta
		cuenta = new Cuentas();
		cuenta.setCueNumero(cueNum);
		cuenta.setCueSaldo(new BigDecimal(100000));
		cuenta.setCueClave("1234");
		cuenta.setCueActiva("S");
		cuenta.setClientes(em.find(Clientes.class, cliId));
		
		em.getTransaction().begin();
		em.persist(cuenta);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
	
	@Test
	public void consultarCuentaTest() {
		log.info("inicio consultarCuentaTest");
		
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta la cuenta
		Cuentas cuenta = em.find(Cuentas.class, cueNum);
		//Si no existe
		assertNotNull("La cuenta NO existe: " , cuenta);
		//Si existe
		log.info("Número de cuenta: "+cuenta.getCueNumero());
		log.info("Id de cliente: "+cuenta.getClientes().getCliId());
		
		em.close();
		emf.close();
		
		
	}
	
	@Test
	public void modificarCuentaTest() {
		
		log.info("inicio modificarCuentaTest");
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta la cuenta
		Cuentas cuenta = em.find(Cuentas.class, cueNum);
		
		//Si no existe
		assertNotNull("La cuenta NO existe: " , cuenta);
		
		//Si existe
		cuenta.setCueClave("1478");
		
		em.getTransaction().begin();
		em.merge(cuenta);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	@Test
	public void eliminarCuentaTest() {
		
		log.info("inicio eliminarCuentaTest");
		//Instanciar la fabrica de Entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		EntityManager em = emf.createEntityManager();
				
		//Se consulta la cuenta
		Cuentas cuenta = em.find(Cuentas.class, cueNum);
		
		//Si no existe
		assertNotNull("La cuenta NO existe: " , cuenta);
		
		//Si existe		
		em.getTransaction().begin();
		em.remove(cuenta);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	

}
