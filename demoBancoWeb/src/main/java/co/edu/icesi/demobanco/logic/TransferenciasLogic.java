package co.edu.icesi.demobanco.logic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demobanco.dao.IClientesDAO;
import co.edu.icesi.demobanco.dao.ICuentasDAO;
import co.edu.icesi.demobanco.dao.ITransferenciasDAO;
import co.edu.icesi.demobanco.dao.IUsuariosDAO;
import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.Cuentas;
import co.edu.icesi.demobanco.modelo.Transferencias;
import co.edu.icesi.demobanco.modelo.TransferenciasId;
import co.edu.icesi.demobanco.modelo.Usuarios;
@Service
@Scope("singleton")
public class TransferenciasLogic implements ITransferenciasLogic{
	
	@Autowired
	private ITransferenciasDAO transferenciasDAO;
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	@Autowired
	private IUsuariosDAO usuariosDAO;
	
	@Autowired
	private IClientesDAO clientesDAO;

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Transferencias entity) throws Exception {
		//Se valida que se ingrese una consignación
		if(entity == null) {
			throw new Exception("Debe ingresar una transferencia");
		}
		
		//Validamos que el id de la consignación no sea null
		if(entity.getId() == null) {
			throw new Exception("Debe ingresar el id de la transferencia ");
		}
		
		//Verifico que el cliente no exista
		Transferencias transferencia = transferenciasDAO.findById(entity.getId());
		if(transferencia != null) {
			throw new Exception("La transferencia con Id "+entity.getId()+" ya existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getCuentasByCueNumeroOrigen() == null) {
			throw new Exception("Debe ingresar una cuenta origen para transferir");
		}
		
		//Validamos que exista la cuenta a la que se le asigna la consignación
		Cuentas cuentaO = cuentasDAO.findById(entity.getCuentasByCueNumeroOrigen().getCueNumero());	
		if(cuentaO == null) {
			throw new Exception("La cuenta de origen con número: "+entity.getCuentasByCueNumeroOrigen().getCueNumero()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getCuentasByCueNumeroDestino() == null) {
			throw new Exception("Debe ingresar una cuenta destino para transferir");
		}
		
		//Validamos que exista la cuenta a la que se le asigna la consignación
		Cuentas cuentaD = cuentasDAO.findById(entity.getCuentasByCueNumeroDestino().getCueNumero());	
		if(cuentaD == null) {
			throw new Exception("La cuenta de origen con número: "+entity.getCuentasByCueNumeroDestino().getCueNumero()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getClientes() == null) {
			throw new Exception("Debe ingresar una cuenta para retirar");
		}
		
		//Validamos que exista la cuenta a la que se le asigna la consignación
		Clientes cliente = clientesDAO.findById(entity.getClientes().getCliId());	
		if(cliente == null) {
			throw new Exception("El cliente con número: "+entity.getClientes().getCliId()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getUsuarios() != null){
			//Validamos que exista la cuenta a la que se le asigna la consignación
			Usuarios usuario = usuariosDAO.findById(entity.getUsuarios().getUsuCedula());	
			if(usuario == null) {
				throw new Exception("El usuario con cédula: "+entity.getUsuarios().getUsuCedula()+" no existe");
			}
		}
		
		//Validamos que el valor no sea null
		if(entity.getTranValor() == null) {
			throw new Exception("Debe ingresar el valor de la transferencia");
		}
		
		//Validamos que el valor no sea 0
		if(entity.getTranValor().doubleValue() == 0) {
			throw new Exception("Debe ingresar un valor superior a 0");
		}
		
		//Validamos que la fecha no sea null
		if(entity.getTranFecha() == null) {
			throw new Exception("Debe ingresar la fecha de la transferencia");
		}
		
		//Validamos que la fecha no sea pasada
		if(entity.getTranFecha().compareTo(new Date())>=0) {
			throw new Exception("La transferencia debe hacerse en la fecha actual o una posterior a la actual");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getTranDescripcion()==null || entity.getTranDescripcion().trim().equals("")) {
			throw new Exception("Debe ingresar una descripción");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getTranDescripcion().length()>50) {
			throw new Exception("La descripción debe ser no mayor a 50 caracteres");
		}
		
		transferenciasDAO.save(entity);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Transferencias entity) throws Exception {
		//Se valida que se ingrese una consignación
		if(entity == null) {
			throw new Exception("Debe ingresar una transferencia");
		}
		
		//Validamos que el id de la consignación no sea null
		if(entity.getId() == null) {
			throw new Exception("Debe ingresar el id de la transferencia ");
		}
		
		//Verifico que el cliente no exista
		Transferencias transferencia = transferenciasDAO.findById(entity.getId());
		if(transferencia == null) {
			throw new Exception("La transferencia con Id "+entity.getId()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getCuentasByCueNumeroOrigen() == null) {
			throw new Exception("Debe ingresar una cuenta origen para transferir");
		}
		
		//Validamos que exista la cuenta a la que se le asigna la consignación
		Cuentas cuentaO = cuentasDAO.findById(entity.getCuentasByCueNumeroOrigen().getCueNumero());	
		if(cuentaO == null) {
			throw new Exception("La cuenta de origen con número: "+entity.getCuentasByCueNumeroOrigen().getCueNumero()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getCuentasByCueNumeroDestino() == null) {
			throw new Exception("Debe ingresar una cuenta destino para transferir");
		}
		
		//Validamos que exista la cuenta a la que se le asigna la consignación
		Cuentas cuentaD = cuentasDAO.findById(entity.getCuentasByCueNumeroDestino().getCueNumero());	
		if(cuentaD == null) {
			throw new Exception("La cuenta de origen con número: "+entity.getCuentasByCueNumeroDestino().getCueNumero()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getClientes() == null) {
			throw new Exception("Debe ingresar una cuenta para retirar");
		}
		
		//Validamos que exista la cuenta a la que se le asigna la consignación
		Clientes cliente = clientesDAO.findById(entity.getClientes().getCliId());	
		if(cliente == null) {
			throw new Exception("El cliente con número: "+entity.getClientes().getCliId()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getUsuarios() != null){
			//Validamos que exista la cuenta a la que se le asigna la consignación
			Usuarios usuario = usuariosDAO.findById(entity.getUsuarios().getUsuCedula());	
			if(usuario == null) {
				throw new Exception("El usuario con cédula: "+entity.getUsuarios().getUsuCedula()+" no existe");
			}
		}
		
		//Validamos que el valor no sea null
		if(entity.getTranValor() == null) {
			throw new Exception("Debe ingresar el valor de la transferencia");
		}
		
		//Validamos que el valor no sea 0
		if(entity.getTranValor().doubleValue() == 0) {
			throw new Exception("Debe ingresar un valor superior a 0");
		}
		
		//Validamos que la fecha no sea null
		if(entity.getTranFecha() == null) {
			throw new Exception("Debe ingresar la fecha de la transferencia");
		}
		
		//Validamos que la fecha no sea pasada
		if(entity.getTranFecha().compareTo(new Date())>=0) {
			throw new Exception("La transferencia debe hacerse en la fecha actual o una posterior a la actual");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getTranDescripcion()==null || entity.getTranDescripcion().trim().equals("")) {
			throw new Exception("Debe ingresar una descripción");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getTranDescripcion().length()>50) {
			throw new Exception("La descripción debe ser no mayor a 50 caracteres");
		}
		
		transferenciasDAO.update(entity);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Transferencias entity) throws Exception {
		//Se valida que se ingrese una consignación
				if(entity == null) {
					throw new Exception("Debe ingresar una transferencia");
				}
				
				//Verifico que la consignación no exista
				Transferencias transferencia = transferenciasDAO.findById(entity.getId());
				if(transferencia == null) {
					throw new Exception("La transferencia con Id "+entity.getId()+" no existe");
				}
				
				transferenciasDAO.delete(entity);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Transferencias findById(TransferenciasId id) throws Exception {
		//Validamos que el id de la consignación no sea null
		if(id == null) {
			throw new Exception("Debe ingresar el id de la transferencia");
		}
		return transferenciasDAO.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Transferencias> findAll() throws Exception {
		List<Transferencias> lista = transferenciasDAO.findAll();
		
		//Validamos que la lista no esté vacía
		if(lista.size()==0) {
			throw new Exception("No hay transferencias para mostrar");
		}
		
		return lista;
	}

}
