package co.edu.icesi.demobanco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demobanco.modelo.Transferencias;
import co.edu.icesi.demobanco.modelo.TransferenciasId;
@Repository //
@Scope("singleton")
public class TransferenciasDAO implements ITransferenciasDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Transferencias entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void update(Transferencias entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(Transferencias entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public Transferencias findById(TransferenciasId id) {
		return entityManager.find(Transferencias.class, id);

	}

	@Override
	public List<Transferencias> findAll() {
		String jpql = "Select trans from Transferencias trans";
		return entityManager.createQuery(jpql).getResultList();
	}

}
