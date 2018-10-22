package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.IndicadorConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.po.IndicadorConhecimentoAlimentar;

public class IndicadorConhecimentoAlimentarExampleBuilder
	extends GenericExampleBuilder<IndicadorConhecimentoAlimentar, IndicadorConhecimentoAlimentarFilter> {

	public static IndicadorConhecimentoAlimentarExampleBuilder newInstance(IndicadorConhecimentoAlimentarFilter filter) {
		return new IndicadorConhecimentoAlimentarExampleBuilder(filter);
	}
	
	private IndicadorConhecimentoAlimentarExampleBuilder(IndicadorConhecimentoAlimentarFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addEnunciado();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addEnunciado() {
		if(this.filter.getEnunciado() != null)
			this.criterions.add(Restrictions.ilike("enunciado", Helper.filterLike(this.filter.getEnunciado())));
	}
}
