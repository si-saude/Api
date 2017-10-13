package br.com.saude.api.model.business.validate;

import br.com.saude.api.generic.GenericValidator;
import br.com.saude.api.model.entity.po.Instalacao;

public class InstalacaoValidator extends GenericValidator<Instalacao> {

	@Override
	public void validate(Instalacao instalacao) throws Exception {		
		
		super.validate(instalacao);
		
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
