package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoErgonomico;
import br.com.saude.api.model.entity.po.IndicadorRiscoErgonomicoInstalacao;

public class IndicadorRiscoErgonomicoInstalacaoBuilder
		extends GenericEntityBuilder<IndicadorRiscoErgonomicoInstalacao,GenericFilter>{

	public static IndicadorRiscoErgonomicoInstalacaoBuilder newInstance(IndicadorRiscoErgonomicoInstalacao entity) {
		return new IndicadorRiscoErgonomicoInstalacaoBuilder(entity);
	}
	
	public static IndicadorRiscoErgonomicoInstalacaoBuilder newInstance(List<IndicadorRiscoErgonomicoInstalacao> entityList) {
		return new IndicadorRiscoErgonomicoInstalacaoBuilder(entityList);
	}
	
	private IndicadorRiscoErgonomicoInstalacaoBuilder(List<IndicadorRiscoErgonomicoInstalacao> entityList) {
		super(entityList);
	}

	private IndicadorRiscoErgonomicoInstalacaoBuilder(IndicadorRiscoErgonomicoInstalacao entity) {
		super(entity);
	}

	@Override
	protected IndicadorRiscoErgonomicoInstalacao clone(IndicadorRiscoErgonomicoInstalacao entity) {
		IndicadorRiscoErgonomicoInstalacao newEntity = new IndicadorRiscoErgonomicoInstalacao();
		
		newEntity.setId(entity.getId());
		newEntity.setAvaliacao(entity.getAvaliacao());
		newEntity.setDataInspecao(entity.getDataInspecao());
		newEntity.setVersion(entity.getVersion());
		
		if(entity.getIndicadorRisco() != null)
			newEntity.setIndicadorRisco((IndicadorRiscoErgonomico)IndicadorRiscoErgonomicoBuilder
					.newInstance(entity.getIndicadorRisco()).loadPeriodicidade().getEntity());
		
		return newEntity;
	}

	@Override
	public IndicadorRiscoErgonomicoInstalacao cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
