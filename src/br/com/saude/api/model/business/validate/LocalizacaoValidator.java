package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Localizacao;

public class LocalizacaoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Localizacao localizacao = (Localizacao)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Localizacao>> localizacaoViolation = factory.getValidator().validate(localizacao);
		
		if(localizacaoViolation.size() > 0)
			throw new Exception(localizacaoViolation.iterator().next().getMessage());
	}

}
