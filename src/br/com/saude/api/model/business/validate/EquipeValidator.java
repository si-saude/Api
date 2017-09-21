package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Equipe;

public class EquipeValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Equipe equipe = (Equipe)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Equipe>> equipeViolation = factory.getValidator().validate(equipe);
		
		if(equipeViolation.size() > 0)
			throw new Exception(equipeViolation.iterator().next().getMessage());
	}

}
