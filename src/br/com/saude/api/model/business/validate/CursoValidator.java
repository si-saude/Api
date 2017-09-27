package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Curso;

public class CursoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Curso curso = (Curso)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Curso>> cursoViolation = factory.getValidator().validate(curso);
		
		if(cursoViolation.size() > 0)
			throw new Exception(cursoViolation.iterator().next().getMessage());
	}

}
