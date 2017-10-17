package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.GheeBuilder;
import br.com.saude.api.model.creation.builder.example.GheeExampleBuilder;
import br.com.saude.api.model.entity.filter.GheeFilter;
import br.com.saude.api.model.entity.po.Ghee;
import br.com.saude.api.model.persistence.GheeDao;

public class GheeBo extends GenericBo<Ghee, GheeFilter, GheeDao, GheeBuilder, GheeExampleBuilder> {

private static GheeBo instance;
	
	private GheeBo() {
		super();
	}
	
	public static GheeBo getInstance() {
		if(instance==null)
			instance = new GheeBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	@Override
	public Ghee save(Ghee ghee) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, Exception {
		
		//GERAR DATA DE CRIAÇÃO
		if(ghee.getId() == 0)
			ghee.setDataCriacao(new Date());
		
		return super.save(ghee);
	}
}
