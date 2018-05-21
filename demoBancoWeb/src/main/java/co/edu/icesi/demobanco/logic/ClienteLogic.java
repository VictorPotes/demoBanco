package co.edu.icesi.demobanco.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demobanco.dao.IClientesDAO;
import co.edu.icesi.demobanco.dao.ITiposDocumentosDAO;
import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class ClienteLogic implements IClienteLogic {
	
	@Autowired
	private IClientesDAO clientesDAO;
	
	@Autowired
	private ITiposDocumentosDAO tiposDocumentosDAO;

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Clientes entity) throws Exception {
		
		//Se valida que se ingrese un cliente
		if(entity == null) {
			throw new Exception("Debe ingresar un cliente");
		}
		
		//Validamos que el id del cliente no sea null
		if(entity.getCliId() == 0) {
			throw new Exception("Debe ingresar el n�mero de identificaci�n del cliente");
		}
		
		//Verifico que el cliente no exista
		Clientes cliente = clientesDAO.findById(entity.getCliId());
		if(cliente != null) {
			throw new Exception("El cliente con Id "+entity.getCliId()+" ya existe");
		}
		
		//Validamos que el tipoDocumento no sea null y que su c�digo sea v�lido
		if(entity.getTiposDocumentos()==null || entity.getTiposDocumentos().getTdocCodigo()==0) {
			throw new Exception("Debe ingresar un tipo de documento");
		}
		
		//Validamos que exista el tipoDocumento que se le asigna al cliente
		TiposDocumentos tipoDocu = tiposDocumentosDAO.findById(entity.getTiposDocumentos().getTdocCodigo());	
		if(tipoDocu == null) {
			throw new Exception("El tipo de documento: "+entity.getTiposDocumentos().getTdocCodigo()+" no existe");
		}
		
		//Validamos que la direcci�n no venga nula ni vac�a
		if(entity.getCliDireccion()==null || entity.getCliDireccion().trim().equals("")) {
			throw new Exception("Debe ingresar una direcci�n");
		}
		
		//Validamos que la direcci�n no sea mayor de 50 caracteres
		if(entity.getCliDireccion().length()>50) {
			throw new Exception("Debe ingresar una direcci�n no mayor a 50 caracteres");
		}
		
		//Se valida que ingrese un tel�fono
		if(entity.getCliTelefono()==null || entity.getCliTelefono().trim().equals("")) {
			throw new Exception("Debe ingresar un tel�fono");
		}

		//Validamos que el telefono no sea mayor de 50 caracteres
		if(entity.getCliTelefono().length()>50) {
			throw new Exception("Debe ingresar un tel�fono");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getCliNombre()==null || entity.getCliNombre().trim().equals("")) {
			throw new Exception("Debe ingresar un nombre");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getCliNombre().length()>50) {
			throw new Exception("El Nombre debe ser no mayor a 50 caracteres");
		}
		
		//Validamos de que si se ingresa un Mail, no supere los 50 caracteres
		if(entity.getCliMail()!=null && entity.getCliMail().length() > 50) {
			throw new Exception("El Mail no debe superar los 50 caracteres");
		}
		
		clientesDAO.save(entity);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Clientes entity) throws Exception {
		
		//Se valida que se ingrese un cliente
		if(entity == null) {
			throw new Exception("Debe ingresar un cliente");
		}
		
		//Validamos que el id del cliente no sea null
		if(entity.getCliId() == 0) {
			throw new Exception("Debe ingresar el n�mero de identificaci�n del cliente");
		}
		
		//Verifico que el clente no exista
		Clientes cliente = clientesDAO.findById(entity.getCliId());
		if(cliente == null) {
			throw new Exception("El cliente con Id "+entity.getCliId()+" NO existe");
		}
		
		//Validamos que el tipoDocumento no sea null y que su c�digo sea v�lido
		if(entity.getTiposDocumentos()==null || entity.getTiposDocumentos().getTdocCodigo()==0) {
			throw new Exception("Debe ingresar un tipo de documento");
		}
		
		//Validamos que exista el tipoDocumento que se le asigna al cliente
		TiposDocumentos tipoDocu = tiposDocumentosDAO.findById(entity.getTiposDocumentos().getTdocCodigo());	
		if(tipoDocu == null) {
			throw new Exception("El tipo de documento: "+entity.getTiposDocumentos().getTdocCodigo()+" no existe");
		}
		
		//Validamos que la direcci�n no venga nula ni vac�a
		if(entity.getCliDireccion()==null || entity.getCliDireccion().trim().equals("")) {
			throw new Exception("Debe ingresar una direcci�n");
		}
		
		//Validamos que la direcci�n no sea mayor de 50 caracteres
		if(entity.getCliDireccion().length()>50) {
			throw new Exception("Debe ingresar una direcci�n menor de 50 caracteres");
		}
		
		//Se valida que ingrese un tel�fono
		if(entity.getCliTelefono()==null || entity.getCliTelefono().trim().equals("")) {
			throw new Exception("Debe ingresar un tel�fono");
		}

		//Validamos que el telefono no sea mayor de 50 caracteres
		if(entity.getCliTelefono().length()>50) {
			throw new Exception("Debe ingresar un tel�fono");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getCliNombre()==null || entity.getCliNombre().trim().equals("")) {
			throw new Exception("Debe ingresar un nombre");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getCliNombre().length()>50) {
			throw new Exception("El Nombre debe tener menos de 50 caracteres");
		}

		//Validamos de que si se ingresa un Mail, no supere los 50 caracteres
		if(entity.getCliMail()!=null && entity.getCliMail().length() > 50) {
			throw new Exception("El Mail debe tener menos de 50 caracteres");
		}
		
		clientesDAO.update(entity);
		
	}

	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Clientes entity) throws Exception {
		
		//Se valida que se ingrese un cliente
		if(entity == null) {
			throw new Exception("Debe ingresar un cliente");
		}
		
		//Verifico que el cliente exista
		Clientes cliente = clientesDAO.findById(entity.getCliId());
		if(cliente == null) {
			throw new Exception("El cliente con Id "+entity.getCliId()+" no existe");
		}
		
		clientesDAO.delete(cliente);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Clientes findById(long cliId) throws Exception {
		
		//Validamos que el id del cliente no sea null
		if(cliId == 0) {
			throw new Exception("Debe ingresar el n�mero de identificaci�n del cliente");
		}
		
		return clientesDAO.findById(cliId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Clientes> findAll() throws Exception {
		
		List<Clientes> lista = clientesDAO.findAll();
		
		//Validamos que la lista no est� vac�a
		if(lista.size()==0) {
			throw new Exception("No hay clientes para mostrar");
		}
		
		return lista;
	}



}
