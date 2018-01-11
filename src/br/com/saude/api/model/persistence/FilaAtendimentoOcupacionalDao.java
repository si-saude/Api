package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;

public class FilaAtendimentoOcupacionalDao extends GenericDao<FilaAtendimentoOcupacional> {

	private static FilaAtendimentoOcupacionalDao instance;
	
	private FilaAtendimentoOcupacionalDao() {
		super();
	}
	
	public static FilaAtendimentoOcupacionalDao getInstance() {
		if(instance == null)
			instance = new FilaAtendimentoOcupacionalDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = fila -> {
			if(fila.getProfissional().getEquipe() != null)
				Hibernate.initialize(fila.getProfissional().getEquipe());
			return fila;
		};
		
		this.functionLoadAll = fila -> {
			fila = this.functionLoad.apply(fila);
			
			if(fila.getLocalizacao() != null)
				Hibernate.initialize(fila.getLocalizacao());
			if(fila.getAtualizacoes() != null)
				Hibernate.initialize(fila.getAtualizacoes());
			return fila;
		};
	}
	
	public FilaAtendimentoOcupacional getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<FilaAtendimentoOcupacional> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadAll);
	}
	
	@Override
	public PagedList<FilaAtendimentoOcupacional> getList(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoad);
	}
}