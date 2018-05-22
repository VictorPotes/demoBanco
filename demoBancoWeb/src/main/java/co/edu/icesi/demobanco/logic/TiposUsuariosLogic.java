package co.edu.icesi.demobanco.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demobanco.dao.ITiposUsuariosDAO;
import co.edu.icesi.demobanco.modelo.TiposUsuarios;
@Service
@Scope("singleton")
@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TiposUsuariosLogic implements ITiposUsuariosLogic {

	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;

	@Override
	public void save(TiposUsuarios entity) throws Exception {
		//Se valida que se ingrese un tipoDocumento
		if(entity == null) {
			throw new Exception("Debe ingresar un tipoUsuario");
		}
		
		//Validamos que el código del documento no sea null
		if(entity.getTusuCodigo() == 0) {
			throw new Exception("Debe ingresar el número de identificación del tipo de usuario");
		}

		
		//Verifico que el tipoDocumento no exista
		TiposUsuarios tipoUsuario = tiposUsuariosDAO.findById(entity.getTusuCodigo());
		if(tipoUsuario != null) {
			throw new Exception("El tipo de usuario con Id "+entity.getTusuCodigo()+" ya existe");
		}
		
		//Validamos que la nombre no venga nula ni vacía
		if(entity.getTusuNombre()==null || entity.getTusuNombre().trim().equals("")) {
			throw new Exception("Debe ingresar un nombre");
		}
		
		//Validamos que el nombre no sea mayor de 50 caracteres
		if(entity.getTusuNombre().length()>50) {
			throw new Exception("Debe ingresar un nombre no mayor a 50 caracteres");
		}
		
		tiposUsuariosDAO.save(entity);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(TiposUsuarios entity) throws Exception {
		//Se valida que se ingrese un tipoDocumento
		if(entity == null) {
			throw new Exception("Debe ingresar un tipoUsuario");
		}
		
		//Validamos que el código del documento no sea null
		if(entity.getTusuCodigo() == 0) {
			throw new Exception("Debe ingresar el número de identificación del tipo de usuario");
		}

		
		//Verifico que el tipoDocumento no exista
		TiposUsuarios tipoUsuario = tiposUsuariosDAO.findById(entity.getTusuCodigo());
		if(tipoUsuario == null) {
			throw new Exception("El tipo de usuario con Id "+entity.getTusuCodigo()+" no existe");
		}
		
		//Validamos que la nombre no venga nula ni vacía
		if(entity.getTusuNombre()==null || entity.getTusuNombre().trim().equals("")) {
			throw new Exception("Debe ingresar un nombre");
		}
		
		//Validamos que el nombre no sea mayor de 50 caracteres
		if(entity.getTusuNombre().length()>50) {
			throw new Exception("Debe ingresar un nombre no mayor a 50 caracteres");
		}
		
		tiposUsuariosDAO.update(entity);
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TiposUsuarios entity) throws Exception {
		//Se valida que se ingrese un cliente
				if(entity == null) {
					throw new Exception("Debe ingresar un tipo de usuario");
				}
				
				//Verifico que el tipoDocumento no exista
				TiposUsuarios tipoUsuario = tiposUsuariosDAO.findById(entity.getTusuCodigo());
				if(tipoUsuario == null) {
					throw new Exception("El tipo de usuario con Id "+entity.getTusuCodigo()+" no existe");
				}
				
				tiposUsuariosDAO.delete(tipoUsuario);
		
	}

	@Override
	@Transactional(readOnly=true)
	public TiposUsuarios findById(long tusuCodigo) throws Exception {
		//Validamos que el id del tipo de documento no sea null
		if(tusuCodigo == 0) {
			throw new Exception("Debe ingresar el número de identificación del tipo de usuario");
		}
		
		return 	tiposUsuariosDAO.findById(tusuCodigo);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TiposUsuarios> findAll() throws Exception {
	List<TiposUsuarios> lista = tiposUsuariosDAO.findAll();
		
		//Validamos que la lista no esté vacía
		if(lista.size()==0) {
			throw new Exception("No hay tiposDocumentos para mostrar");
		}
		
		return lista;
	}



}
