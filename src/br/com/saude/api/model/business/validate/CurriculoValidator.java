package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Curriculo;

public class CurriculoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Curriculo curriculo = (Curriculo)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Curriculo>>curriculoViolation = factory.getValidator().validate(curriculo);
		
		if(curriculoViolation.size() > 0)
			throw new Exception(curriculoViolation.iterator().next().getMessage());
		
		if(curriculo.getCurriculoCursos() != null)
			new CurriculoCursoValidator().validate(curriculo.getCurriculoCursos());
	}
}
