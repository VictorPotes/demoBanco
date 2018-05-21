package co.edu.icesi.demobanco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demobanco.modelo.Cuentas;

@Repository //
@Scope("singleton")
public class CuentasDAO implements ICuentasDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Cuentas entity) {
		entityManager.persist(entity);		
	}

	@Override
	public void update(Cuentas entity) {
		entityManager.merge(entity);		
	}

	@Override
	public void delete(Cuentas entity) {
		entityManager.remove(entity);
	}

	@Override
	public Cuentas findById(String cueNumero) {
		return entityManager.find(Cuentas.class, cueNumero);
	}

	@Override
	public List<Cuentas> findAll() {
		String jpql = "Select cue from Cuentas cue";
		return entityManager.createQuery(jpql).getResultList();
	}

}
