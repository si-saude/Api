package br.com.saude.api.model.persistence;

import java.util.function.Function;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.filter.AtestadoFilter;
import br.com.saude.api.model.entity.po.Atestado;

public class AtestadoDao extends GenericDao<Atestado> {
	
	protected Function<Atestado,Atestado> functionLoadRegime;

	private static AtestadoDao instance;
	
	private AtestadoDao() {
		super();
	}
	
	public static AtestadoDao getInstance() {
		if(instance==null)
			instance = new AtestadoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = atestado -> {
			atestado = loadCat(atestado);
			atestado = loadProfissionalRealizouVisita(atestado);
			atestado = loadHomologacaoAtestado(atestado);
			atestado = loadRegime(atestado);
			
			return atestado;
		};
		
		this.functionLoadRegime = atestado -> {
			atestado = loadRegime(atestado);
			
			return atestado;
		};
	}
	
	private Atestado loadCat(Atestado atestado) {
		if(atestado.getCat()!=null) {
			Hibernate.initialize(atestado.getCat());
		}
		return atestado;
	}
	
	private Atestado loadProfissionalRealizouVisita(Atestado atestado) {
		if(atestado.getProfissionalRealizouVisita()!=null) {
			Hibernate.initialize(atestado.getProfissionalRealizouVisita());
		}
		return atestado;
	}
	
	private Atestado loadHomologacaoAtestado(Atestado atestado) {
		if (atestado.getHomologacaoAtestado() != null)
			Hibernate.initialize(atestado.getHomologacaoAtestado());
		return atestado;
	}
	
	private Atestado loadRegime(Atestado atestado) {
		if (atestado.getRegime() != null)
			Hibernate.initialize(atestado.getRegime());
		return atestado;
	}
	
	public Atestado getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Atestado> getListRegime(GenericExampleBuilder<Atestado,AtestadoFilter> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadRegime);
	}
}