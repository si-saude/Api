package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.IndicadorRiscoErgonomicoInstalacao;

public class IndicadorRiscoErgonomicoInstalacaoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		IndicadorRiscoErgonomicoInstalacao indicador = (IndicadorRiscoErgonomicoInstalacao)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<IndicadorRiscoErgonomicoInstalacao>>
			indicadorViolation = factory.getValidator().validate(indicador);
		
		if(indicadorViolation.size() > 0)
			throw new Exception(indicadorViolation.iterator().next().getMessage());
	}

}
