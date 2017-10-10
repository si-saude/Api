package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.PeriodicidadeFilter;
import br.com.saude.api.model.entity.po.Periodicidade;

public class PeriodicidadeBuilder extends GenericEntityBuilder<Periodicidade,PeriodicidadeFilter> {

	public static PeriodicidadeBuilder newInstance(Periodicidade periodicidade) {
		return new PeriodicidadeBuilder(periodicidade);
	}
	
	public static PeriodicidadeBuilder newInstance(List<Periodicidade> periodicidades) {
		return new PeriodicidadeBuilder(periodicidades);
	}
	
	private PeriodicidadeBuilder(Periodicidade periodicidade) {
		super(periodicidade);
	}

	private PeriodicidadeBuilder(List<Periodicidade> periodicidades) {
		super(periodicidades);
	}

	@Override
	protected Periodicidade clone(Periodicidade periodicidade) {
		Periodicidade newPeriodicidade = new Periodicidade();
		
		newPeriodicidade.setId(periodicidade.getId());
		newPeriodicidade.setDescricao(periodicidade.getDescricao());
		newPeriodicidade.setMeses(periodicidade.getMeses());
		newPeriodicidade.setVersion(periodicidade.getVersion());
		
		return newPeriodicidade;
	}

	@Override
	public Periodicidade cloneFromFilter(PeriodicidadeFilter filter) {
		return null;
	}

}
