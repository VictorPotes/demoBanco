package co.edu.icesi.demobanco.logic;

import java.util.List;

import co.edu.icesi.demobanco.modelo.TiposUsuarios;

public interface ITiposUsuariosLogic {

	public void save(TiposUsuarios entity)  throws Exception;
	public void update(TiposUsuarios entity) throws Exception;
	public void delete(TiposUsuarios entity) throws Exception;
	public TiposUsuarios findById(long tusuCodigo) throws Exception;
	public List<TiposUsuarios> findAll() throws Exception;
}
