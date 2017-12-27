package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Atividade;

public class AtividadeBuilder extends GenericEntityBuilder<Atividade, GenericFilter> {

	public static AtividadeBuilder newInstance(Atividade demanda) {
		return new AtividadeBuilder(demanda);
	}
	
	public static AtividadeBuilder newInstance(List<Atividade> demandas) {
		return new AtividadeBuilder(demandas);
	}
	
	private AtividadeBuilder(Atividade demanda) {
		super(demanda);
	}
	
	private AtividadeBuilder(List<Atividade> demandas) {
		super(demandas);
	}

	@Override
	protected void initializeFunctions() {}

	@Override
	protected Atividade clone(Atividade demanda) {
		Atividade newAtividade = new Atividade();
		
		newAtividade.setId(demanda.getId());
		newAtividade.setTempoMedio(demanda.getTempoMedio());
		newAtividade.setVersion(demanda.getVersion());
		newAtividade.setEquipe(demanda.getEquipe());
		
		return newAtividade;
	}
	
	@Override
	public Atividade cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
