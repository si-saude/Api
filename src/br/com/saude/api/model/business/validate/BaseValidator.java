package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Base;

public class BaseValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Base base = (Base)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Base>> baseViolation = factory.getValidator().validate(base);
		
		if(baseViolation.size() > 0)
			throw new Exception(baseViolation.iterator().next().getMessage());
	}
}
