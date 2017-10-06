package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.IndicadorRisco;

public class IndicadorRiscoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		IndicadorRisco indicadorRisco = (IndicadorRisco)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<IndicadorRisco>> indicadorRiscoViolation = 
				factory.getValidator().validate(indicadorRisco);
		
		if(indicadorRiscoViolation.size() > 0)
			throw new Exception(indicadorRiscoViolation.iterator().next().getMessage());
		
	}

}
