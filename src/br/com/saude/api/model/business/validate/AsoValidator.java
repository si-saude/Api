package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Aso;

public class AsoValidator extends GenericValidator<Aso> {

	@Override
	public void validate(Aso aso) throws Exception {
		super.validate(aso);
		
		if(aso.getAsoAlteracoes() != null)
			new AsoAlteracaoValidator().validate(aso.getAsoAlteracoes());
	}
}
