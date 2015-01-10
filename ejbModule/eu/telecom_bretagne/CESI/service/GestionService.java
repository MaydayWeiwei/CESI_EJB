package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.EmployeDAO;
import eu.telecom_bretagne.CESI.data.dao.ServiceDAO;
import eu.telecom_bretagne.CESI.data.model.Employe;
import eu.telecom_bretagne.CESI.data.model.Service;

/**
 * Session Bean implementation class GestionDepartement
 */
@Stateless
@LocalBean
public class GestionService implements IGestionService {
	@EJB
	ServiceDAO serviceDAO;
	
	@EJB
	EmployeDAO employeDAO;

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

	@Override
	public Service creerService(String nomService, int responsableId) {
		Employe employe = employeDAO.findById(responsableId);
		Service service = new Service();
		service.setNom(nomService);
		service.setResponsableFk(responsableId);
		service.addEmploye(employe);
		serviceDAO.create(service);
		employeDAO.update(employe);
		return service;
	}
	
	@Override
	public Service creerService(String nomService, int responsableId, int serviceRattacheId) {
		Employe employe = employeDAO.findById(responsableId);
		Service serviceRattache = serviceDAO.findById(serviceRattacheId);
		Service service = new Service();
		service.setNom(nomService);
		service.setResponsableFk(responsableId);
		service.addEmploye(employe);
		serviceRattache.addService(service);
		serviceDAO.create(service);
		employeDAO.update(employe);
		serviceDAO.update(serviceRattache);
		return service;
	}

}
