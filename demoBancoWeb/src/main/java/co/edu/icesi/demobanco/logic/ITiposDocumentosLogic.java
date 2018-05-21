package co.edu.icesi.demobanco.logic;

import java.util.List;

import co.edu.icesi.demobanco.modelo.TiposDocumentos;

public interface ITiposDocumentosLogic {
	
	public void save(TiposDocumentos entity) throws Exception;
	public void update(TiposDocumentos entity) throws Exception;
	public void delete(TiposDocumentos entity) throws Exception;
	public TiposDocumentos findById(long tdocId) throws Exception;
	public List<TiposDocumentos> findAll() throws Exception;


}
