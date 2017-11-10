package br.com.saude.api.model.business;

import java.util.List;

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
	
	public List<Gerencia> getListNotIn(List<Integer> ids) throws Exception {
		GerenciaFilter filter = new GerenciaFilter();
		filter.setPageNumber(1);
		filter.setPageSize(Integer.MAX_VALUE);
		
		return GerenciaBuilder.newInstance(this.getDao()
					.getList(GerenciaExampleBuilder
									.newInstance(filter)
									.exampleNotIn(ids)).getList()).getEntityList();
	}
}
