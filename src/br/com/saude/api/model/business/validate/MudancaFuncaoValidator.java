package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.MudancaFuncao;

public class MudancaFuncaoValidator extends GenericValidator<MudancaFuncao> {
	
	@Override
	public void validate(MudancaFuncao solicitacao) throws Exception {
		super.validate(solicitacao);
				
		if(solicitacao.getTarefas() != null)
			new TarefaValidator().validate(solicitacao.getTarefas());
	}

}
