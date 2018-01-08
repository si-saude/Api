package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.Atividade;

public class AtividadeBuilder extends GenericEntityBuilder<Atividade, GenericFilter> {

	public static AtividadeBuilder newInstance(Atividade atividade) {
		return new AtividadeBuilder(atividade);
	}
	
	public static AtividadeBuilder newInstance(List<Atividade> atividades) {
		return new AtividadeBuilder(atividades);
	}
	
	private AtividadeBuilder(Atividade atividade) {
		super(atividade);
	}
	
	private AtividadeBuilder(List<Atividade> atividades) {
		super(atividades);
	}

	@Override
	protected void initializeFunctions() {}

	@Override
	protected Atividade clone(Atividade atividade) {
		Atividade newAtividade = new Atividade();
		
		newAtividade.setId(atividade.getId());
		newAtividade.setTempoMedio(atividade.getTempoMedio());
		newAtividade.setVersion(atividade.getVersion());
		
		if(atividade.getEquipe() != null)
			newAtividade.setEquipe(EquipeBuilder
					.newInstance(atividade.getEquipe()).getEntity());
		
		return newAtividade;
	}
	
	@Override
	public Atividade cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
