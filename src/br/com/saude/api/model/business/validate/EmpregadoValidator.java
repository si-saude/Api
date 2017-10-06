package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Empregado;

public class EmpregadoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Empregado empregado = (Empregado)entity; 
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Empregado>> empregadoViolation = factory.getValidator().validate(empregado);
		
		if(empregadoViolation.size() > 0)
			throw new Exception(empregadoViolation.iterator().next().getMessage());
	}

}
