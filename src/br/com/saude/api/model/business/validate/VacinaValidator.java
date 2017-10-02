package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Vacina;

public class VacinaValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Vacina vacina = (Vacina)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Vacina>> vacinaViolation = factory.getValidator().validate(vacina);
		
		if(vacinaViolation.size() > 0)
			throw new Exception(vacinaViolation.iterator().next().getMessage());
	}

}
