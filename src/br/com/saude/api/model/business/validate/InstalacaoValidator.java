package br.com.saude.api.model.business.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Instalacao;

public class InstalacaoValidator extends GenericValidator {

	@Override
	public void validate(Object entity) throws Exception {
		Instalacao instalacao = (Instalacao)entity;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Instalacao>> instalacaoViolation = factory.getValidator().validate(instalacao);
		
		if(instalacaoViolation.size() > 0)
			throw new Exception(instalacaoViolation.iterator().next().getMessage());
		
		if(instalacao.getIndicadorRiscoAcidenteInstalacoes() != null)
			new IndicadorRiscoAcidenteInstalacaoValidator()
				.validate(instalacao.getIndicadorRiscoAcidenteInstalacoes());
		
		if(instalacao.getIndicadorRiscoAmbientalInstalacoes() != null)
			new IndicadorRiscoAmbientalInstalacaoValidator()
				.validate(instalacao.getIndicadorRiscoAmbientalInstalacoes());
		
		if(instalacao.getIndicadorRiscoErgonomicoInstalacoes() != null)
			new IndicadorRiscoErgonomicoInstalacaoValidator()
				.validate(instalacao.getIndicadorRiscoErgonomicoInstalacoes());
		
		if(instalacao.getIndicadorRiscoSanitarioInstalacoes() != null)
			new IndicadorRiscoSanitarioInstalacaoValidator()
				.validate(instalacao.getIndicadorRiscoSanitarioInstalacoes());
		
		if(instalacao.getIndicadorRiscoSaudeAmbientalInstalacoes() != null)
			new IndicadorRiscoSaudeAmbientalInstalacaoValidator()
				.validate(instalacao.getIndicadorRiscoSaudeAmbientalInstalacoes());
	}
}
