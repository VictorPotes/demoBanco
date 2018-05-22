package co.edu.icesi.demobanco.dao;

import java.util.List;

import co.edu.icesi.demobanco.modelo.TiposUsuarios;

public interface ITiposUsuariosDAO {
	
	public void save(TiposUsuarios entity);
	public void update(TiposUsuarios entity);
	public void delete(TiposUsuarios entity);
	public TiposUsuarios findById(long tusuCodigo);
	public List<TiposUsuarios> findAll();

}
