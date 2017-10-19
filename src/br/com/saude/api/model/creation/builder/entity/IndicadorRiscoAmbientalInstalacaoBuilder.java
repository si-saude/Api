package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoAmbientalInstalacao;

public class IndicadorRiscoAmbientalInstalacaoBuilder
		extends GenericEntityBuilder<IndicadorRiscoAmbientalInstalacao,GenericFilter>{

	public static IndicadorRiscoAmbientalInstalacaoBuilder newInstance(IndicadorRiscoAmbientalInstalacao entity) {
		return new IndicadorRiscoAmbientalInstalacaoBuilder(entity);
	}
	
	public static IndicadorRiscoAmbientalInstalacaoBuilder newInstance(List<IndicadorRiscoAmbientalInstalacao> entityList) {
		return new IndicadorRiscoAmbientalInstalacaoBuilder(entityList);
	}
	
	private IndicadorRiscoAmbientalInstalacaoBuilder(List<IndicadorRiscoAmbientalInstalacao> entityList) {
		super(entityList);
	}

	private IndicadorRiscoAmbientalInstalacaoBuilder(IndicadorRiscoAmbientalInstalacao entity) {
		super(entity);
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected IndicadorRiscoAmbientalInstalacao clone(IndicadorRiscoAmbientalInstalacao entity) {
		IndicadorRiscoAmbientalInstalacao newEntity = new IndicadorRiscoAmbientalInstalacao();
		
		newEntity.setId(entity.getId());
		newEntity.setAvaliacao(entity.getAvaliacao());
		newEntity.setDataInspecao(entity.getDataInspecao());
		newEntity.setVersion(entity.getVersion());
		
		if(entity.getIndicadorRisco() != null)
			newEntity.setIndicadorRisco(IndicadorRiscoAmbientalBuilder.newInstance(entity.getIndicadorRisco()).getEntity());
		
		return newEntity;
	}

	@Override
	public IndicadorRiscoAmbientalInstalacao cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
