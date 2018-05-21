package co.edu.icesi.demobanco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demobanco.modelo.Clientes;

@Repository //
@Scope("singleton")
public class ClientesDAO implements IClientesDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Clientes entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void update(Clientes entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(Clientes entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public Clientes findById(long cliId) {
		return entityManager.find(Clientes.class, cliId);
	}

	@Override
	public List<Clientes> findAll() {
		String jpql = "Select cli from Clientes cli";
		return entityManager.createQuery(jpql).getResultList();
	}

}
