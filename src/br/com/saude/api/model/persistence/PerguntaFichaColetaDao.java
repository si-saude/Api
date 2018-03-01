package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.PerguntaFichaColeta;

public class PerguntaFichaColetaDao extends GenericDao<PerguntaFichaColeta> {
	
	private static PerguntaFichaColetaDao instance;
	
	private PerguntaFichaColetaDao() {
		super();
	}
	
	public static PerguntaFichaColetaDao getInstance() {
		if(instance == null)
			instance = new PerguntaFichaColetaDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = perguntaFichaColeta -> {
			if(perguntaFichaColeta.getItens() != null)
				Hibernate.initialize(perguntaFichaColeta.getItens());
			return perguntaFichaColeta;
		};
	}
	
	public PerguntaFichaColeta getByIdLoadAll(Object id) throws Exception {
		return this.getById(id, this.functionLoadAll);
	}
	
	public PagedList<PerguntaFichaColeta> getListLoadAll(GenericExampleBuilder<?,?> perguntaExampleBuilder) throws Exception{
		return this.getList(perguntaExampleBuilder, this.functionLoadAll);
	}
}
