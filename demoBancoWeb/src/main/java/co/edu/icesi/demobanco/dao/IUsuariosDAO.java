package co.edu.icesi.demobanco.dao;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Usuarios;

public interface IUsuariosDAO {
	
	public void save(Usuarios entity);
	public void update(Usuarios entity);
	public void delete(Usuarios entity);
	public Usuarios findById(long usuCedula);
	public List<Usuarios> findAll();

}
