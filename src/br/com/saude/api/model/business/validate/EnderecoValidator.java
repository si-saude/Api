package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Endereco;

public class EnderecoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Endereco endereco = (Endereco)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Endereco>> enderecoViolation = factory.getValidator().validate(endereco);
		
		if(enderecoViolation.size() > 0)
			throw new Exception(enderecoViolation.iterator().next().getMessage());
	}

}
