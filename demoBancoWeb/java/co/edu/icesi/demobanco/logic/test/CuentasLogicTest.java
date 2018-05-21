package co.edu.icesi.demobanco.logic.test;


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

import co.edu.icesi.demobanco.logic.IClienteLogic;
import co.edu.icesi.demobanco.logic.ICuentasLogic;
import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.Cuentas;
import co.icesi.edu.demobanco.dto.InfoCuentaPublicaDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class CuentasLogicTest {
	
	private static final Logger log = LoggerFactory.getLogger(CuentasLogicTest.class);
	
	public final static String cueNumero = "4008-5305-0090";
	public final static Long cliId = 801234L;
	
	@Autowired
	private IClienteLogic clientesLogic;
	
	@Autowired
	private ICuentasLogic cuentasLogic;

	/**
	 * Método de prueba que se encarga de verificar la creación de una cuenta
	 */
	@Test
	public void aCrearCuenta() {
		
		log.info("inicio crearCuentaTest");
		

		try {
			
			//Instanciar la entidad de cuentas, ya que se va a persistir una cuenta
			Cuentas cuenta = new Cuentas();
			
			//Le doy los valores a la cuenta que estoy instanciando
			cuenta.setCueActiva("S");
			cuenta.setCueClave("1234");
			cuenta.setCueNumero(cueNumero);
			cuenta.setCueSaldo(new BigDecimal(50000));
			
			//Se consulta el cliente
			Clientes cliente = clientesLogic.findById(cliId);
			
			//Le doy el valor del cliente de la cuenta
			cuenta.setClientes(cliente);
			
			//Persisto el Cliente que instancié
			cuentasLogic.save(cuenta);
			
			//Imprimo en consola
			log.info("Se creó la cuenta con número de cuenta: "+cueNumero+" estado de activación: "+cuenta.getCueActiva()+" y saldo: "+cuenta.getCueSaldo());
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Método de prueba que se encarga de verificar la consulta de una Cuenta
	 */
	@Test
	public void bConsultarCuentaTest() {
		
		log.info("inicio consultarCuentaTest");

	
		try {
			//Se consulta la cuenta
			Cuentas cuenta = cuentasLogic.findById(cueNumero);
			//Si existe
			if(cuenta!=null) {
				InfoCuentaPublicaDTO mostrar = new InfoCuentaPublicaDTO(cuenta.getClientes().getCliId(),
				cuenta.getCueNumero(), cuenta.getCueSaldo(), cuenta.getCueActiva());

				//TODO: Me trae el cliente con información Nula, de acuerdo a lo que investigue no se trae el cliente porque se cierra la transacción, el error es el siguiente.
				//TODO: could not initialize proxy - no Session
				
				log.info(mostrar.toString());	
			}
			else {
				log.info("No se obtuvieron datos porque la cuenta "+cueNumero+" no existe");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Método de prueba que se encarga de verificar la modificación de una cuenta
	 */
	@Test
	public void cModificarCuentaTest() {
		
		log.info("inicio modificarCuentaTest");

		try {
			//Se consulta la cuenta
			Cuentas cuenta = cuentasLogic.findById(cueNumero);
			
			//Si existe  modifico el estado
			if(cuenta!=null) {
				cuenta.setCueActiva("N");
			}
			
			//Actualizo el cambio en la cuenta en la base de datos
			cuentasLogic.update(cuenta);
			
			//Realizo la consulta para verificar si en realidad si se modificó la cuenta
			Cuentas verificar = cuentasLogic.findById(cueNumero);
			
			//Imprimo en consola
			log.info("Número de cuenta: "+verificar.getCueNumero());
			log.info("Estado de activación: "+verificar.getCueActiva());
			log.info("Saldo de cuenta: "+verificar.getCueSaldo());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Método de prueba que se encarga de verificar la eliminación de una cuenta
	 */
	@Test
	public void dEliminarCuentaTest() {
		
		log.info("inicio eliminarCuentaTest");
				
		try {
			//Se consulta la cuenta
			Cuentas cuenta = cuentasLogic.findById(cueNumero);
			
			//Si existe	se elimina	
			cuentasLogic.delete(cuenta);
			
			//Imprimo en consola
			log.info("Se eliminó la cuenta con número :"+ cuenta.getCueNumero()+", saldo: "+cuenta.getCueSaldo()+" y estado de activación: "+cuenta.getCueActiva());
		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
