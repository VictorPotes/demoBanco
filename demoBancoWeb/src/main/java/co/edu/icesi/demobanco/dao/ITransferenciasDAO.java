package co.edu.icesi.demobanco.dao;

import java.util.List;

import co.edu.icesi.demobanco.modelo.Transferencias;
import co.edu.icesi.demobanco.modelo.TransferenciasId;

public interface ITransferenciasDAO {
	
	public void save(Transferencias entity);
	public void update(Transferencias entity);
	public void delete(Transferencias entity);
	public Transferencias findById(TransferenciasId id);
	public List<Transferencias> findAll();

}
