package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.RegraAtendimentoEquipeRequisito;

public class RegraAtendimentoEquipeRequisitoBuilder
	extends GenericEntityBuilder<RegraAtendimentoEquipeRequisito, GenericFilter>{

	public static RegraAtendimentoEquipeRequisitoBuilder 
		newInstance(RegraAtendimentoEquipeRequisito requisito) {
			return new RegraAtendimentoEquipeRequisitoBuilder(requisito);
	}
	
	public static RegraAtendimentoEquipeRequisitoBuilder 
		newInstance(List<RegraAtendimentoEquipeRequisito> requisitos) {
			return new RegraAtendimentoEquipeRequisitoBuilder(requisitos);
	}
	
	private RegraAtendimentoEquipeRequisitoBuilder(RegraAtendimentoEquipeRequisito requisito) {
		super(requisito);
	}
	
	private RegraAtendimentoEquipeRequisitoBuilder(List<RegraAtendimentoEquipeRequisito> requisitos) {
		super(requisitos);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected RegraAtendimentoEquipeRequisito clone(RegraAtendimentoEquipeRequisito requisito) {
		RegraAtendimentoEquipeRequisito newRequisito = new RegraAtendimentoEquipeRequisito();
		
		newRequisito.setId(requisito.getId());
		newRequisito.setVersion(requisito.getVersion());
		
		if(requisito.getEquipe() != null)
			newRequisito.setEquipe(EquipeBuilder.newInstance(requisito.getEquipe()).getEntity());
		
		return newRequisito;
	}

	@Override
	public RegraAtendimentoEquipeRequisito cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
