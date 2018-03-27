package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.RiscoEmpregadoBuilder;
import br.com.saude.api.model.creation.builder.example.RiscoEmpregadoExampleBuilder;
import br.com.saude.api.model.entity.filter.RiscoEmpregadoFilter;
import br.com.saude.api.model.entity.po.RiscoEmpregado;
import br.com.saude.api.model.persistence.RiscoEmpregadoDao;

public class RiscoEmpregadoBo extends 
	GenericBo<RiscoEmpregado, RiscoEmpregadoFilter, RiscoEmpregadoDao, RiscoEmpregadoBuilder, RiscoEmpregadoExampleBuilder> {
	
	private static RiscoEmpregadoBo instance;

	private RiscoEmpregadoBo() {
		super();
	}

	public static RiscoEmpregadoBo getInstance() {
		if (instance == null)
			instance = new RiscoEmpregadoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadRiscoPotencial().loadTriagens();
		};
	}

	@Override
	public PagedList<RiscoEmpregado> getList(RiscoEmpregadoFilter filter)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.getList(filter, this.functionLoad);
	}

	

	@Override
	public RiscoEmpregado getById(Object id) throws Exception {
		return super.getById(id, this.functionLoad);
	}

	@Override
	public RiscoEmpregado save(RiscoEmpregado riscoEmpregado) throws Exception {
		riscoEmpregado.getTriagens().forEach(t -> {
			t.setRiscoEmpregado(riscoEmpregado);
		});
		
		return super.save(riscoEmpregado);
	}
	
}