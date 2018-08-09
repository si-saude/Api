package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.PossivelDanoSaudeFilter;
import br.com.saude.api.model.entity.po.PossivelDanoSaude;

public class PossivelDanoSaudeExampleBuilder extends GenericExampleBuilder<PossivelDanoSaude, PossivelDanoSaudeFilter> {

	public static PossivelDanoSaudeExampleBuilder newInstance(PossivelDanoSaudeFilter filter) {
		return new PossivelDanoSaudeExampleBuilder(filter);
	}
	
	private PossivelDanoSaudeExampleBuilder(PossivelDanoSaudeFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addDescricao();
	}

	@Override
	protected void createExampleSelectList() {
		
	}	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}

}
