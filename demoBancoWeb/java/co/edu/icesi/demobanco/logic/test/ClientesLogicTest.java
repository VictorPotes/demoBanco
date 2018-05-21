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


import co.edu.icesi.demobanco.logic.IClienteLogic;
import co.edu.icesi.demobanco.logic.ITiposDocumentosLogic;
import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.TiposDocumentos;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class ClientesLogicTest {
	
	private static final Logger log = LoggerFactory.getLogger(ClientesLogicTest.class);
	
	public final static Long cliId = 111111L;
	public final static Long tdocId = 10L;
	
	@Autowired
	private IClienteLogic clienteLogic;
	
	@Autowired
	private ITiposDocumentosLogic tiposDocumentosLogic;
	

	/**
	 * Método de prueba que se encarga de verificar la creación de un Cliente
	 */
	@Test
	public void aCrearCliente() {
		
		log.info("inicio crearClienteTest");
		
		try {
		//Instanciar la entidad de clientes, ya que se va a persistir un cliente
		Clientes cliente = new Clientes();
		
		//Le doy los valores al Cliente que estoy instanciando
		cliente.setCliDireccion("Avd siempre viva 789");
		cliente.setCliId(cliId);
		cliente.setCliMail("homerojsimpson@gmail.com");
		cliente.setCliNombre("Homer J Simpson");
		cliente.setCliTelefono("123456789");
		
		//Se consulta el tipo de documento
		TiposDocumentos tiposDocumentos = tiposDocumentosLogic.findById(tdocId);
		cliente.setTiposDocumentos(tiposDocumentos);
		clienteLogic.save(cliente);
		
		log.info("Se creó el cliente con identificación: "+cliente.getCliId()+" y nombre "+cliente.getCliNombre());
		
		} catch(Exception e) {
			log.error(e.getMessage());
		}

	}
	
	/**
	 * Método de prueba que se encarga de verificar la consulta de un Cliente
	 */
	@Test
	public void bConsultarClienteTest() {
		
		log.info("inicio consultarClienteTest");

		try {
			//Se consulta el cliente
			Clientes cliente = clienteLogic.findById(cliId);
			
			//Si existe
			if(cliente!= null) {
				log.info("Id: "+cliente.getCliId());
				log.info("Nombre: "+cliente.getCliNombre());
				log.info("Dirección: "+cliente.getCliDireccion());
				log.info("Teléfono: " +cliente.getCliNombre());
				log.info("Mail: " +cliente.getCliMail());
			}
			else {
				log.info("El cliente con Id: "+cliId+" no exite");

			}
			
		} catch (Exception e) {
			log.error(e.getMessage());

		}
		
	}
	
	/**
	 * Método de prueba que se encarga de verificar la modificación de un Cliente
	 */
	@Test
	public void cModificarClienteTest() {
		
		log.info("inicio modificarClienteTest");

		try {
			//Se consulta el cliente
			Clientes cliente = clienteLogic.findById(cliId);
			
			//Si existe  modifico la dirección
			if(cliente!=null) {
				cliente.setCliDireccion("Carrera 123");
			}
			
			//Actualizo el cambio hecho en el Cliente en la base de datos
			clienteLogic.update(cliente);
			
			//Realizo la consulta para verificar si en realidad si se modificó el Cliente
			Clientes verificar = clienteLogic.findById(cliId);
			
			//Imprimo en consola
			log.info("Id: "+verificar.getCliId());
			log.info("Nombre: "+verificar.getCliNombre());
			log.info("Dirección: "+verificar.getCliDireccion());
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	/**
	 * Método de prueba que se encarga de verificar la eliminación de un Cliente
	 */
	@Test
	public void dEliminarClienteTest() {
		
		log.info("inicio eliminarClienteTest");
				
		try {
			//Se consulta el cliente
			Clientes cliente = clienteLogic.findById(cliId);
			
			//Si existe	se elimina	
			clienteLogic.delete(cliente);
			
			//Imprimo en consola
			log.info("Se eliminó el cliente con Id: "+ cliente.getCliId()+" y nombre: "+cliente.getCliNombre());
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
}
