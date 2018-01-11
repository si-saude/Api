package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;

public class FilaEsperaOcupacionalDao extends GenericDao<FilaEsperaOcupacional> {

	private static FilaEsperaOcupacionalDao instance;
	
	private FilaEsperaOcupacionalDao() {
		super();
	}
	
	public static FilaEsperaOcupacionalDao getInstance() {
		if(instance == null)
			instance = new FilaEsperaOcupacionalDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = fila -> {
			if(fila.getLocalizacao() != null)
				Hibernate.initialize(fila.getLocalizacao());
			return fila;
		};
	}
	
	public FilaEsperaOcupacional getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<FilaEsperaOcupacional> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
}
