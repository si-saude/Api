package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Ghe;

public class GheValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Ghe ghe = (Ghe)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Ghe>> gheViolation = factory.getValidator().validate(ghe);
		
		if(gheViolation.size() > 0)
			throw new Exception(gheViolation.iterator().next().getMessage());
		
	}

}
