package co.edu.icesi.demobanco.dao;

import java.util.List;

import co.edu.icesi.demobanco.modelo.TiposDocumentos;

public interface ITiposDocumentosDAO {
	
	public void save(TiposDocumentos entity);
	public void update(TiposDocumentos entity);
	public void delete(TiposDocumentos entity);
	public TiposDocumentos findById(long tdocId);
	public List<TiposDocumentos> findAll();

}
