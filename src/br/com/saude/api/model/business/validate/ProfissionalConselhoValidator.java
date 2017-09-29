package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.ProfissionalConselho;

public class ProfissionalConselhoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		ProfissionalConselho profissionalConselho = (ProfissionalConselho)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<ProfissionalConselho>> profissionalConselhoViolation = 
				factory.getValidator().validate(profissionalConselho);
		
		if(profissionalConselhoViolation.size() > 0)
			throw new Exception(profissionalConselhoViolation.iterator().next().getMessage());
	}

}
