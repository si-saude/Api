package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AgenteCausadorFilter;
import br.com.saude.api.model.entity.po.AgenteCausador;

public class AgenteCausadorBuilder extends GenericEntityBuilder<AgenteCausador, AgenteCausadorFilter> {

	private AgenteCausadorBuilder(List<AgenteCausador> agenteCausadors) {
		super(agenteCausadors);
	}

	private AgenteCausadorBuilder(AgenteCausador agenteCausador) {
		super(agenteCausador);
	}

	public static AgenteCausadorBuilder newInstance(AgenteCausador agenteCausador) {
		return new AgenteCausadorBuilder(agenteCausador);
	}
	
	public static AgenteCausadorBuilder newInstance(List<AgenteCausador> agenteCausadors) {
		return new AgenteCausadorBuilder(agenteCausadors);
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	@Override
	protected AgenteCausador clone(AgenteCausador agenteCausador) {
		
		AgenteCausador newAgenteCausador = new AgenteCausador();
		newAgenteCausador.setId(agenteCausador.getId());
		newAgenteCausador.setCodigo(agenteCausador.getCodigo());
		newAgenteCausador.setDescricao(agenteCausador.getDescricao());
		newAgenteCausador.setVersion(agenteCausador.getVersion());
		
		return newAgenteCausador;
	}

	@Override
	public AgenteCausador cloneFromFilter(AgenteCausadorFilter filter) {
		return null;
	}

}
