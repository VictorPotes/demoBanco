package co.edu.icesi.demobanco.logic;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Consignaciones;
import co.edu.icesi.demobanco.modelo.ConsignacionesId;

public interface IConsignacionesLogic {
	
	public void save(Consignaciones entity)  throws Exception;
	public void update(Consignaciones entity) throws Exception;
	public void delete(Consignaciones entity) throws Exception;
	public Consignaciones findById(ConsignacionesId id) throws Exception;
	public List<Consignaciones> findAll() throws Exception;

}
