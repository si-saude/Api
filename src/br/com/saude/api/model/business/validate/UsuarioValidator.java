package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Usuario;

public class UsuarioValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Usuario usuario = (Usuario)entity;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Usuario>> usuarioViolation = factory.getValidator().validate(usuario);
		if(usuarioViolation.size() > 0)
			throw new Exception(usuarioViolation.iterator().next().getMessage());
	}

}
