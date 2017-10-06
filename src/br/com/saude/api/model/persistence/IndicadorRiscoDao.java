package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.IndicadorRisco;

public abstract class IndicadorRiscoDao<T> extends GenericDao<T> {

	@SuppressWarnings("unchecked")
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = entity -> {
			IndicadorRisco indicadorRisco = (IndicadorRisco)entity; 
			if( indicadorRisco.getPeriodicidade() != null)
				Hibernate.initialize(indicadorRisco.getPeriodicidade());
			return (T) indicadorRisco;
		};
	}
	
	@Override
	public T getById(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}
}
