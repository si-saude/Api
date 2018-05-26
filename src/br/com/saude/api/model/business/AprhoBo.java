package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.AprhoBuilder;
import br.com.saude.api.model.creation.builder.example.AprhoExampleBuilder;
import br.com.saude.api.model.entity.filter.AprhoFilter;
import br.com.saude.api.model.entity.po.Aprho;
import br.com.saude.api.model.persistence.AprhoDao;

public class AprhoBo extends GenericBo<Aprho, AprhoFilter, AprhoDao, AprhoBuilder, AprhoExampleBuilder> {

	private static AprhoBo instance;

	private AprhoBo() {
		super();
	}

	public static AprhoBo getInstance() {
		if (instance == null)
			instance = new AprhoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}
	
}
