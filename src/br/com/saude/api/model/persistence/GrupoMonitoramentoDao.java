package br.com.saude.api.model.persistence;

import java.util.function.Function;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.javatuples.Pair;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;

public class GrupoMonitoramentoDao extends GenericDao<GrupoMonitoramento> {
	
	private static GrupoMonitoramentoDao instance;
	
	private GrupoMonitoramentoDao() {
		super();
	}
	
	public static GrupoMonitoramentoDao getInstance() {
		if(instance==null)
			instance = new GrupoMonitoramentoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = grupoMonitoramento -> {
			grupoMonitoramento = loadTipoGrupoMonitoramento(grupoMonitoramento);
			return grupoMonitoramento;
		};
		
		this.functionLoadAll = grupoMonitoramento -> {
			grupoMonitoramento = this.functionLoad.apply(grupoMonitoramento);
			grupoMonitoramento = loadEmpregados(grupoMonitoramento);
			grupoMonitoramento = loadAvaliacoes(grupoMonitoramento);
			return grupoMonitoramento;
		};
		
	}
	
	public GrupoMonitoramento getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<GrupoMonitoramento> getListFunctionLoad(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
	public PagedList<GrupoMonitoramento> getListFunctionLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadAll);
	}
	
	private GrupoMonitoramento loadTipoGrupoMonitoramento(GrupoMonitoramento grupoMonitoramento) {
		if(grupoMonitoramento.getTipoGrupoMonitoramento() != null)
			Hibernate.initialize(grupoMonitoramento.getTipoGrupoMonitoramento());
		return grupoMonitoramento;
	}
	
	private GrupoMonitoramento loadEmpregados(GrupoMonitoramento grupoMonitoramento) {
		if(grupoMonitoramento.getEmpregados() != null)
			Hibernate.initialize(grupoMonitoramento.getEmpregados());
		return grupoMonitoramento;
	}
	
	private GrupoMonitoramento loadAvaliacoes(GrupoMonitoramento grupoMonitoramento) {
		if(grupoMonitoramento.getAvaliacoes() != null)
			Hibernate.initialize(grupoMonitoramento.getAvaliacoes());
		return grupoMonitoramento;
	}
	
	protected Function<Pair<GrupoMonitoramento,Session>,GrupoMonitoramento> getFunctionBeforeSave(){
		return this.functionBeforeSave;
	}
}
