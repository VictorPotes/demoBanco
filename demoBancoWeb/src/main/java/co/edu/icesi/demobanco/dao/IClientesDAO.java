package co.edu.icesi.demobanco.dao;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Clientes;

public interface IClientesDAO {
	
public void save(Clientes entity);
public void update(Clientes entity);
public void delete(Clientes entity);
public Clientes findById(long cliId);
public List<Clientes> findAll();


}
