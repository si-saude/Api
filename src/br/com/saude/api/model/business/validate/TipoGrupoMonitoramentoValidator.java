package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.TipoGrupoMonitoramento;

public class TipoGrupoMonitoramentoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		TipoGrupoMonitoramento tipo = (TipoGrupoMonitoramento)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<TipoGrupoMonitoramento>> tipoViolation = factory.getValidator().validate(tipo);
		
		if(tipoViolation.size() > 0)
			throw new Exception(tipoViolation.iterator().next().getMessage());
		
	}

}
