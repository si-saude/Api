package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Demanda;

public class DemandaBuilder extends GenericEntityBuilder<Demanda, GenericFilter> {

	public static DemandaBuilder newInstance(Demanda demanda) {
		return new DemandaBuilder(demanda);
	}
	
	public static DemandaBuilder newInstance(List<Demanda> demandas) {
		return new DemandaBuilder(demandas);
	}
	
	private DemandaBuilder(Demanda demanda) {
		super(demanda);
	}
	
	private DemandaBuilder(List<Demanda> demandas) {
		super(demandas);
	}

	@Override
	protected void initializeFunctions() {}

	@Override
	protected Demanda clone(Demanda demanda) {
		Demanda newDemanda = new Demanda();
		
		newDemanda.setId(demanda.getId());
		newDemanda.setTempoMedio(demanda.getTempoMedio());
		newDemanda.setVersion(demanda.getVersion());
		newDemanda.setEquipe(demanda.getEquipe());
		
		return newDemanda;
	}
	
	@Override
	public Demanda cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
