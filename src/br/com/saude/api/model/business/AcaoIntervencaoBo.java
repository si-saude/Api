package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.AcaoIntervencaoBuilder;
import br.com.saude.api.model.creation.builder.example.AcaoIntervencaoExampleBuilder;
import br.com.saude.api.model.entity.filter.AcaoIntervencaoFilter;
import br.com.saude.api.model.entity.po.AcaoIntervencao;
import br.com.saude.api.model.persistence.AcaoIntervencaoDao;

public class AcaoIntervencaoBo extends GenericBo<AcaoIntervencao, AcaoIntervencaoFilter, AcaoIntervencaoDao, AcaoIntervencaoBuilder, AcaoIntervencaoExampleBuilder> {

	private static AcaoIntervencaoBo instance;

	private AcaoIntervencaoBo() {
		super();
	}

	public static AcaoIntervencaoBo getInstance() {
		if (instance == null)
			instance = new AcaoIntervencaoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadEquipe();
		};
		
		this.functionLoadAll = builder -> {
			return builder.loadEquipe();
		};
	}
	
	@Override
	public AcaoIntervencao getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}

}
