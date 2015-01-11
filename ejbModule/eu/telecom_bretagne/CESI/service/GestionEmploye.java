package eu.telecom_bretagne.CESI.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.EmployeDAO;
import eu.telecom_bretagne.CESI.data.dao.ServiceDAO;
import eu.telecom_bretagne.CESI.data.model.Employe;
import eu.telecom_bretagne.CESI.data.model.Service;

/**
 * Session Bean implementation class GestionAgent
 */
@Stateless
@LocalBean
public class GestionEmploye implements IGestionEmploye {

	/**
	 * Default constructor.
	 */
	@EJB
	EmployeDAO employeDAO;
	@EJB
	ServiceDAO serviceDAO;

	public GestionEmploye() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employe> listeEmployes() {
		return employeDAO.findAll();
	}

	@Override
	public Employe lireEmploye(int identifiant) {
		return employeDAO.findById(identifiant);
	}


	@Override
	public Employe creerEmploye(String nom, String prenom, int serviceId, String statut, Date date_debut, Date date_fin) {
		Service service = serviceDAO.findById(serviceId);
		Employe employe = new Employe();
		employe.setNom(nom);
		employe.setPrenom(prenom);
		employe.setService(service);
		employe.setStatut(statut);
		employe.setDateDebut(date_debut);
		employe.setDateFin(date_fin);
		service.getEmployes().add(employe);
		employeDAO.create(employe);
		serviceDAO.update(service);
		return employe;
	}

}
