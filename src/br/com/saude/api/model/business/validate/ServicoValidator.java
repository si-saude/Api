package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Servico;

public class ServicoValidator extends GenericValidator<Servico> {

	@Override
	public void validate(Servico servico) throws Exception {
		super.validate(servico);
		
		if(servico.getAtividades() != null)
			new AtividadeValidator().validate(servico.getAtividades());
	}
}
