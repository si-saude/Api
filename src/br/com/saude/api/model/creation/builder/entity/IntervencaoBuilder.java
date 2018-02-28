package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.IntervencaoFilter;
import br.com.saude.api.model.entity.po.Intervencao;

public class IntervencaoBuilder extends GenericEntityBuilder<Intervencao,IntervencaoFilter> {
	public static IntervencaoBuilder newInstance(Intervencao intervencao) {
		return new IntervencaoBuilder(intervencao);
	}
	
	public static IntervencaoBuilder newInstance(List<Intervencao> intervencoes) {
		return new IntervencaoBuilder(intervencoes);
	}
	
	private IntervencaoBuilder(List<Intervencao> intervencoes) {
		super(intervencoes);
	}

	private IntervencaoBuilder(Intervencao intervencao) {
		super(intervencao);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Intervencao clone(Intervencao intervencao) {
		Intervencao cloneIntervencao = new Intervencao();
		
		cloneIntervencao.setId(intervencao.getId());
		cloneIntervencao.setVersion(intervencao.getVersion());
		cloneIntervencao.setInativo(intervencao.isInativo());
		cloneIntervencao.setDescricao(intervencao.getDescricao());
		if ( intervencao.getEquipe() != null )
			cloneIntervencao.setEquipe(EquipeBuilder.newInstance(intervencao.getEquipe()).getEntity());
		
		return cloneIntervencao;
	}

	@Override
	public Intervencao cloneFromFilter(IntervencaoFilter filter) {
		return null;
	}
	
}