package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Service;

/**
 * Session Bean implementation class DepartementDAO
 */
@Stateless
@LocalBean
public class ServiceDAO implements DAO<Service> {
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ServiceDAO() {
	}

	@Override
	public Service create(Service entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Service findById(int id) {
		return entityManager.find(Service.class, id);
	}

	@Override
	public Service findByName(String name) {
		Query query = entityManager
				.createQuery("select service from Service service where service.nom= :nom");
		query.setParameter("nom", name);
		try{
			return (Service) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	@Override
	public Service update(Service entity) {
		return entity; // no-op.
	}

	@Override
	public void delete(Service entity) {
		entityManager.remove(entity);
	}

	public List<Service> findAll() {
		Query query = entityManager
				.createQuery("select service from Service service");
		return (List<Service>) query.getResultList();

	}
}
