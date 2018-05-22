package co.edu.icesi.demobanco.dao;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Retiros;
import co.edu.icesi.demobanco.modelo.RetirosId;

public interface IRetirosDAO {
	
	public void save(Retiros entity);
	public void update(Retiros entity);
	public void delete(Retiros entity);
	public Retiros findById(RetirosId id);
	public List<Retiros> findAll();

}
