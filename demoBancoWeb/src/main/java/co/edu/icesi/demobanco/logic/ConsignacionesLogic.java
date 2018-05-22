package co.edu.icesi.demobanco.logic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demobanco.dao.IConsignacionesDAO;
import co.edu.icesi.demobanco.dao.ICuentasDAO;
import co.edu.icesi.demobanco.dao.IUsuariosDAO;
import co.edu.icesi.demobanco.modelo.Consignaciones;
import co.edu.icesi.demobanco.modelo.ConsignacionesId;
import co.edu.icesi.demobanco.modelo.Cuentas;
import co.edu.icesi.demobanco.modelo.Usuarios;

@Service
@Scope("singleton")
public class ConsignacionesLogic implements  IConsignacionesLogic {
	
	@Autowired
	private IConsignacionesDAO consignacionesDAO;
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	@Autowired
	private IUsuariosDAO usuariosDAO;

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Consignaciones entity) throws Exception {
		
		//Se valida que se ingrese una consignaci�n
		if(entity == null) {
			throw new Exception("Debe ingresar una consignaci�n");
		}
		
		//Validamos que el id de la consignaci�n no sea null
		if(entity.getId() == null) {
			throw new Exception("Debe ingresar el id de la consignaci�n ");
		}
		
		//Verifico que el cliente no exista
		Consignaciones consignacion = consignacionesDAO.findById(entity.getId());
		if(consignacion != null) {
			throw new Exception("La consignaci�n con Id "+entity.getId()+" ya existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getCuentas() == null) {
			throw new Exception("Debe ingresar una cuenta para la consignaci�n");
		}
		
		//Validamos que exista la cuenta a la que se le asigna la consignaci�n
		Cuentas cuenta = cuentasDAO.findById(entity.getCuentas().getCueNumero());	
		if(cuenta == null) {
			throw new Exception("La cuenta con n�mero: "+entity.getCuentas().getCueNumero()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getUsuarios() != null){
			//Validamos que exista la cuenta a la que se le asigna la consignaci�n
			Usuarios usuario = usuariosDAO.findById(entity.getUsuarios().getUsuCedula());	
			if(usuario == null) {
				throw new Exception("El usuario con c�dula: "+entity.getUsuarios().getUsuCedula()+" no existe");
			}
		}
		
		//Validamos que el valor no sea null
		if(entity.getConValor() == null) {
			throw new Exception("Debe ingresar el valor de la consignaci�n");
		}
		
		//Validamos que el valor no sea 0
		if(entity.getConValor().doubleValue() == 0) {
			throw new Exception("Debe ingresar un valor superior a 0");
		}
		
		//Validamos que la fecha no sea null
		if(entity.getConFecha() == null) {
			throw new Exception("Debe ingresar la fecha de la consignaci�n");
		}
		
		//Validamos que la fecha no sea pasada
		if(entity.getConFecha().compareTo(new Date())>=0) {
			throw new Exception("La consignaci�n debe hacerse en la fecha actual o una posterior a la actual");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getConDescripcion()==null || entity.getConDescripcion().trim().equals("")) {
			throw new Exception("Debe ingresar una descripci�n");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getConDescripcion().length()>50) {
			throw new Exception("La descripci�n debe ser no mayor a 50 caracteres");
		}
		
		consignacionesDAO.save(entity);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Consignaciones entity) throws Exception {
		//Se valida que se ingrese una consignaci�n
		if(entity == null) {
			throw new Exception("Debe ingresar una consignaci�n");
		}
		
		//Validamos que el id de la consignaci�n no sea null
		if(entity.getId() == null) {
			throw new Exception("Debe ingresar el id de la consignaci�n ");
		}
		
		//Verifico que el cliente no exista
		Consignaciones consignacion = consignacionesDAO.findById(entity.getId());
		if(consignacion == null) {
			throw new Exception("La consignaci�n con Id "+entity.getId()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getCuentas() == null) {
			throw new Exception("Debe ingresar una cuenta para la consignaci�n");
		}
		
		//Validamos que exista la cuenta a la que se le asigna la consignaci�n
		Cuentas cuenta = cuentasDAO.findById(entity.getCuentas().getCueNumero());	
		if(cuenta == null) {
			throw new Exception("La cuenta con n�mero: "+entity.getCuentas().getCueNumero()+" no existe");
		}
		
		//Se valida que se ingrese una cuenta
		if(entity.getUsuarios() != null){
			//Validamos que exista la cuenta a la que se le asigna la consignaci�n
			Usuarios usuario = usuariosDAO.findById(entity.getUsuarios().getUsuCedula());	
			if(usuario == null) {
				throw new Exception("El usuario con c�dula: "+entity.getUsuarios().getUsuCedula()+" no existe");
			}
		}
		
		//Validamos que el valor no sea null
		if(entity.getConValor() == null) {
			throw new Exception("Debe ingresar el valor de la consignaci�n");
		}
		
		//Validamos que el valor no sea 0
		if(entity.getConValor().doubleValue() == 0) {
			throw new Exception("Debe ingresar un valor superior a 0");
		}
		
		//Validamos que la fecha no sea null
		if(entity.getConFecha() == null) {
			throw new Exception("Debe ingresar la fecha de la consignaci�n");
		}
		
		//Validamos que la fecha no sea pasada
		if(entity.getConFecha().compareTo(new Date())>=0) {
			throw new Exception("La consignaci�n debe hacerse en la fecha actual o una posterior a la actual");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getConDescripcion()==null || entity.getConDescripcion().trim().equals("")) {
			throw new Exception("Debe ingresar una descripci�n");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getConDescripcion().length()>50) {
			throw new Exception("La descripci�n debe ser no mayor a 50 caracteres");
		}
		
		consignacionesDAO.update(entity);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Consignaciones entity) throws Exception {
		//Se valida que se ingrese una consignaci�n
		if(entity == null) {
			throw new Exception("Debe ingresar una consignaci�n");
		}
		
		//Verifico que la consignaci�n no exista
		Consignaciones consignacion = consignacionesDAO.findById(entity.getId());
		if(consignacion == null) {
			throw new Exception("La consignaci�n con Id "+entity.getId()+" no existe");
		}
		
		consignacionesDAO.delete(entity);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Consignaciones findById(ConsignacionesId id) throws Exception {
		//Validamos que el id de la consignaci�n no sea null
		if(id == null) {
			throw new Exception("Debe ingresar el id de la consignaci�n ");
		}
		return consignacionesDAO.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Consignaciones> findAll() throws Exception {
		List<Consignaciones> lista = consignacionesDAO.findAll();
		
		//Validamos que la lista no est� vac�a
		if(lista.size()==0) {
			throw new Exception("No hay consignaciones para mostrar");
		}
		
		return lista;
	}
	
	

}
