package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.CatBuilder;
import br.com.saude.api.model.creation.builder.example.CatExampleBuilder;
import br.com.saude.api.model.entity.filter.CatFilter;
import br.com.saude.api.model.entity.po.Cat;
import br.com.saude.api.model.persistence.CatDao;

public class CatBo extends GenericBo<Cat, CatFilter, CatDao, CatBuilder, CatExampleBuilder> {

	private static CatBo instance;

	private CatBo() {
		super();
	}

	public static CatBo getInstance() {
		if (instance == null)
			instance = new CatBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

}
