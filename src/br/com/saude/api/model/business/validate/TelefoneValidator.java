package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Telefone;

public class TelefoneValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Telefone telefone = (Telefone)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Telefone>> telefoneViolation = factory.getValidator().validate(telefone);
		
		if(telefoneViolation.size() > 0)
			throw new Exception(telefoneViolation.iterator().next().getMessage());
	}

}
