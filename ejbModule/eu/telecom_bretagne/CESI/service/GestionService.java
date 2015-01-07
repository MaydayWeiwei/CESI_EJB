package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.ServiceDAO;
import eu.telecom_bretagne.CESI.data.model.Service;

/**
 * Session Bean implementation class GestionDepartement
 */
@Stateless
@LocalBean
public class GestionService implements IGestionService {
	@EJB
	ServiceDAO serviceDAO;

	/**
	 * Default constructor.
	 */
	public GestionService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Service lireService(int identifiant) {
		return serviceDAO.findById(identifiant);
	}

	@Override
	public List<Service> listeServices() {
		return serviceDAO.findAll();
	}

}
