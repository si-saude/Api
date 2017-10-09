package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoAcidente;
import br.com.saude.api.model.entity.po.IndicadorRiscoAcidenteInstalacao;

public class IndicadorRiscoAcidenteInstalacaoBuilder
		extends GenericEntityBuilder<IndicadorRiscoAcidenteInstalacao,GenericFilter>{

	public IndicadorRiscoAcidenteInstalacaoBuilder newInstance(IndicadorRiscoAcidenteInstalacao entity) {
		return new IndicadorRiscoAcidenteInstalacaoBuilder(entity);
	}
	
	public IndicadorRiscoAcidenteInstalacaoBuilder newInstance(List<IndicadorRiscoAcidenteInstalacao> entityList) {
		return new IndicadorRiscoAcidenteInstalacaoBuilder(entityList);
	}
	
	private IndicadorRiscoAcidenteInstalacaoBuilder(List<IndicadorRiscoAcidenteInstalacao> entityList) {
		super(entityList);
	}

	private IndicadorRiscoAcidenteInstalacaoBuilder(IndicadorRiscoAcidenteInstalacao entity) {
		super(entity);
	}

	@Override
	protected IndicadorRiscoAcidenteInstalacao clone(IndicadorRiscoAcidenteInstalacao entity) {
		IndicadorRiscoAcidenteInstalacao newEntity = new IndicadorRiscoAcidenteInstalacao();
		
		newEntity.setId(entity.getId());
		newEntity.setAvaliacao(entity.getAvaliacao());
		newEntity.setDataInspecao(entity.getDataInspecao());
		newEntity.setVersion(entity.getVersion());
		
		if(entity.getIndicadorRisco() != null)
			newEntity.setIndicadorRisco((IndicadorRiscoAcidente)IndicadorRiscoAcidenteBuilder
					.newInstance(entity.getIndicadorRisco()).loadPeriodicidade().getEntity());
		
		return newEntity;
	}

	@Override
	public IndicadorRiscoAcidenteInstalacao cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
