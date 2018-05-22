package co.edu.icesi.demobanco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demobanco.modelo.Retiros;
import co.edu.icesi.demobanco.modelo.RetirosId;
@Repository //
@Scope("singleton")
public class RetirosDAO implements IRetirosDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Retiros entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void update(Retiros entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(Retiros entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public Retiros findById(RetirosId id) {
		return entityManager.find(Retiros.class, id);

	}

	@Override
	public List<Retiros> findAll() {
		String jpql = "Select ret from Retiros ret";
		return entityManager.createQuery(jpql).getResultList();
	}

}
