package co.edu.icesi.demobanco.logic;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Retiros;
import co.edu.icesi.demobanco.modelo.RetirosId;

public interface IRetirosLogic {
	
	public void save(Retiros entity)  throws Exception;
	public void update(Retiros entity) throws Exception;
	public void delete(Retiros entity) throws Exception;
	public Retiros findById(RetirosId id) throws Exception;
	public List<Retiros> findAll() throws Exception;

}
