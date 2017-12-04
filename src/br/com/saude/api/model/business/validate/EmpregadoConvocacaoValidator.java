package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;

public class EmpregadoConvocacaoValidator extends GenericValidator<EmpregadoConvocacao> {

	@Override
	public void validate(EmpregadoConvocacao eC) throws Exception {
		super.validate(eC);
		
		if(eC.getEmpregadoConvocacaoExames() != null)
			new EmpregadoConvocacaoExameValidator().validate(eC.getEmpregadoConvocacaoExames());
	}
}
