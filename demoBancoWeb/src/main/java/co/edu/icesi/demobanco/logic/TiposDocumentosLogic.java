package co.edu.icesi.demobanco.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demobanco.dao.ITiposDocumentosDAO;
import co.edu.icesi.demobanco.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class TiposDocumentosLogic implements ITiposDocumentosLogic {
	
	@Autowired
	private ITiposDocumentosDAO tiposDocumentosDAO;

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(TiposDocumentos entity) throws Exception {
		
		//Se valida que se ingrese un tipoDocumento
		if(entity == null) {
			throw new Exception("Debe ingresar un tipoDocumento");
		}
		
		//Validamos que el código del documento no sea null
		if(entity.getTdocCodigo() == 0) {
			throw new Exception("Debe ingresar el número de identificación del tipo de documento");
		}

		
		//Verifico que el tipoDocumento no exista
		TiposDocumentos tipoDocumento = tiposDocumentosDAO.findById(entity.getTdocCodigo());
		if(tipoDocumento != null) {
			throw new Exception("El tipo de documento con Id "+entity.getTdocCodigo()+" ya existe");
		}
		
		//Validamos que la nombre no venga nula ni vacía
		if(entity.getTdocNombre()==null || entity.getTdocNombre().trim().equals("")) {
			throw new Exception("Debe ingresar un nombre");
		}
		
		//Validamos que el nombre no sea mayor de 50 caracteres
		if(entity.getTdocNombre().length()>50) {
			throw new Exception("Debe ingresar un nombre no mayor a 50 caracteres");
		}
		
		tiposDocumentosDAO.save(entity);
		
		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(TiposDocumentos entity) throws Exception {
		//Se valida que se ingrese un tipoDocumento
		if(entity == null) {
			throw new Exception("Debe ingresar un tipoDocumento");
		}
		
		//Validamos que el código del documento no sea null
		if(entity.getTdocCodigo() == 0) {
			throw new Exception("Debe ingresar el número de identificación del tipo de documento");
		}

		//Verifico que el tipoDocumento no exista
		TiposDocumentos tipoDocumento = tiposDocumentosDAO.findById(entity.getTdocCodigo());
		if(tipoDocumento == null) {
			throw new Exception("El tipo de documento con Id "+entity.getTdocCodigo()+" NO existe");
		}
		
		//Validamos que la nombre no venga nula ni vacía
		if(entity.getTdocNombre()==null || entity.getTdocNombre().trim().equals("")) {
			throw new Exception("Debe ingresar un nombre");
		}
		
		//Validamos que el nombre no sea mayor de 50 caracteres
		if(entity.getTdocNombre().length()>50) {
			throw new Exception("Debe ingresar un nombre no mayor a 50 caracteres");
		}
		
		tiposDocumentosDAO.update(entity);		
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TiposDocumentos entity) throws Exception {
		//Se valida que se ingrese un cliente
		if(entity == null) {
			throw new Exception("Debe ingresar un Tipo de documento");
		}
		
		//Verifico que el clente no exista
		TiposDocumentos tipo = tiposDocumentosDAO.findById(entity.getTdocCodigo());
		if(tipo == null) {
			throw new Exception("El tipoDocumento con código "+entity.getTdocCodigo()+" no existe");
		}
		
		tiposDocumentosDAO.delete(tipo);
		
	}

	@Override
	@Transactional(readOnly=true)
	public TiposDocumentos findById(long tdocId) throws Exception {
		
		//Validamos que el id del tipo de documento no sea null
		if(tdocId == 0) {
			throw new Exception("Debe ingresar el número de identificación del tipo de documento");
		}
		
		return 	tiposDocumentosDAO.findById(tdocId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TiposDocumentos> findAll() throws Exception {
		
		List<TiposDocumentos> lista = tiposDocumentosDAO.findAll();
		
		//Validamos que la lista no esté vacía
		if(lista.size()==0) {
			throw new Exception("No hay tiposDocumentos para mostrar");
		}
		
		return lista;
	}
	

	
}
