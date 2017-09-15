package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Permissao;

public class PermissaoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Permissao permissao = (Permissao)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Permissao>> permissaoViolation = factory.getValidator().validate(permissao);
		
		if(permissaoViolation.size() > 0)
			throw new Exception(permissaoViolation.iterator().next().getMessage());
	}

}
