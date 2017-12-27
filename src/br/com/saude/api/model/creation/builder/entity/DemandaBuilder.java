package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Demanda;

public class DemandaBuilder extends GenericEntityBuilder<Demanda, GenericFilter> {

	private Function<Map<String,Demanda>,Demanda> loadEquipe;
	
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
	protected void initializeFunctions() {
		this.loadEquipe = demandas -> {
			if(demandas.get("origem").getEquipe() != null) {
				demandas.get("destino").setEquipe(
						EquipeBuilder.newInstance(demandas.get("origem").getEquipe())
									.getEntity());
			}
			return demandas.get("destino");
		};
	}

	@Override
	protected Demanda clone(Demanda demanda) {
		Demanda newDemanda = new Demanda();
		
		newDemanda.setId(demanda.getId());
		newDemanda.setTempoMedio(demanda.getTempoMedio());
		newDemanda.setVersion(demanda.getVersion());
		
		return newDemanda;
	}
	
	public DemandaBuilder loadEquipe() {
		return (DemandaBuilder)this.loadProperty(this.loadEquipe);
	}

	@Override
	public Demanda cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
