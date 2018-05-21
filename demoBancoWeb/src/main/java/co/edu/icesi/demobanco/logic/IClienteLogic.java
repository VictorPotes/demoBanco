package co.edu.icesi.demobanco.logic;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Clientes;

public interface IClienteLogic {

	public void save(Clientes entity)  throws Exception;
	public void update(Clientes entity) throws Exception;
	public void delete(Clientes entity) throws Exception;
	public Clientes findById(long cliId) throws Exception;
	public List<Clientes> findAll() throws Exception;
	
	
}
