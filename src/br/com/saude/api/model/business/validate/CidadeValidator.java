package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Cidade;

public class CidadeValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Cidade cidade = (Cidade)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Cidade>> cidadeViolation = factory.getValidator().validate(cidade);
		
		if(cidadeViolation.size() > 0)
			throw new Exception(cidadeViolation.iterator().next().getMessage());
	}

}
