package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Exame;

public class ExameValidator extends GenericValidator{

	@Override
	public void validate(Object entity) throws Exception {
		Exame exame = (Exame)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Exame>> exameViolation = factory.getValidator().validate(exame);
		
		if(exameViolation.size() > 0)
			throw new Exception(exameViolation.iterator().next().getMessage());
	}
}
