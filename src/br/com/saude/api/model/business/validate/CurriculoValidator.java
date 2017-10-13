package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Curriculo;

public class CurriculoValidator extends GenericValidator<Curriculo> {

	@Override
	public void validate(Curriculo curriculo) throws Exception {
		super.validate(curriculo);
		
		if(curriculo.getCurriculoCursos() != null)
			new CurriculoCursoValidator().validate(curriculo.getCurriculoCursos());
	}
}
