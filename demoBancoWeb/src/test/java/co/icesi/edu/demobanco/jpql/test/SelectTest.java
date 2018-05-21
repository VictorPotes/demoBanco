package co.icesi.edu.demobanco.jpql.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demobanco.dto.InfoCuentaDTO;
import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.Consignaciones;
import co.edu.icesi.demobanco.modelo.Cuentas;
import co.edu.icesi.demobanco.modelo.Retiros;

public class SelectTest {

	private static final Logger log = LoggerFactory.getLogger(SelectTest.class);
	private static final String persistenceUnit = "demoBanco";
	
	public EntityManagerFactory emf = null;
	public EntityManager em = null;
	
	// Por cada Test se ejecuta primero el Before y luego el After, ya que cada prueba es
	// una unidad independiente
	@Before
	public void inicio() {
		log.info("Inicio...");
		emf = Persistence.createEntityManagerFactory(persistenceUnit);
		em = emf.createEntityManager();
	}
	
	
	@Test
	public void selectCuentaMasDinero() {
		log.info("Select cuenta con más dinero...");
		
		String jpql = "select cu from Cuentas cu where cu.cueSaldo = (select MAX(cue.cueSaldo) from Cuentas cue)";
		Query query = em.createQuery(jpql);

		List<Cuentas> lstCuentas = query.getResultList();

		Cuentas cuenta = lstCuentas.get(0);
			log.info("Numero de cuenta: "+cuenta.getCueNumero());
			log.info("Saldo de cuenta: "+cuenta.getCueSaldo());
		
	}
	
	
	@Test
	public void selectCuentaMenosDinero() {
		log.info("Select cuenta con menos dinero...");
		
		String jpql = "select cu from Cuentas cu where cu.cueSaldo = (select MIN(cue.cueSaldo) from Cuentas cue)";
		Query query = em.createQuery(jpql);

		List<Cuentas> lstCuentas = query.getResultList();

		Cuentas cuenta = lstCuentas.get(0);
			log.info("Numero de cuenta: "+cuenta.getCueNumero());
			log.info("Saldo de cuenta: "+cuenta.getCueSaldo());
		
	}
	
	@Test
	public void selectSumaDeSaldos() {
		log.info("Select suma de saldos disponibles de las cuentas del banco...");
		
		String jpql = "select SUM(cue.cueSaldo) from Cuentas cue";
		Query query = em.createQuery(jpql);

		BigDecimal SUM = (BigDecimal)query.getSingleResult();
		log.info("Suma del saldo disponible de las cuentas: "+SUM);	
	}
	
//	@Test
//	public void selectSumaSaldosClienteDTO() {
//		log.info("Select la suma de las cuentas por cliente...");
//		
//		String jpql = "select cue.clientes.cliId, SUM(cue.cueSaldo) from Cuentas cue group by cue.clientes.cliId";
//		Query query = em.createQuery(jpql);
//		
//		Object[] objectResult = (Object[])query.getSingleResult();
//			
//		long cliId = (long) objectResult[0];
//		BigDecimal SUM = (BigDecimal) objectResult[1];
//		log.info("max: "+cliId);
//		log.info("min: "+SUM);
//	}
	
	@Test
	public void selectTotalDineroCliente() {
		log.info("Select Suma del saldo de todas las cuentas de un cliente...");

		long clieId = 101234;

		String jpql = "select SUM(cue.cueSaldo) from Cuentas cue where cue.clientes =" + clieId + "";
		Query query = em.createQuery(jpql);

		BigDecimal totalSaldoCliente = (BigDecimal) query.getSingleResult();

		log.info("Cliente id:" + clieId);
		log.info("Suma de las cuentas del cliente: " + totalSaldoCliente);

	}
	
	@Test
	public void selectAvgDeSaldos() {
		log.info("Select promedio de saldos disponibles de las cuentas del banco...");
		
		String jpql = "select AVG(cue.cueSaldo) from Cuentas cue";
		Query query = em.createQuery(jpql);

		Double AVG = (Double)query.getSingleResult();
		log.info("promedio del saldo disponible de las cuentas: "+AVG);	
	}
	
	@Test
	public void selectRetirosBetween() {
		log.info("Select retiros entre los valores $100.000 y $150.000...");
		
		String jpql = "select ret from Retiros ret where ret.retValor BETWEEN 100000 AND 150000";
		Query query = em.createQuery(jpql);

		List<Retiros> lstRetiros = query.getResultList();
		
		for (Retiros retiro : lstRetiros) {
			log.info("Fecha: "+retiro.getRetFecha());	
			log.info("Valor: "+retiro.getRetValor());	
		}
	}
	
	@Test
	public void selectClientesWhereLikeA() {
		log.info("Select clientes que contengan en su nombre al menos una 'a'...");
		
		String contain = "%a%";
		String jpql = "select cli from Clientes cli where cli.cliNombre like :elContain";
		Query query = em.createQuery(jpql);
		query.setParameter("elContain", contain);
		
		List<Clientes> lstClientes = query.getResultList();
		
		for (Clientes cliente : lstClientes) {
			log.info("Cliente id: "+cliente.getCliId());
			log.info("Nombre: "+cliente.getCliNombre());
		}
	}
	
	@Test
	public void selectClientesConVariasCuentas() {
		log.info("Select clientes con más de una cuenta...");
		
		String jpql = "select cli from Clientes cli where cli.cuentases.size>1";
		Query query = em.createQuery(jpql);
		
		List<Clientes> lstClientes = query.getResultList();
		
		for (Clientes cliente : lstClientes) {
			log.info("Cliente id: "+cliente.getCliId());
			log.info("Nombre: "+cliente.getCliNombre());
		}
		
	}
	
	@Test
	public void selectConsignacionesUnaCuenta() {
		log.info("select consignaciones de una cuenta...");

		String cuenta_cod = "4008-5305-0010";

		String jpql = "select cons from Consignaciones cons where cons.cuentas.cueNumero ='"+cuenta_cod+"'";
		Query query = em.createQuery(jpql);

		List<Consignaciones> lstConsignaciones = query.getResultList();
		
		log.info("Número de cuenta:" + cuenta_cod);
		for (Consignaciones consignacion : lstConsignaciones) {
			
			log.info("Consecutivo:" + consignacion.getId().getConCodigo());
			log.info("Descripción de consignación:" + consignacion.getConDescripcion());
			log.info("Valor de consgnación " + consignacion.getConValor());
		}
	}
	
	@Test
	public void selectConsignacionesCliente() {
		log.info("Select las consignaciones de las cuentas de un cliente...");

		long clieId = 101234;

		String jpql = "select cue.consignacioneses from Cuentas cue where cue.clientes =" + clieId + "";
		Query query = em.createQuery(jpql);

		List<Consignaciones> lstConsignaciones = query.getResultList();
		
		log.info("Cliente id:" + clieId);
		for (Consignaciones consignaciones : lstConsignaciones) {
			log.info("Número de cuenta :" + consignaciones.getId().getCueNumero());
			log.info("Valor de la consignación : " + consignaciones.getConValor());
		}
		


	}
	
	@Test
	public void selectRetirosMayores() {
		log.info("select retiros mayores a $100.000...");

		String jpql = "select ret from Retiros ret where ret.retValor>100000";
		Query query = em.createQuery(jpql);

		List<Retiros> lstRetiros = query.getResultList();
		
		for (Retiros retiro : lstRetiros) {
			
			log.info("Id = cuenta: "+ retiro.getId().getCueNumero()+" consecutivo: "+retiro.getId().getRetCodigo());
			log.info("Valor: " + retiro.getRetValor());
		}

	}
	
	
	@Test
	public void selectClientes() {
		log.info("Select clientes...");
		
		String jpql = "select cli from Clientes cli";
		Query query = em.createQuery(jpql);
		List<Clientes> lstClientes = query.getResultList();
		
		for (Clientes cliente : lstClientes) {
			log.info("Cliente id: "+cliente.getCliId());
			log.info("Nombre: "+cliente.getCliNombre());
		}
	}
	
	@Test
	public void selectClientesWhere() {
		log.info("Select clientes where...");
		
		String jpql = "select cli from Clientes cli where cli.cliNombre = 'John Smith'";
		Query query = em.createQuery(jpql);
		List<Clientes> lstClientes = query.getResultList();
		
		for (Clientes cliente : lstClientes) {
			log.info("Cliente id: "+cliente.getCliId());
			log.info("Nombre: "+cliente.getCliNombre());
		}
	}
	
	@Test
	public void selectClientesWhereParametro() {
		log.info("Select clientes where parametro...");
		
		String nombre = "John Smith";
		String jpql = "select cli from Clientes cli where cli.cliNombre = :elNombre";
		Query query = em.createQuery(jpql);
		query.setParameter("elNombre", nombre);
		
		List<Clientes> lstClientes = query.getResultList();
		
		for (Clientes cliente : lstClientes) {
			log.info("Cliente id: "+cliente.getCliId());
			log.info("Nombre: "+cliente.getCliNombre());
		}
	}
	
	@Test
	public void selectClientesWhereLike() {
		log.info("Select clientes where like...");
		
		String palabra = "%j%";
		String jpql = "select cli from Clientes cli where cli.cliNombre like :laPalabra";
		Query query = em.createQuery(jpql);
		query.setParameter("laPalabra", palabra);
		
		List<Clientes> lstClientes = query.getResultList();
		
		for (Clientes cliente : lstClientes) {
			log.info("Cliente id: "+cliente.getCliId());
			log.info("Nombre: "+cliente.getCliNombre());
		}
	}
	
	@Test
	public void selectClientesMatematicas() {
		log.info("Select cuentas  matematicas...");
		
		String jpql = "select MAX(cue.cueSaldo), MIN(cue.cueSaldo), AVG(cue.cueSaldo), SUM(cue.cueSaldo) from Cuentas cue";
		Query query = em.createQuery(jpql);
		
		Object[] objectResult = (Object[])query.getSingleResult();
		
		BigDecimal max = (BigDecimal) objectResult[0];
		BigDecimal min = (BigDecimal) objectResult[1];
		Double avg = (Double) objectResult[2];
		BigDecimal sum = (BigDecimal) objectResult[3];

		log.info("max: "+max);
		log.info("min: "+min);
		log.info("avg: "+avg);
		log.info("sum: "+sum);
	}
	
	@Test
	public void selectClientesMatematicasDTO() {
		log.info("Select cuentas  matematicas DTO...");
		
		String jpql = "select new co.icesi.edu.demobanco.dto.InfoCuentaDTO (MAX(cue.cueSaldo), MIN(cue.cueSaldo), AVG(cue.cueSaldo), SUM(cue.cueSaldo)) from Cuentas cue";
		Query query = em.createQuery(jpql);
		InfoCuentaDTO infoCuentaDTO = (InfoCuentaDTO)query.getSingleResult();		

		log.info("max: "+infoCuentaDTO.getMax());
		log.info("min: "+infoCuentaDTO.getMin());
		log.info("avg: "+infoCuentaDTO.getAvg());
		log.info("sum: "+infoCuentaDTO.getSum());
	}
	
	@After
	public void fin() {
		log.info("Fin...");
		em.close();
		emf.close();
	}

}
