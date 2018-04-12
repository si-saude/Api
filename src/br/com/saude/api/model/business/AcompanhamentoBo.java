package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.AcompanhamentoBuilder;
import br.com.saude.api.model.creation.builder.example.AcompanhamentoExampleBuilder;
import br.com.saude.api.model.entity.filter.AcompanhamentoFilter;
import br.com.saude.api.model.entity.po.Acompanhamento;
import br.com.saude.api.model.persistence.AcompanhamentoDao;

public class AcompanhamentoBo extends 
	GenericBo<Acompanhamento, AcompanhamentoFilter, AcompanhamentoDao, AcompanhamentoBuilder, AcompanhamentoExampleBuilder> {

	private static AcompanhamentoBo instance;

	private AcompanhamentoBo() {
		super();
	}

	public static AcompanhamentoBo getInstance() {
		if (instance == null)
			instance = new AcompanhamentoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

}
