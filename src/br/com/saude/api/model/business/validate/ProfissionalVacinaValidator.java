package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.ProfissionalVacina;

public class ProfissionalVacinaValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		ProfissionalVacina profissionalVacina = (ProfissionalVacina)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<ProfissionalVacina>> profissionaVacinaViolation = 
				factory.getValidator().validate(profissionalVacina);
		
		if(profissionaVacinaViolation.size() > 0)
			throw new Exception(profissionaVacinaViolation.iterator().next().getMessage());
		
		if(profissionalVacina.getVacina() != null)
			new VacinaValidator().validate(profissionalVacina.getVacina());
	}

}
