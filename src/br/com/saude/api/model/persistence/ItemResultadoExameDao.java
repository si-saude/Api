package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.ItemResultadoExame;

public class ItemResultadoExameDao extends GenericDao<ItemResultadoExame> {

	private static ItemResultadoExameDao instance;
	
	private ItemResultadoExameDao() {
		super();
	}
	
	public static ItemResultadoExameDao getInstance() {
		if (instance == null)
			instance = new ItemResultadoExameDao();
		return instance;
	}
	
	private ItemResultadoExame loadResultadoExame(ItemResultadoExame item) {
		if (item.getResultadoExame()!=null) 
			Hibernate.initialize(item.getResultadoExame());
		return item;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = item -> {
			item = loadResultadoExame(item);
			return item;
		};
	}
	
	public ItemResultadoExame getByIdLoadAll(Object id) throws Exception {
		return this.getById(id, this.functionLoadAll);
	}

}
