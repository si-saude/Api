package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Profissional;

public class ProfissionalValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Profissional profissional = (Profissional)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Profissional>> profissionalViolation = factory.getValidator().validate(profissional);
		
		if(profissionalViolation.size() > 0)
			throw new Exception(profissionalViolation.iterator().next().getMessage());
		
		if(profissional.getTelefones() != null)
			new TelefoneValidator().validate(profissional.getTelefones());
		
		if(profissional.getEndereco() != null)
			new EnderecoValidator().validate(profissional.getEndereco());
	}

}
