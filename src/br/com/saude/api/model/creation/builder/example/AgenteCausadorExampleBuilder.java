package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.AgenteCausadorFilter;
import br.com.saude.api.model.entity.po.AgenteCausador;

public class AgenteCausadorExampleBuilder  extends GenericExampleBuilder<AgenteCausador, AgenteCausadorFilter> {
	
	public static AgenteCausadorExampleBuilder newInstance(AgenteCausadorFilter filter) {
		return new AgenteCausadorExampleBuilder(filter);
	}
	
	private AgenteCausadorExampleBuilder(AgenteCausadorFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		 addCodigo();
		 addDescricao();
	}

	@Override
	protected void createExampleSelectList() throws InstantiationException, IllegalAccessException {
		
	}
	
	private void addCodigo() {
		if(this.filter.getCodigo()!=null)
			this.criterions.add(Restrictions.ilike("codigo", Helper.filterLike(this.filter.getCodigo())));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao()!=null)
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}
}
