package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.GheBuilder;
import br.com.saude.api.model.creation.builder.example.GheExampleBuilder;
import br.com.saude.api.model.entity.filter.GheFilter;
import br.com.saude.api.model.entity.po.Ghe;
import br.com.saude.api.model.persistence.GheDao;

public class GheBo extends GenericBo<Ghe, GheFilter, GheDao, GheBuilder, GheExampleBuilder> {

	private static GheBo instance;
	
	private GheBo() {
		super();
	}
	
	public static GheBo getInstance() {
		if(instance==null)
			instance = new GheBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadAll();
		};
	}
	
	@Override
	public Ghe save(Ghe ghe) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		
		//GERAR DATA DE CRIAÇÃO
		if(ghe.getId() == 0)
			ghe.setDataCriacao(new Date());
		
		return super.save(ghe);
	}
	
	@Override
	public Ghe getById(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
