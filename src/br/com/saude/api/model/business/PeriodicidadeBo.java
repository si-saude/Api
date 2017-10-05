package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.PeriodicidadeBuilder;
import br.com.saude.api.model.creation.builder.example.PeriodicidadeExampleBuilder;
import br.com.saude.api.model.entity.filter.PeriodicidadeFilter;
import br.com.saude.api.model.entity.po.Periodicidade;
import br.com.saude.api.model.persistence.PeriodicidadeDao;

public class PeriodicidadeBo extends GenericBo<Periodicidade, PeriodicidadeFilter, PeriodicidadeDao, 
												PeriodicidadeBuilder, PeriodicidadeExampleBuilder>{

	private static PeriodicidadeBo instance;
	
	private PeriodicidadeBo() {
		super();
	}
	
	public static PeriodicidadeBo getInstance() {
		if(instance == null)
			instance = new PeriodicidadeBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

}
