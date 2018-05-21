package co.edu.icesi.demo.dao.test;

import static org.junit.Assert.*;
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
import co.edu.icesi.demobanco.dao.ITiposDocumentosDAO;
import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.TiposDocumentos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class ClientesDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(ClientesDAOTest.class);
	
	public final static Long cliId = 999L;
	public final static Long tdocId = 10L;

	
	@Autowired
	private IClientesDAO clientesDAO;
	
	@Autowired
	private ITiposDocumentosDAO TiposDocumentosDAO;
	
	/**
	 * Método de prueba que se encarga de verificar la creación de un Cliente
	 */
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aCrearCliente() {
		
		log.info("inicio crearClienteTest");
		
		//Se consulta el cliente
		Clientes cliente = clientesDAO.findById(cliId);
		
		//Si ya existe
		assertNull("El cliente ya existe: " , cliente);
		
		//Instanciar la entidad de clientes, ya que se va a persistir un cliente
		cliente = new Clientes();
		
		//Le doy los valores al Cliente que estoy instanciando
		cliente.setCliDireccion("Avd siempre viva 789");
		cliente.setCliId(cliId);
		cliente.setCliMail("homerojsimpson@gmail.com");
		cliente.setCliNombre("Homer J Simpson");
		cliente.setCliTelefono("123456789");
		
		//Se consulta el tipo de documento
		TiposDocumentos tiposDocumentos = TiposDocumentosDAO.findById(tdocId);
		
		//Si No existe
		assertNotNull("El tipo de documento NO existe", tiposDocumentos);
		
		//Le doy el valor del tipoDocumento del Cliente
		cliente.setTiposDocumentos(tiposDocumentos);
		
		//Persisto el Cliente que instancié
		clientesDAO.save(cliente);
		
		//Imprimo en consola
		log.info("Se creó el cliente con Id: "+cliId+" nombre: "+cliente.getCliNombre()+" y dirección: "+cliente.getCliDireccion());
		
	}
	
	/**
	 * Método de prueba que se encarga de verificar la consulta de un Cliente
	 */
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void bConsultarClienteTest() {
		
		log.info("inicio consultarClienteTest");

		//Se consulta el cliente
		Clientes cliente = clientesDAO.findById(cliId);
		
		//Si no existe
		assertNotNull("El cliente NO existe: " , cliente);
		
		//Si existe
		log.info("Id: "+cliente.getCliId());
		log.info("Nombre: "+cliente.getCliNombre());
		log.info("Dirección: "+cliente.getCliDireccion());
		log.info("Teléfono: " +cliente.getCliNombre());
		
	}
	
	/**
	 * Método de prueba que se encarga de verificar la modificación de un Cliente
	 */
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cModificarClienteTest() {
		
		log.info("inicio modificarClienteTest");

		//Se consulta el cliente
		Clientes cliente = clientesDAO.findById(cliId);
		
		//Si no existe
		assertNotNull("El cliente NO existe: " , cliente);
		
		//Si existe  modifico la dirección
		cliente.setCliDireccion("Carrera 123");
		
		//Actualizo el cambio hecho en el Cliente en la base de datos
		clientesDAO.update(cliente);
		
		//Realizo la consulta para verificar si en realidad si se modificó el Cliente
		Clientes verificar = clientesDAO.findById(cliId);
		
		//Imprimo en consola
		log.info("Id: "+verificar.getCliId());
		log.info("Nombre: "+verificar.getCliNombre());
		log.info("Dirección: "+verificar.getCliDireccion());
		
	}
	
	/**
	 * Método de prueba que se encarga de verificar la eliminación de un Cliente
	 */
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dEliminarClienteTest() {
		
		log.info("inicio eliminarClienteTest");
				
		//Se consulta el cliente
		Clientes cliente = clientesDAO.findById(cliId);
		
		//Si no existe
		assertNotNull("El cliente NO existe: " , cliente);
		
		//Si existe	se elimina	
		clientesDAO.delete(cliente);
		
		//Imprimo en consola
		log.info("Se eliminó el cliente con Id :"+ cliente.getCliId()+" y nombre: "+cliente.getCliNombre());
	}

}
