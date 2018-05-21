package co.edu.icesi.demobanco.dao;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Consignaciones;
import co.edu.icesi.demobanco.modelo.ConsignacionesId;

public interface IConsignacionesDAO {
	
	public void save(Consignaciones entity);
	public void update(Consignaciones entity);
	public void delete(Consignaciones entity);
	public Consignaciones findById(ConsignacionesId id);
	public List<Consignaciones> findAll();

}
