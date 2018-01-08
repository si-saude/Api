package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.ItemResultadoExameBuilder;
import br.com.saude.api.model.creation.builder.example.ItemResultadoExameExampleBuilder;
import br.com.saude.api.model.entity.filter.ItemResultadoExameFilter;
import br.com.saude.api.model.entity.po.ItemResultadoExame;
import br.com.saude.api.model.persistence.ItemResultadoExameDao;

public class ItemResultadoExameBo 
	extends GenericBo<ItemResultadoExame, ItemResultadoExameFilter, ItemResultadoExameDao, ItemResultadoExameBuilder, ItemResultadoExameExampleBuilder> {

	private static ItemResultadoExameBo instance;
	
	private ItemResultadoExameBo() {
		super();
	}
	
	public static ItemResultadoExameBo getInstance() {
		if (instance == null)
			instance = new ItemResultadoExameBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadResultadoExame();
		};
	}
	
	@Override
	public ItemResultadoExame getById(Object id) throws Exception {
		ItemResultadoExame item = getByEntity(getDao().getByIdLoadAll(id), functionLoadAll); 
		return item;
	}

}
