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
	public void modifierEmploye(int identifiant, String nouveauNom) {
		Employe employe = lireEmploye(identifiant);
		employe.setNom(nouveauNom);
	}

	@Override
	public void modifierEmploye(int identifiant, String nouveauNom,
			int departement_id) {
		Service service = serviceDAO.findById(departement_id);
		Employe employe = this.lireEmploye(identifiant);
		Service old_departement = employe.getService();
		employe.setNom(nouveauNom);
		old_departement.getEmployes().remove(employe);
		employe.setService(service);
		service.getEmployes().add(employe);
		employeDAO.update(employe);
		serviceDAO.update(old_departement);
		serviceDAO.update(service);
	}

	@Override
	public Employe creerEmploye(String nouveauNom, int departement_id) {
		Service service = serviceDAO.findById(departement_id);
		Employe employe = new Employe();
		employe.setNom(nouveauNom);
		employe.setService(service);
		service.getEmployes().add(employe);
		employeDAO.create(employe);
		serviceDAO.update(service);
		return employe;
	}

	@Override
	public void supprimerEmploye(int identifiant) {
		Employe employe = employeDAO.findById(identifiant);
		Service service_employe = employe.getService();
		service_employe.getEmployes().remove(employe);
		employeDAO.delete(employe);
		serviceDAO.update(service_employe);
	}

}
