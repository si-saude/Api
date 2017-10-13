package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.ProfissionalVacina;

public class ProfissionalVacinaValidator extends GenericValidator<ProfissionalVacina> {

	@Override
	public void validate(ProfissionalVacina profissionalVacina) throws Exception {
		
		super.validate(profissionalVacina);
		
		if(profissionalVacina.getVacina() != null)
			new VacinaValidator().validate(profissionalVacina.getVacina());
	}
}
