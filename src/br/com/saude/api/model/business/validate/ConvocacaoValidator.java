package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Convocacao;

public class ConvocacaoValidator extends GenericValidator<Convocacao>{

	@Override
	public void validate(Convocacao convocacao) throws Exception {
		super.validate(convocacao);
		
		if(convocacao.getGerenciaConvocacoes() != null)
			new GerenciaConvocacaoValidator().validate(convocacao.getGerenciaConvocacoes());
		
		if(convocacao.getEmpregadoConvocacoes() != null)
			new EmpregadoConvocacaoValidator().validate(convocacao.getEmpregadoConvocacoes());
	}
}
