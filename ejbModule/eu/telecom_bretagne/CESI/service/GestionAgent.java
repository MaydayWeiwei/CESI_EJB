package eu.telecom_bretagne.CESI.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.AgentDAO;
import eu.telecom_bretagne.CESI.data.dao.DepartementDAO;
import eu.telecom_bretagne.CESI.data.model.Agent;
import eu.telecom_bretagne.CESI.data.model.Departement;

/**
 * Session Bean implementation class GestionAgent
 */
@Stateless
@LocalBean
public class GestionAgent implements IGestionAgent {

	/**
	 * Default constructor.
	 */
	@EJB
	AgentDAO agentDAO;
	@EJB
	DepartementDAO departementDAO;

	public GestionAgent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Agent> listeAgents() {
		return agentDAO.findAll();
	}

	@Override
	public Agent lireAgent(int identifiant) {
		return agentDAO.findById(identifiant);
	}

	@Override
	public void modifierAgent(int identifiant, String nouveauNom) {
		Agent agent = lireAgent(identifiant);
		agent.setNom(nouveauNom);
	}

	@Override
	public void modifierAgent(int identifiant, String nouveauNom,
			int departement_id) {
		Departement departement = departementDAO.findById(departement_id);
		Agent agent = this.lireAgent(identifiant);
		Departement old_departement = agent.getDepartement();
		agent.setNom(nouveauNom);
		old_departement.getAgents().remove(agent);
		agent.setDepartement(departement);
		departement.getAgents().add(agent);
		agentDAO.update(agent);
		departementDAO.update(old_departement);
		departementDAO.update(departement);
	}

	@Override
	public Agent creerAgent(String nouveauNom, int departement_id) {
		Departement departement = departementDAO.findById(departement_id);
		Agent agent = new Agent();
		agent.setNom(nouveauNom);
		agent.setDepartement(departement);
		departement.getAgents().add(agent);
		agentDAO.create(agent);
		departementDAO.update(departement);
		return agent;
	}

	@Override
	public void supprimerAgent(int identifiant) {
		Agent agent = agentDAO.findById(identifiant);
		Departement departement_agent = agent.getDepartement();
		departement_agent.getAgents().remove(agent);
		agentDAO.delete(agent);
		departementDAO.update(departement_agent);
	}

}
