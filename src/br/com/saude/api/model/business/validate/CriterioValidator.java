package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Criterio;

public class CriterioValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Criterio criterio = (Criterio)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Criterio>> criterioViolation = factory.getValidator().validate(criterio);
		
		if(criterioViolation.size() > 0)
			throw new Exception(criterioViolation.iterator().next().getMessage());
	}

}
