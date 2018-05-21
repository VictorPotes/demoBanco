package co.edu.icesi.demobanco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demobanco.modelo.Consignaciones;
import co.edu.icesi.demobanco.modelo.ConsignacionesId;

@Repository //
@Scope("singleton")
public class ConsignacionesDAO implements IConsignacionesDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Consignaciones entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void update(Consignaciones entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(Consignaciones entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public Consignaciones findById(ConsignacionesId id) {
		return entityManager.find(Consignaciones.class, id);

	}

	@Override
	public List<Consignaciones> findAll() {
		String jpql = "Select id from Consignaciones id";
		return entityManager.createQuery(jpql).getResultList();
	}

}
