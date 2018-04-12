package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.AcaoBuilder;
import br.com.saude.api.model.creation.builder.example.AcaoExampleBuilder;
import br.com.saude.api.model.entity.filter.AcaoFilter;
import br.com.saude.api.model.entity.po.Acao;
import br.com.saude.api.model.persistence.AcaoDao;

public class AcaoBo extends GenericBo<Acao, AcaoFilter, AcaoDao, AcaoBuilder, AcaoExampleBuilder> {

	private static AcaoBo instance;

	private AcaoBo() {
		super();
	}

	public static AcaoBo getInstance() {
		if (instance == null)
			instance = new AcaoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

}
