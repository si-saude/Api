package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.IndicadorConhecimentoAlimentarBuilder;
import br.com.saude.api.model.creation.builder.example.IndicadorConhecimentoAlimentarExampleBuilder;
import br.com.saude.api.model.entity.filter.IndicadorConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.po.IndicadorConhecimentoAlimentar;
import br.com.saude.api.model.persistence.IndicadorConhecimentoAlimentarDao;

public class IndicadorConhecimentoAlimentarBo  
	extends GenericBo<IndicadorConhecimentoAlimentar, IndicadorConhecimentoAlimentarFilter, 
		IndicadorConhecimentoAlimentarDao, IndicadorConhecimentoAlimentarBuilder, IndicadorConhecimentoAlimentarExampleBuilder> {

	private static IndicadorConhecimentoAlimentarBo instance;

	private IndicadorConhecimentoAlimentarBo() {
		super();
	}

	public static IndicadorConhecimentoAlimentarBo getInstance() {
		if (instance == null)
			instance = new IndicadorConhecimentoAlimentarBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadItemIndicadorConhecimentoAlimentares();
		};
	}

	@Override
	public IndicadorConhecimentoAlimentar getById(Object id) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		return super.getById(id, functionLoadAll);
	}

	@Override
	public IndicadorConhecimentoAlimentar save(IndicadorConhecimentoAlimentar entity) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Exception {
		
		entity.getItemIndicadorConhecimentoAlimentares().forEach(iica -> iica.setIndicadorConhecimentoAlimentar(entity));
		
		return super.save(entity);
	}
	
}
