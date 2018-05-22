package co.edu.icesi.demobanco.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demobanco.dao.ITiposUsuariosDAO;
import co.edu.icesi.demobanco.dao.IUsuariosDAO;
import co.edu.icesi.demobanco.modelo.TiposUsuarios;
import co.edu.icesi.demobanco.modelo.Usuarios;
@Service
@Scope("singleton")
public class UsuariosLogic implements IUsuariosLogic {

	@Autowired
	private IUsuariosDAO usuariosDAO;
	
	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Usuarios entity) throws Exception {
		//Se valida que se ingrese un cliente
		if(entity == null) {
			throw new Exception("Debe ingresar un usuario");
		}
		
		//Validamos que el id del cliente no sea null
		if(entity.getUsuCedula() == 0) {
			throw new Exception("Debe ingresar el número de identificación del usuario");
		}
		
		//Verifico que el cliente no exista
		Usuarios usuario = usuariosDAO.findById(entity.getUsuCedula());
		if(usuario != null) {
			throw new Exception("El usuario con Id "+entity.getUsuCedula()+" ya existe");
		}
		
		//Validamos que el tipoDocumento no sea null y que su código sea válido
		if(entity.getTiposUsuarios()==null || entity.getTiposUsuarios().getTusuCodigo()==0) {
			throw new Exception("Debe ingresar un tipo de usuario");
		}
		
		//Validamos que exista el tipoDocumento que se le asigna al cliente
		TiposUsuarios tipoUsu = tiposUsuariosDAO.findById(entity.getTiposUsuarios().getTusuCodigo());	
		if(tipoUsu == null) {
			throw new Exception("El tipo de usuario: "+entity.getTiposUsuarios().getTusuCodigo()+" no existe");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getUsuNombre()==null || entity.getUsuNombre().trim().equals("")) {
			throw new Exception("Debe ingresar un nombre");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getUsuNombre().length()>50) {
			throw new Exception("El Nombre debe ser no mayor a 50 caracteres");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getUsuLogin()==null || entity.getUsuLogin().trim().equals("")) {
			throw new Exception("Debe ingresar un login");
		}
		
		//Se valida que el nombre no sea mayor de 30 caracteres
		if(entity.getUsuLogin().length()>30) {
			throw new Exception("El Login debe ser no mayor a 30 caracteres");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getUsuClave()==null || entity.getUsuClave().trim().equals("")) {
			throw new Exception("Debe ingresar una clave");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getUsuClave().length()>50) {
			throw new Exception("La clave debe ser no mayor a 50 caracteres");
		}

		usuariosDAO.save(entity);

	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Usuarios entity) throws Exception {
		//Se valida que se ingrese un cliente
		if(entity == null) {
			throw new Exception("Debe ingresar un usuario");
		}
		
		//Validamos que el id del cliente no sea null
		if(entity.getUsuCedula() == 0) {
			throw new Exception("Debe ingresar el número de identificación del usuario");
		}
		
		//Verifico que el cliente no exista
		Usuarios usuario = usuariosDAO.findById(entity.getUsuCedula());
		if(usuario == null) {
			throw new Exception("El usuario con Id "+entity.getUsuCedula()+" no existe");
		}
		
		//Validamos que el tipoDocumento no sea null y que su código sea válido
		if(entity.getTiposUsuarios()==null || entity.getTiposUsuarios().getTusuCodigo()==0) {
			throw new Exception("Debe ingresar un tipo de usuario");
		}
		
		//Validamos que exista el tipoDocumento que se le asigna al cliente
		TiposUsuarios tipoUsu = tiposUsuariosDAO.findById(entity.getTiposUsuarios().getTusuCodigo());	
		if(tipoUsu == null) {
			throw new Exception("El tipo de usuario: "+entity.getTiposUsuarios().getTusuCodigo()+" no existe");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getUsuNombre()==null || entity.getUsuNombre().trim().equals("")) {
			throw new Exception("Debe ingresar un nombre");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getUsuNombre().length()>50) {
			throw new Exception("El Nombre debe ser no mayor a 50 caracteres");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getUsuLogin()==null || entity.getUsuLogin().trim().equals("")) {
			throw new Exception("Debe ingresar un login");
		}
		
		//Se valida que el nombre no sea mayor de 30 caracteres
		if(entity.getUsuLogin().length()>30) {
			throw new Exception("El Login debe ser no mayor a 30 caracteres");
		}
		
		//Se valida que ingrese un nombre
		if(entity.getUsuClave()==null || entity.getUsuClave().trim().equals("")) {
			throw new Exception("Debe ingresar una clave");
		}
		
		//Se valida que el nombre no sea mayor de 50 caracteres
		if(entity.getUsuClave().length()>50) {
			throw new Exception("La clave debe ser no mayor a 50 caracteres");
		}

		usuariosDAO.update(entity);

	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Usuarios entity) throws Exception {
		//Se valida que se ingrese un cliente
				if(entity == null) {
					throw new Exception("Debe ingresar un usuario");
				}
				
				//Verifico que el cliente exista
				Usuarios usuario = usuariosDAO.findById(entity.getUsuCedula());
				if(usuario == null) {
					throw new Exception("El usuario con cedula "+entity.getUsuCedula()+" no existe");
				}
				
				usuariosDAO.delete(usuario);

	}

	@Override
	@Transactional(readOnly=true)
	public Usuarios findById(long usuCedula) throws Exception {
		//Validamos que el id del cliente no sea null
		if(usuCedula == 0) {
			throw new Exception("Debe ingresar la cedula del usuario");
		}
		
		return usuariosDAO.findById(usuCedula);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuarios> findAll() throws Exception {
	List<Usuarios> lista = usuariosDAO.findAll();
		
		//Validamos que la lista no esté vacía
		if(lista.size()==0) {
			throw new Exception("No hay usuarios para mostrar");
		}
		
		return lista;
	}

}
