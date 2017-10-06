package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.creation.builder.entity.IndicadorRiscoBuilder;
import br.com.saude.api.model.entity.po.IndicadorRisco;

public class IndicadorRiscoBo<	T extends IndicadorRisco, 	
								F extends GenericFilter, 
								D extends GenericDao<T>, 
								B extends GenericEntityBuilder<T,F>, 
								X extends GenericExampleBuilder<T,F>>
			extends GenericBo<T, F, D, B, X> {

	@SuppressWarnings("unchecked")
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			IndicadorRiscoBuilder<T> genericBuilder = (IndicadorRiscoBuilder<T>)builder;
			return (B) genericBuilder.loadPeriodicidade();
		};
	}
	
	@Override
	public T getById(Object id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
