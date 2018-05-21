package co.edu.icesi.demobanco.businessdelegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.icesi.demobanco.logic.IClienteLogic;
import co.edu.icesi.demobanco.logic.ICuentasLogic;
import co.edu.icesi.demobanco.logic.ITiposDocumentosLogic;
import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.Cuentas;
import co.edu.icesi.demobanco.modelo.TiposDocumentos;



@Component("businessDelegate")
@Scope("singleton")
public class BusinessDelegate implements IBusinessDelegate {
	
	@Autowired
	private IClienteLogic clienteLogic;
	
	@Autowired
	private ITiposDocumentosLogic tiposDocumentosLogic;
	
	@Autowired
	private ICuentasLogic cuentasLogic;

	@Override
	public void saveCliente(Clientes entity) throws Exception {
		clienteLogic.save(entity);
		
	}

	@Override
	public void updateCliente(Clientes entity) throws Exception {
		clienteLogic.update(entity);
		
	}

	@Override
	public void deleteCliente(Clientes entity) throws Exception {
		clienteLogic.delete(entity);
		
	}

	@Override
	public Clientes findByIdCliente(long cliId) throws Exception {
		return clienteLogic.findById(cliId);
	}

	@Override
	public List<Clientes> findAllClientes() throws Exception {
		return clienteLogic.findAll();
	}

	@Override
	public void saveCuenta(Cuentas entity) throws Exception {
		cuentasLogic.save(entity);	}

	@Override
	public void updateCuenta(Cuentas entity) throws Exception {
		cuentasLogic.update(entity);		
	}

	@Override
	public void deleteCuenta(Cuentas entity) throws Exception {
		cuentasLogic.delete(entity);		
	}

	@Override
	public Cuentas findByIdCuenta(String cueNumero) throws Exception {
		return cuentasLogic.findById(cueNumero);
	}

	@Override
	public List<Cuentas> findAllCuentas() throws Exception {
		return cuentasLogic.findAll();
	}

	@Override
	public void saveTipoDocumento(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogic.save(entity);		
	}

	@Override
	public void updateTipoDocumento(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogic.update(entity);		
	}

	@Override
	public void deleteTipoDocumento(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogic.delete(entity);		
	}

	@Override
	public TiposDocumentos findByIdTipoDocumento(long tdocId) throws Exception {
		return tiposDocumentosLogic.findById(tdocId);
	}

	@Override
	public List<TiposDocumentos> findAllTiposDocumentos() throws Exception {
		return tiposDocumentosLogic.findAll();
	}

	
	
	
	

}
