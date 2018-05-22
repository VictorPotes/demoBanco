package co.edu.icesi.demobanco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demobanco.modelo.Usuarios;
@Repository //
@Scope("singleton")
public class UsuariosDAO implements IUsuariosDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Usuarios entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void update(Usuarios entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(Usuarios entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public Usuarios findById(long usuCedula) {
		return entityManager.find(Usuarios.class, usuCedula);

	}

	@Override
	public List<Usuarios> findAll() {
		String jpql = "Select usu from Usuarios usu";
		return entityManager.createQuery(jpql).getResultList();
	}

}
