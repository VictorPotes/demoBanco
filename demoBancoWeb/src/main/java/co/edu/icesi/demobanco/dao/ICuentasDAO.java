package co.edu.icesi.demobanco.dao;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Cuentas;

public interface ICuentasDAO {

	public void save(Cuentas entity);
	public void update(Cuentas entity);
	public void delete(Cuentas entity);
	public Cuentas findById(String cueNumero);
	public List<Cuentas> findAll();
	
}
