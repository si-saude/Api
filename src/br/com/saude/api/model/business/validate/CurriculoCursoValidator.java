package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.CurriculoCurso;

public class CurriculoCursoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		CurriculoCurso curriculoCurso = (CurriculoCurso)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<CurriculoCurso>>curriculoCursoViolation = factory.getValidator().validate(curriculoCurso);
		
		if(curriculoCursoViolation.size() > 0)
			throw new Exception(curriculoCursoViolation.iterator().next().getMessage());		
	}

}
