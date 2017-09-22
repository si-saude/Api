package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Gerencia;

public class GerenciaValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Gerencia gerencia = (Gerencia)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Gerencia>> gerenciaViolation = factory.getValidator().validate(gerencia);
		
		if(gerenciaViolation.size() > 0)
			throw new Exception(gerenciaViolation.iterator().next().getMessage());
	}

}
