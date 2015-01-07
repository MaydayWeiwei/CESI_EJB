package eu.telecom_bretagne.CESI.data.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import eu.telecom_bretagne.CESI.data.dto.AgentDepartement;
import eu.telecom_bretagne.CESI.data.model.Agent;
import eu.telecom_bretagne.CESI.data.model.Departement;

@Stateless
@LocalBean
public class AgentDepartementDAO implements DAO<AgentDepartement> {
	@PersistenceContext
	EntityManager entityManager;

	public AgentDepartementDAO() {
	}

	@Override
	public AgentDepartement create(AgentDepartement entity) {
		return null;
	}

	public AgentDepartement create(String agent_nom, int departement_id) {
		Departement departement = entityManager.find(Departement.class,
				departement_id);
		if (departement == null)
			throw new IllegalArgumentException();
		Agent agent = new Agent();
		agent.setDepartement(departement);
		departement.getAgents().add(agent);
		entityManager.persist(agent);
		AgentDepartement agtDept = buildAgentDepartement(agent);
		return agtDept;
	}

	@Override
	public AgentDepartement findById(final int id) {
		final Agent agent = entityManager.find(Agent.class, id);
		if (agent == null)
			return null;
		return buildAgentDepartement(agent);
	}

	@Override
	public AgentDepartement update(AgentDepartement entity) {
		final Agent agent = entityManager.find(Agent.class,
				entity.getAgent_id());
		if (agent == null)
			throw new IllegalArgumentException();
		agent.setNom(entity.getAgent_nom());
		return buildAgentDepartement(agent);
	}

	@Override
	public void delete(final AgentDepartement entity) {
		final Agent agent = entityManager.find(Agent.class,
				entity.getAgent_id());
		if (agent == null)
			throw new IllegalArgumentException();
		Departement departement = agent.getDepartement();
		departement.getAgents().remove(agent);
		entityManager.remove(agent);
	}

	/* helper methods */
	static protected AgentDepartement buildAgentDepartement(final Agent agent) {
		return new AgentDepartement(agent.getId(), agent.getNom(), agent
				.getDepartement().getId(), agent.getDepartement().getIntitule());
	}
}
