package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Funcao;

public class FuncaoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Funcao funcao = (Funcao)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Funcao>> funcaoViolation = factory.getValidator().validate(funcao);
		
		if(funcaoViolation.size() > 0)
			throw new Exception(funcaoViolation.iterator().next().getMessage());
	}

}
