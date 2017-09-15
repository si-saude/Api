package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Perfil;

public class PerfilValidator extends GenericValidator{

	@Override
	public void validate(Object entity) throws Exception {
		Perfil perfil = (Perfil)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Perfil>> perfilViolation = factory.getValidator().validate(perfil);
		
		if(perfilViolation.size() > 0)
			throw new Exception(perfilViolation.iterator().next().getMessage());
		
		new PermissaoValidator().validate(perfil.getPermissoes());
	}
}
