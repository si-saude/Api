package br.com.saude.api.model.persistence;

import java.util.List;
import java.util.function.Function;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.Servico;

public class FilaAtendimentoOcupacionalDao extends GenericDao<FilaAtendimentoOcupacional> {

	private Function<FilaAtendimentoOcupacional,FilaAtendimentoOcupacional> loadAtualizacoes;
	
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
	@SuppressWarnings("unchecked")
	protected void initializeFunctions() {
		this.functionLoad = fila -> {
			if(fila.getProfissional().getEquipe() != null)
				Hibernate.initialize(fila.getProfissional().getEquipe());
			
			if(fila.getProfissional().getServicos() != null)
				fila.getProfissional()
					.setServicos((List<Servico>) Hibernate.unproxy(fila.getProfissional().getServicos()));
			
			return fila;
		};
		
		this.functionBeforeSave = pair -> {
			FilaAtendimentoOcupacional fila = pair.getValue0();
			
			if (fila.getAtualizacoes() != null)
				fila.getAtualizacoes().forEach(a -> a.setFila(fila));
			
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
		
		this.loadAtualizacoes = fila -> {
			if(fila.getProfissional().getEquipe() != null)
				Hibernate.initialize(fila.getProfissional().getEquipe());
			
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
		return super.getList(exampleBuilder,this.loadAtualizacoes);
	}
}
