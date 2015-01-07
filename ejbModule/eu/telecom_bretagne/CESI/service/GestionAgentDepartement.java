package eu.telecom_bretagne.CESI.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.AgentDepartementDAO;
import eu.telecom_bretagne.CESI.data.dto.AgentDepartement;

/**
 * Session Bean implementation class GestionAgentDepartement
 */
@Stateless
@LocalBean
public class GestionAgentDepartement implements IGestionAgentDepartement {

	/**
	 * Default constructor.
	 */
	public GestionAgentDepartement() {
		// TODO Auto-generated constructor stub
	}

	@EJB
	AgentDepartementDAO agentDepartementDAO;

	@Override
	public AgentDepartement lireAgentDepartement(int id) {
		return agentDepartementDAO.findById(id);
	}

	@Override
	public void modifierAgentDepartement(AgentDepartement agentDepartement) {
		agentDepartementDAO.update(agentDepartement);
	}

}
