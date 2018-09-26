package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.EmpresaBuilder;
import br.com.saude.api.model.creation.builder.example.EmpresaExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpresaFilter;
import br.com.saude.api.model.entity.po.Empresa;
import br.com.saude.api.model.persistence.EmpresaDao;

public class EmpresaBo extends GenericBo<Empresa, EmpresaFilter, EmpresaDao, EmpresaBuilder, EmpresaExampleBuilder> {

	private static EmpresaBo instance;

	private EmpresaBo() {
		super();
	}

	public static EmpresaBo getInstance() {
		if (instance == null)
			instance = new EmpresaBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

	}

}
