package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.RegraAtendimentoEquipe;

public class RegraAtendimentoEquipeValidator 
	extends GenericValidator<RegraAtendimentoEquipe> {

	@Override
	public void validate(RegraAtendimentoEquipe regraAtendimentoEquipe) throws Exception {
		super.validate(regraAtendimentoEquipe);
		
		if(regraAtendimentoEquipe.getRegraAtendimentoEquipeRequisitos() != null)
			new RegraAtendimentoEquipeRequisitoValidator()
				.validate(regraAtendimentoEquipe.getRegraAtendimentoEquipeRequisitos());
	}
}
