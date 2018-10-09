package br.com.saude.api.model.business;

import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.EmpresaBuilder;
import br.com.saude.api.model.creation.builder.example.EmpresaExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpresaFilter;
import br.com.saude.api.model.entity.po.Empresa;
import br.com.saude.api.model.persistence.EmpresaDao;

public class EmpresaBo extends GenericBo<Empresa, EmpresaFilter, EmpresaDao, EmpresaBuilder, EmpresaExampleBuilder> {
	
	private Function<EmpresaBuilder, EmpresaBuilder> functionLoadAll;
	
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
		this.functionLoadAll = builder -> {
			return builder.loadMunicipio().loadCnaes();
		};
	}
	
	@Override
	public Empresa getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadMunicipio(id), this.functionLoadAll);
	}

	@Override
	public Empresa save(Empresa entity) throws Exception {
		entity.getCnaes().forEach(c -> c.setEmpresa(entity));
		
		return super.save(entity);
	}
	
}