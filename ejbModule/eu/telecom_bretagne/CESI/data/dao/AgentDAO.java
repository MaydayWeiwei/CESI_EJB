package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Agent;

/**
 * Session Bean implementation class AgentDAO
 */
@Stateless
@LocalBean
public class AgentDAO implements DAO<Agent> {
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AgentDAO() {
	}

	@Override
	public Agent create(Agent entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Agent findById(int id) {
		return entityManager.find(Agent.class, id);
	}

	@Override
	public Agent update(Agent entity) {
		return entity; // no-op.
	}

	@Override
	public void delete(Agent entity) {
		entityManager.remove(entity);
	}

	public List<Agent> findAll() {

		Query query = entityManager.createQuery("select agent from Agent agent");
		return (List<Agent>) query.getResultList();

	}

}
