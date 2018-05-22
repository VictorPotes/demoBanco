package co.edu.icesi.demobanco.logic;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Transferencias;
import co.edu.icesi.demobanco.modelo.TransferenciasId;

public interface ITransferenciasLogic {
	
	public void save(Transferencias entity)  throws Exception;
	public void update(Transferencias entity) throws Exception;
	public void delete(Transferencias entity) throws Exception;
	public Transferencias findById(TransferenciasId id) throws Exception;
	public List<Transferencias> findAll() throws Exception;

}
