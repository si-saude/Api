package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.RegraAtendimentoEquipe;

public class RegraAtendimentoEquipeBuilder
	extends GenericEntityBuilder<RegraAtendimentoEquipe, GenericFilter>{

	private Function<Map<String,RegraAtendimentoEquipe>,RegraAtendimentoEquipe> loadRequisitos;
	
	public static RegraAtendimentoEquipeBuilder 
		newInstance(RegraAtendimentoEquipe regraAtendimentoEquipe) {
			return new RegraAtendimentoEquipeBuilder(regraAtendimentoEquipe);
	}
	
	public static RegraAtendimentoEquipeBuilder 
		newInstance(List<RegraAtendimentoEquipe> regraAtendimentoEquipes) {
			return new RegraAtendimentoEquipeBuilder(regraAtendimentoEquipes);
	}
	
	private RegraAtendimentoEquipeBuilder(RegraAtendimentoEquipe regraAtendimentoEquipe) {
		super(regraAtendimentoEquipe);
	}
	
	private RegraAtendimentoEquipeBuilder(List<RegraAtendimentoEquipe> regraAtendimentoEquipes) {
		super(regraAtendimentoEquipes);
	}

	@Override
	protected void initializeFunctions() {
		this.loadRequisitos = regraAtendimentoEquipes -> {
			if(regraAtendimentoEquipes.get("origem").getRegraAtendimentoEquipeRequisitos() != null) {
				regraAtendimentoEquipes.get("destino")
					.setRegraAtendimentoEquipeRequisitos(
							RegraAtendimentoEquipeRequisitoBuilder.newInstance(
									regraAtendimentoEquipes.get("origem")
									.getRegraAtendimentoEquipeRequisitos()).getEntityList());
			}
			return regraAtendimentoEquipes.get("destino");
		};
	}

	@Override
	protected RegraAtendimentoEquipe clone(RegraAtendimentoEquipe regraAtendimentoEquipe) {
		RegraAtendimentoEquipe newRegraAtendimentoEquipe = new RegraAtendimentoEquipe();
		
		newRegraAtendimentoEquipe.setId(regraAtendimentoEquipe.getId());
		newRegraAtendimentoEquipe.setAcolhimento(regraAtendimentoEquipe.isAcolhimento());
		newRegraAtendimentoEquipe.setVersion(regraAtendimentoEquipe.getVersion());
		
		if(regraAtendimentoEquipe.getEquipe() != null)
			newRegraAtendimentoEquipe.setEquipe(EquipeBuilder.
					newInstance(regraAtendimentoEquipe.getEquipe()).getEntity());
		
		return newRegraAtendimentoEquipe;
	}
	
	public RegraAtendimentoEquipeBuilder loadRequisitos() {
		return (RegraAtendimentoEquipeBuilder) this.loadProperty(this.loadRequisitos);
	}

	@Override
	public RegraAtendimentoEquipe cloneFromFilter(GenericFilter filter) {
		return null;
	}

	

}
