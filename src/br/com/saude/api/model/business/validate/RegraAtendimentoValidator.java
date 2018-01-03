package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.RegraAtendimento;

public class RegraAtendimentoValidator extends GenericValidator<RegraAtendimento> {

	
	@Override
	public void validate(RegraAtendimento regraAtendimento) throws Exception {
		super.validate(regraAtendimento);
		
		if(regraAtendimento.getRegraAtendimentoEquipes() != null)
			new RegraAtendimentoEquipeValidator()
				.validate(regraAtendimento.getRegraAtendimentoEquipes());
	}
}
