package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Departement;

/**
 * Session Bean implementation class DepartementDAO
 */
@Stateless
@LocalBean
public class DepartementDAO implements DAO<Departement> {
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public DepartementDAO() {
	}

	@Override
	public Departement create(Departement entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Departement findById(int id) {
		return entityManager.find(Departement.class, id);
	}

	@Override
	public Departement update(Departement entity) {
		return entity; // no-op.
	}

	@Override
	public void delete(Departement entity) {
		entityManager.remove(entity);
	}

	public List<Departement> findAll() {
		Query query = entityManager
				.createQuery("select departement from Departement departement");
				return (List<Departement>) query.getResultList();

	}
}
