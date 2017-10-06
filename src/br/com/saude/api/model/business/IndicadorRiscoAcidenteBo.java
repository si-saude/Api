package br.com.saude.api.model.business;

import br.com.saude.api.model.creation.builder.entity.IndicadorRiscoAcidenteBuilder;
import br.com.saude.api.model.creation.builder.example.IndicadorRiscoAcidenteExampleBuilder;
import br.com.saude.api.model.entity.filter.IndicadorRiscoFilter;
import br.com.saude.api.model.entity.po.IndicadorRiscoAcidente;
import br.com.saude.api.model.persistence.IndicadorRiscoAcidenteDao;

public class IndicadorRiscoAcidenteBo extends IndicadorRiscoBo<IndicadorRiscoAcidente, 
					IndicadorRiscoFilter, IndicadorRiscoAcidenteDao, 
					IndicadorRiscoAcidenteBuilder, IndicadorRiscoAcidenteExampleBuilder> {

	private static IndicadorRiscoAcidenteBo instance;

	private IndicadorRiscoAcidenteBo() {
		super();
	}


	public static IndicadorRiscoAcidenteBo getInstance() {
		if(instance==null)
			instance = new IndicadorRiscoAcidenteBo();
		return instance;
	}
}