package co.edu.icesi.demobanco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demobanco.modelo.TiposDocumentos;

@Repository //
@Scope("singleton")
public class TiposDocumentosDAO implements ITiposDocumentosDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TiposDocumentos entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void update(TiposDocumentos entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(TiposDocumentos entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public TiposDocumentos findById(long tdocId) {
		return entityManager.find(TiposDocumentos.class, tdocId);

	}

	@Override
	public List<TiposDocumentos> findAll() {
		String jpql = "Select tipo from TiposDocumentos tipo";
		return entityManager.createQuery(jpql).getResultList();
	}

}
