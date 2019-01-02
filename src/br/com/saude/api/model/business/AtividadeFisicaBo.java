package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.AtividadeFisicaBuilder;
import br.com.saude.api.model.creation.builder.example.AtividadeFisicaExampleBuilder;
import br.com.saude.api.model.entity.filter.AtividadeFisicaFilter;
import br.com.saude.api.model.entity.po.AtividadeFisica;
import br.com.saude.api.model.persistence.AtividadeFisicaDao;

public class AtividadeFisicaBo extends
		GenericBo<AtividadeFisica, AtividadeFisicaFilter, AtividadeFisicaDao, AtividadeFisicaBuilder, AtividadeFisicaExampleBuilder> {
	
	private static AtividadeFisicaBo instance;

	private AtividadeFisicaBo() {
		super();
	}

	public static AtividadeFisicaBo getInstance() {
		if (instance == null)
			instance = new AtividadeFisicaBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {}

}
