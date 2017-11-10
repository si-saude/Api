package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Profissional;

public class ProfissionalValidator extends GenericValidator<Profissional> {

	@Override
	public void validate(Profissional profissional) throws Exception {
		
		super.validate(profissional);
		
		if(profissional.getCurriculo() != null)
			new CurriculoValidator().validate(profissional.getCurriculo());
		
		if(profissional.getProfissionalConselho() != null)
			new ProfissionalConselhoValidator().validate(profissional.getProfissionalConselho());
	}

}
