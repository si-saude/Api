package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Periodicidade;

public class PeriodicidadeValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Periodicidade periodicidade = (Periodicidade)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Periodicidade>> periodicidadeViolation = factory.getValidator().validate(periodicidade);
		
		if(periodicidadeViolation.size() > 0)
			throw new Exception(periodicidadeViolation.iterator().next().getMessage());
	}
}
