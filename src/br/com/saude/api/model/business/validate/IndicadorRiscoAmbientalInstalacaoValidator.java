package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.IndicadorRiscoAmbientalInstalacao;

public class IndicadorRiscoAmbientalInstalacaoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		IndicadorRiscoAmbientalInstalacao indicador = (IndicadorRiscoAmbientalInstalacao)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<IndicadorRiscoAmbientalInstalacao>>
			indicadorViolation = factory.getValidator().validate(indicador);
		
		if(indicadorViolation.size() > 0)
			throw new Exception(indicadorViolation.iterator().next().getMessage());
	}

}
