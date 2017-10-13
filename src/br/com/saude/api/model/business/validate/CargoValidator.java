package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Cargo;

public class CargoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Cargo cargo = (Cargo)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Cargo>> cargoViolation = factory.getValidator().validate(cargo);
		
		if(cargoViolation.size() > 0)
			throw new Exception(cargoViolation.iterator().next().getMessage());
	}

}
