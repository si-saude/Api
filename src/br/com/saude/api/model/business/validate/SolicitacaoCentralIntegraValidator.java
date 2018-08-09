package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.SolicitacaoCentralIntegra;

public class SolicitacaoCentralIntegraValidator extends GenericValidator<SolicitacaoCentralIntegra> {
	
	@Override
	public void validate(SolicitacaoCentralIntegra solicitacao) throws Exception {
		super.validate(solicitacao);
				
		if(solicitacao.getTarefa() != null)
			new TarefaValidator().validate(solicitacao.getTarefa());
	}

}
