package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Employe;

/**
 * Session Bean implementation class AgentDAO
 */
@Stateless
@LocalBean
public class EmployeDAO implements DAO<Employe> {
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public EmployeDAO() {
	}

	@Override
	public Employe create(Employe entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Employe findById(int id) {
		return entityManager.find(Employe.class, id);
	}

	@Override
	public Employe update(Employe entity) {
		return entity; // no-op.
	}

	@Override
	public void delete(Employe entity) {
	}

	public List<Employe> findAll() {

		Query query = entityManager.createQuery("select employe from Employe employe");
		return (List<Employe>) query.getResultList();

	}

}
