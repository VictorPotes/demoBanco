package co.edu.icesi.demobanco.businessdelegate;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Clientes;
import co.edu.icesi.demobanco.modelo.Cuentas;
import co.edu.icesi.demobanco.modelo.TiposDocumentos;

public interface IBusinessDelegate {
	
	public void saveCliente(Clientes entity)  throws Exception;
	public void updateCliente(Clientes entity)  throws Exception;
	public void deleteCliente(Clientes entity)  throws Exception;
	public Clientes findByIdCliente(long cliId)  throws Exception;
	public List<Clientes> findAllClientes()  throws Exception;
	
	public void saveCuenta(Cuentas entity)  throws Exception;
	public void updateCuenta(Cuentas entity) throws Exception;
	public void deleteCuenta(Cuentas entity) throws Exception;
	public Cuentas findByIdCuenta(String cueNumero) throws Exception;
	public List<Cuentas> findAllCuentas() throws Exception;
	
	public void saveTipoDocumento(TiposDocumentos entity) throws Exception;
	public void updateTipoDocumento(TiposDocumentos entity) throws Exception;
	public void deleteTipoDocumento(TiposDocumentos entity) throws Exception;
	public TiposDocumentos findByIdTipoDocumento(long tdocId) throws Exception;
	public List<TiposDocumentos> findAllTiposDocumentos() throws Exception;
	

}
