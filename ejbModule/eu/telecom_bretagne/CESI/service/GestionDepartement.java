package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.DepartementDAO;
import eu.telecom_bretagne.CESI.data.model.Departement;

/**
 * Session Bean implementation class GestionDepartement
 */
@Stateless
@LocalBean
public class GestionDepartement implements IGestionDepartement {
	@EJB
	DepartementDAO departementDAO;

	/**
	 * Default constructor.
	 */
	public GestionDepartement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Departement lireDepartement(int identifiant) {
		return departementDAO.findById(identifiant);
	}

	@Override
	public List<Departement> listeDepartements() {
		return departementDAO.findAll();
	}

}
