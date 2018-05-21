package co.edu.icesi.demobanco.logic;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demobanco.dao.IClientesDAO;
import co.edu.icesi.demobanco.dao.ICuentasDAO;
import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.Cuentas;

@Service
@Scope("singleton")
public class CuentasLogic implements ICuentasLogic {
	
	@Autowired
	private IClientesDAO clientesDAO;
	
	@Autowired
	private ICuentasDAO cuentasDAO;

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Cuentas entity) throws Exception {
		
		//Se valida que se ingrese una cuenta
		if(entity == null) {
			throw new Exception("Debe ingresar una cuenta");
		}
		
		//Validamos que el id de la cuenta no sea null
		if(entity.getCueNumero() == null || entity.getCueNumero().equals("")) {
			throw new Exception("Debe ingresar el número de la cuenta");
		}
		
		//Validamos que el id del numero de cuenta no supere los 30 caracteres
		if(entity.getCueNumero().length()>30) {
			throw new Exception("Debe ingresar un número de cuenta no mayor a 30 caracteres");
		}
		
		//Verifico que el cuenta exista
		Cuentas cuenta = cuentasDAO.findById(entity.getCueNumero());
		if(cuenta != null) {
			throw new Exception("La cuenta con número "+entity.getCueNumero()+" ya existe");
		}
		
		//Validamos que el Cliente no sea null y que su Id sea válido
		if(entity.getClientes()==null || entity.getClientes().getCliId()==0) {
			throw new Exception("Debe ingresar un cliente");
		}
		
		//Validamos que exista el cliente que se le asigna la cuenta
		Clientes cliente = clientesDAO.findById(entity.getClientes().getCliId());	
		if(cliente == null) {
			throw new Exception("El cliente con Id: "+entity.getClientes().getCliId()+" no existe");
		}
		
		//Validamos que exista un saldo en la cuenta
		if(entity.getCueSaldo()==null) {
			throw new Exception("Debe ingresar un saldo a la cuenta");
		}
		
		//Validamos que el estado de la cuenta no venga nula ni vacía
		if(entity.getCueActiva()==null || entity.getCueActiva().trim().equals("")) {
			throw new Exception("Debe ingresar un estado de cuenta (Si - No)");
		}
		
		//Validamos que la cuenta Activa no sea mayor de 2 caracteres
		if(entity.getCueActiva().length()>2) {
			throw new Exception("Debe ingresar un estado de cuenta no mayor a 2 caracteres");
		}
		
		//Validamos que la clave de la cuenta no venga nula ni vacía
		if(entity.getCueClave()==null || entity.getCueClave().trim().equals("")) {
			throw new Exception("Debe ingresar una clave a la cuenta");
		}
		
		//Validamos que la la clave de la cuenta no sea mayor de 50 caracteres
		if(entity.getCueClave().length()>50) {
			throw new Exception("Debe ingresar una clave a la cuenta no mayor a 50 caracteres");
		}
		
		cuentasDAO.save(entity);

		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Cuentas entity) throws Exception {
		//Se valida que se ingrese una cuenta
		if(entity == null) {
			throw new Exception("Debe ingresar una cuenta");
		}
		
		//Validamos que el id de la cuenta no sea null
		if(entity.getCueNumero() == null || entity.getCueNumero().equals("")) {
			throw new Exception("Debe ingresar el número de la cuenta");
		}
		
		//Validamos que el id del numero de cuenta no supere los 30 caracteres
		if(entity.getCueNumero().length()>30) {
			throw new Exception("Debe ingresar un número de cuenta no mayor a 30 caracteres");
		}
		
		//Verifico que la cuenta exista
		Cuentas cuenta = cuentasDAO.findById(entity.getCueNumero());
		if(cuenta == null) {
			throw new Exception("La cuenta con número "+entity.getCueNumero()+" No existe");
		}
		
		//Validamos que el Cliente no sea null y que su Id sea válido
		if(entity.getClientes()==null || entity.getClientes().getCliId()==0) {
			throw new Exception("Debe ingresar un cliente");
		}
		
		//Validamos que exista el cliente que se le asigna la cuenta
		Clientes cliente = clientesDAO.findById(entity.getClientes().getCliId());	
		if(cliente == null) {
			throw new Exception("El cliente con Id: "+entity.getClientes().getCliId()+" no existe");
		}
		
		//Validamos que exista un saldo en la cuenta
		if(entity.getCueSaldo()==null) {
			throw new Exception("Debe ingresar un saldo a la cuenta");
		}
		
		//Validamos que el estado de la cuenta no venga nula ni vacía
		if(entity.getCueActiva()==null || entity.getCueActiva().trim().equals("")) {
			throw new Exception("Debe ingresar un estado de cuenta (Si - No)");
		}
		
		//Validamos que la cuenta Activa no sea mayor de 2 caracteres
		if(entity.getCueActiva().length()>2) {
			throw new Exception("Debe ingresar un estado de cuenta no mayor a 2 caracteres");
		}
		
		//Validamos que la clave de la cuenta no venga nula ni vacía
		if(entity.getCueClave()==null || entity.getCueClave().trim().equals("")) {
			throw new Exception("Debe ingresar una clave a la cuenta");
		}
		
		//Validamos que la la clave de la cuenta no sea mayor de 50 caracteres
		if(entity.getCueClave().length()>50) {
			throw new Exception("Debe ingresar una clave a la cuenta no mayor a 50 caracteres");
		}
		
		cuentasDAO.update(entity);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Cuentas entity) throws Exception {
		//Se valida que se ingrese una cuenta
		if(entity == null) {
			throw new Exception("Debe ingresar una cuenta");
		}
		
		//Verifico que la cuenta exista
		Cuentas cuenta = cuentasDAO.findById(entity.getCueNumero());
		if(cuenta == null) {
			throw new Exception("La cuenta con número "+entity.getCueNumero()+" no existe");
		}
		
		cuentasDAO.delete(cuenta);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cuentas findById(String cueNumero) throws Exception {
		//Validamos que el número de cuenta no sea null
		if(cueNumero == null || cueNumero.equals("")) {
			throw new Exception("Debe ingresar el número de cuenta");
		}
		//Validamos que el id del numero de cuenta no supere los 30 caracteres
		if(cueNumero.length()>30) {
			throw new Exception("Debe ingresar un número de cuenta no mayor a 30 caracteres");
		}
		
		return cuentasDAO.findById(cueNumero);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cuentas> findAll() throws Exception {
		List<Cuentas> lista = cuentasDAO.findAll();
		
		//Validamos que la lista no esté vacía
		if(lista.size()==0) {
			throw new Exception("No hay cuentas para mostrar");
		}
		
		return lista;
	}

	
	

}
