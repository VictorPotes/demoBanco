package co.edu.icesi.demobanco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demobanco.modelo.TiposUsuarios;
@Repository //
@Scope("singleton")
public class TiposUsuariosDAO implements ITiposUsuariosDAO {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public void save(TiposUsuarios entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void update(TiposUsuarios entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(TiposUsuarios entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public TiposUsuarios findById(long tusuCodigo) {
		return entityManager.find(TiposUsuarios.class, tusuCodigo);

	}

	@Override
	public List<TiposUsuarios> findAll() {
		String jpql = "Select tus from TiposUsuarios tus";
		return entityManager.createQuery(jpql).getResultList();
	}

}
