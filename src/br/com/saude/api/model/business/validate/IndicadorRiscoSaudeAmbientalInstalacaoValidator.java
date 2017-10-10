package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.IndicadorRiscoSaudeAmbientalInstalacao;

public class IndicadorRiscoSaudeAmbientalInstalacaoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		IndicadorRiscoSaudeAmbientalInstalacao indicador = (IndicadorRiscoSaudeAmbientalInstalacao)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<IndicadorRiscoSaudeAmbientalInstalacao>>
			indicadorViolation = factory.getValidator().validate(indicador);
		
		if(indicadorViolation.size() > 0)
			throw new Exception(indicadorViolation.iterator().next().getMessage());
	}

}
