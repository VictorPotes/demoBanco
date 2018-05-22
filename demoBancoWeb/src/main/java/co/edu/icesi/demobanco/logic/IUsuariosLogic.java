package co.edu.icesi.demobanco.logic;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Usuarios;

public interface IUsuariosLogic {
	
	public void save(Usuarios entity)  throws Exception;
	public void update(Usuarios entity) throws Exception;
	public void delete(Usuarios entity) throws Exception;
	public Usuarios findById(long usuCedula) throws Exception;
	public List<Usuarios> findAll() throws Exception;

}
