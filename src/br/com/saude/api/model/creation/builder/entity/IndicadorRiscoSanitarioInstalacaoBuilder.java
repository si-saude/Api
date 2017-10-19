package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoSanitarioInstalacao;

public class IndicadorRiscoSanitarioInstalacaoBuilder
		extends GenericEntityBuilder<IndicadorRiscoSanitarioInstalacao,GenericFilter>{

	public static IndicadorRiscoSanitarioInstalacaoBuilder newInstance(List<IndicadorRiscoSanitarioInstalacao> entityList) {
		return new IndicadorRiscoSanitarioInstalacaoBuilder(entityList);
	}
	
	public static IndicadorRiscoSanitarioInstalacaoBuilder newInstance(IndicadorRiscoSanitarioInstalacao entity) {
		return new IndicadorRiscoSanitarioInstalacaoBuilder(entity);
	}
	
	private IndicadorRiscoSanitarioInstalacaoBuilder(List<IndicadorRiscoSanitarioInstalacao> entityList) {
		super(entityList);
	}

	private IndicadorRiscoSanitarioInstalacaoBuilder(IndicadorRiscoSanitarioInstalacao entity) {
		super(entity);
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected IndicadorRiscoSanitarioInstalacao clone(IndicadorRiscoSanitarioInstalacao entity) {
		IndicadorRiscoSanitarioInstalacao newEntity = new IndicadorRiscoSanitarioInstalacao();
		
		newEntity.setId(entity.getId());
		newEntity.setAvaliacao(entity.getAvaliacao());
		newEntity.setDataInspecao(entity.getDataInspecao());
		newEntity.setVersion(entity.getVersion());
		
		if(entity.getIndicadorRisco() != null)
			newEntity.setIndicadorRisco(IndicadorRiscoSanitarioBuilder.newInstance(entity.getIndicadorRisco()).getEntity());
			
		return newEntity;
	}

	@Override
	public IndicadorRiscoSanitarioInstalacao cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
