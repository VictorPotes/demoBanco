package co.edu.icesi.demobanco.logic;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Cuentas;

public interface ICuentasLogic {
	
	public void save(Cuentas entity)  throws Exception;
	public void update(Cuentas entity) throws Exception;
	public void delete(Cuentas entity) throws Exception;
	public Cuentas findById(String cueNumero) throws Exception;
	public List<Cuentas> findAll() throws Exception;

}
