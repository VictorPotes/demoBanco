package co.edu.icesi.demo.dao.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demobanco.dao.IClientesDAO;
import co.edu.icesi.demobanco.dao.ICuentasDAO;
import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.Cuentas;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class CuentasDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(CuentasDAOTest.class);
	
	public final static String cueNumero = "4008-5305-0090";
	public final static Long cliId = 801234L;

	
	@Autowired
	private IClientesDAO clientesDAO;
	
	@Autowired
	private ICuentasDAO cuentasDAO;

	/**
	 * Método de prueba que se encarga de verificar la creación de una cuenta
	 */
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aCrearCuenta() {
		
		log.info("inicio crearCuentaTest");
		
		//Se consulta la cuenta
		Cuentas cuenta = cuentasDAO.findById(cueNumero);
		
		//Si ya existe
		assertNull("La cuenta ya existe: " , cuenta);
		
		//Instanciar la entidad de cuentas, ya que se va a persistir una cuenta
		cuenta = new Cuentas();
		
		//Le doy los valores a la cuenta que estoy instanciando
		cuenta.setCueActiva("S");
		cuenta.setCueClave("1234");
		cuenta.setCueNumero(cueNumero);
		cuenta.setCueSaldo(new BigDecimal(50000));
		
		//Se consulta el cliente
		Clientes cliente = clientesDAO.findById(cliId);
		
		//Si No existe
		assertNotNull("El cliente NO existe", cliente);
		
		//Le doy el valor del cliente de la cuenta
		cuenta.setClientes(cliente);
		
		//Persisto el Cliente que instancié
		cuentasDAO.save(cuenta);
		
		//Imprimo en consola
		log.info("Se creó la cuenta con número de cuenta: "+cueNumero+" estado de activación: "+cuenta.getCueActiva()+" y saldo: "+cuenta.getCueSaldo());
		
	}
	
	/**
	 * Método de prueba que se encarga de verificar la consulta de una Cuenta
	 */
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void bConsultarCuentaTest() {
		
		log.info("inicio consultarCuentaTest");

		//Se consulta la cuenta
		Cuentas cuenta = cuentasDAO.findById(cueNumero);
		
		//Si no existe
		assertNotNull("La cuenta NO existe: " , cuenta);
		
		//Si existe
		log.info("Número de cuenta: "+cuenta.getCueNumero());
		log.info("Saldo de cuenta: "+cuenta.getCueSaldo());
		log.info("Estado de activación: "+cuenta.getCueActiva());
		log.info("Id de cliente: " +cuenta.getClientes().getCliId());
		
	}
	
	/**
	 * Método de prueba que se encarga de verificar la modificación de una cuenta
	 */
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cModificarCuentaTest() {
		
		log.info("inicio modificarCuentaTest");

		//Se consulta la cuenta
		Cuentas cuenta = cuentasDAO.findById(cueNumero);
		
		//Si no existe
		assertNotNull("La cuenta NO existe: " , cuenta);
		
		//Si existe  modifico el estado
		cuenta.setCueActiva("N");
		
		//Actualizo el cambio en la cuenta en la base de datos
		cuentasDAO.update(cuenta);
		
		//Realizo la consulta para verificar si en realidad si se modificó la cuenta
		Cuentas verificar = cuentasDAO.findById(cueNumero);
		
		//Imprimo en consola
		log.info("Número de cuenta: "+verificar.getCueNumero());
		log.info("Estado de activación: "+verificar.getCueActiva());
		log.info("Saldo de cuenta: "+verificar.getCueSaldo());
		
	}
	
	/**
	 * Método de prueba que se encarga de verificar la eliminación de una cuenta
	 */
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dEliminarCuentaTest() {
		
		log.info("inicio eliminarCuentaTest");
				
		//Se consulta el cliente
		Cuentas cuenta = cuentasDAO.findById(cueNumero);
		
		//Si no existe
		assertNotNull("La cuenta NO existe: " , cuenta);
		
		//Si existe	se elimina	
		cuentasDAO.delete(cuenta);
		
		//Imprimo en consola
		log.info("Se eliminó la cuenta con número :"+ cuenta.getCueNumero()+", saldo: "+cuenta.getCueSaldo()+" y estado de activación: "+cuenta.getCueActiva());
	}

}
