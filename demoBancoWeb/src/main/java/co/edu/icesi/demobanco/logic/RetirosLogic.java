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
import co.edu.icesi.demobanco.dao.IRetirosDAO;
import co.edu.icesi.demobanco.dao.IUsuariosDAO;
import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.Cuentas;
import co.edu.icesi.demobanco.modelo.Retiros;
import co.edu.icesi.demobanco.modelo.RetirosId;
import co.edu.icesi.demobanco.modelo.Usuarios;
@Service
@Scope("singleton")
public class RetirosLogic implements IRetirosLogic {
	
	@Autowired
	private IRetirosDAO retirosDAO;
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	@Autowired
	private IUsuariosDAO usuariosDAO;
	
	@Autowired
	private IClientesDAO clientesDAO;

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Retiros entity) throws Exception {
		//Se valida que se ingrese una consignación
		if(entity == null) {
			throw new Exception("Debe ingresar un retiro");
		}
		
		//Validamos que el id de la consignación no sea null
		if(entity.getId() == null) {
			throw new Exception("Debe ingresar el id del retiro ");
		}
		
		//Verifico que el cliente no exista
		Retiros retiro = retirosDAO.findById(entity.getId());
		if(retiro != null) {
			throw new Exception("El retiro con Id "+entity.getId()+" ya existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getCuentas() == null) {
			throw new Exception("Debe ingresar una cuenta para retirar");
		}
		
		//Validamos que exista la cuenta a la que se le asigna la consignación
		Cuentas cuenta = cuentasDAO.findById(entity.getCuentas().getCueNumero());	
		if(cuenta == null) {
			throw new Exception("La cuenta con número: "+entity.getCuentas().getCueNumero()+" no existe");
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
		if(entity.getRetValor() == null) {
			throw new Exception("Debe ingresar el valor del retiro");
		}
		
		//Validamos que el valor no sea 0
		if(entity.getRetValor().doubleValue() == 0) {
			throw new Exception("Debe ingresar un valor superior a 0");
		}
		
		//Validamos que la fecha no sea null
		if(entity.getRetFecha() == null) {
			throw new Exception("Debe ingresar la fecha del retiro");
		}
		
		//Validamos que la fecha no sea pasada
		if(entity.getRetFecha().compareTo(new Date())>=0) {
			throw new Exception("El retiro debe hacerse en la fecha actual o una posterior a la actual");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getRetDescripcion()==null || entity.getRetDescripcion().trim().equals("")) {
			throw new Exception("Debe ingresar una descripción");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getRetDescripcion().length()>50) {
			throw new Exception("La descripción debe ser no mayor a 50 caracteres");
		}
		
		retirosDAO.save(entity);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Retiros entity) throws Exception {
		//Se valida que se ingrese una consignación
		if(entity == null) {
			throw new Exception("Debe ingresar un retiro");
		}
		
		//Validamos que el id de la consignación no sea null
		if(entity.getId() == null) {
			throw new Exception("Debe ingresar el id del retiro ");
		}
		
		//Verifico que el cliente no exista
		Retiros retiro = retirosDAO.findById(entity.getId());
		if(retiro == null) {
			throw new Exception("El retiro con Id "+entity.getId()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getCuentas() == null) {
			throw new Exception("Debe ingresar una cuenta para retirar");
		}
		
		//Validamos que exista la cuenta a la que se le asigna la consignación
		Cuentas cuenta = cuentasDAO.findById(entity.getCuentas().getCueNumero());	
		if(cuenta == null) {
			throw new Exception("La cuenta con número: "+entity.getCuentas().getCueNumero()+" no existe");
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
		if(entity.getRetValor() == null) {
			throw new Exception("Debe ingresar el valor del retiro");
		}
		
		//Validamos que el valor no sea 0
		if(entity.getRetValor().doubleValue() == 0) {
			throw new Exception("Debe ingresar un valor superior a 0");
		}
		
		//Validamos que la fecha no sea null
		if(entity.getRetFecha() == null) {
			throw new Exception("Debe ingresar la fecha del retiro");
		}
		
		//Validamos que la fecha no sea pasada
		if(entity.getRetFecha().compareTo(new Date())>=0) {
			throw new Exception("El retiro debe hacerse en la fecha actual o una posterior a la actual");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getRetDescripcion()==null || entity.getRetDescripcion().trim().equals("")) {
			throw new Exception("Debe ingresar una descripción");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getRetDescripcion().length()>50) {
			throw new Exception("La descripción debe ser no mayor a 50 caracteres");
		}
		
		retirosDAO.update(entity);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Retiros entity) throws Exception {
		//Se valida que se ingrese una consignación
		if(entity == null) {
			throw new Exception("Debe ingresar un retiro");
		}
		
		//Verifico que la consignación no exista
		Retiros retiro = retirosDAO.findById(entity.getId());
		if(retiro == null) {
			throw new Exception("El retiro con Id "+entity.getId()+" no existe");
		}
		
		retirosDAO.delete(entity);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Retiros findById(RetirosId id) throws Exception {
		//Validamos que el id de la consignación no sea null
				if(id == null) {
					throw new Exception("Debe ingresar el id del retiro");
				}
				return retirosDAO.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Retiros> findAll() throws Exception {
		List<Retiros> lista = retirosDAO.findAll();
		
		//Validamos que la lista no esté vacía
		if(lista.size()==0) {
			throw new Exception("No hay retiros para mostrar");
		}
		
		return lista;
	}

}
