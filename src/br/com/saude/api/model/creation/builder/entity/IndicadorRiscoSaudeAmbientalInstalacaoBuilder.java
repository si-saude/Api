package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoSaudeAmbiental;
import br.com.saude.api.model.entity.po.IndicadorRiscoSaudeAmbientalInstalacao;

public class IndicadorRiscoSaudeAmbientalInstalacaoBuilder
extends GenericEntityBuilder<IndicadorRiscoSaudeAmbientalInstalacao,GenericFilter>{

	public static IndicadorRiscoSaudeAmbientalInstalacaoBuilder
			newInstance(IndicadorRiscoSaudeAmbientalInstalacao entity) {
		return new IndicadorRiscoSaudeAmbientalInstalacaoBuilder(entity);
	}
	
	public static IndicadorRiscoSaudeAmbientalInstalacaoBuilder
			newInstance(List<IndicadorRiscoSaudeAmbientalInstalacao> entityList) {
		return new IndicadorRiscoSaudeAmbientalInstalacaoBuilder(entityList);
	}
	
	private IndicadorRiscoSaudeAmbientalInstalacaoBuilder(List<IndicadorRiscoSaudeAmbientalInstalacao> entityList) {
		super(entityList);
	}

	private IndicadorRiscoSaudeAmbientalInstalacaoBuilder(IndicadorRiscoSaudeAmbientalInstalacao entity) {
		super(entity);
	}

	@Override
	protected IndicadorRiscoSaudeAmbientalInstalacao clone(IndicadorRiscoSaudeAmbientalInstalacao entity) {
		IndicadorRiscoSaudeAmbientalInstalacao newEntity = new IndicadorRiscoSaudeAmbientalInstalacao();
		
		newEntity.setId(entity.getId());
		newEntity.setAvaliacao(entity.getAvaliacao());
		newEntity.setDataInspecao(entity.getDataInspecao());
		newEntity.setVersion(entity.getVersion());
		
		if(entity.getIndicadorRisco() != null)
			newEntity.setIndicadorRisco((IndicadorRiscoSaudeAmbiental)IndicadorRiscoSaudeAmbientalBuilder
					.newInstance(entity.getIndicadorRisco()).loadPeriodicidade().getEntity());
		
		return newEntity;
	}

	@Override
	public IndicadorRiscoSaudeAmbientalInstalacao cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
