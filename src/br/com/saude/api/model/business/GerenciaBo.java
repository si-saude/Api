package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.GerenciaBuilder;
import br.com.saude.api.model.creation.builder.example.GerenciaExampleBuilder;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.po.Gerencia;
import br.com.saude.api.model.persistence.GerenciaDao;

public class GerenciaBo extends GenericBo<Gerencia, GerenciaFilter, GerenciaDao, 
								GerenciaBuilder, GerenciaExampleBuilder> {

	private static GerenciaBo instance;
	
	private GerenciaBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadGerente().loadSecretario1().loadSecretario2();
		};
	}
	
	public static GerenciaBo getInstance() {
		if(instance==null)
			instance = new GerenciaBo();
		return instance;
	}
	
	@Override
	public Gerencia getById(Object id) throws Exception {
		return this.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
}
