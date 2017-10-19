package br.com.saude.api.model.persistence;

import java.util.function.Function;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.javatuples.Pair;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Criterio;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.entity.po.GrupoMonitoramentoExame;

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
			grupoMonitoramento = loadGrupoMonitoramentoExames(grupoMonitoramento);
			grupoMonitoramento = loadEmpregados(grupoMonitoramento);
			return grupoMonitoramento;
		};
		
		this.functionBeforeSave = pair -> {
			GrupoMonitoramento grupoMonitoramento = pair.getValue0();
			Session session = pair.getValue1();
			
			if(grupoMonitoramento.getGrupoMonitoramentoExames() != null)
				for(int i=0; i < grupoMonitoramento.getGrupoMonitoramentoExames().size(); i++){
					GrupoMonitoramentoExame grupoMonitoramentoExame = 
							grupoMonitoramento.getGrupoMonitoramentoExames().get(i);
					
					//CARREGAR OS EXAMES
					grupoMonitoramentoExame.setExame(
							session.get(Exame.class,grupoMonitoramentoExame.getExame().getId()));
					
					//CARREGAR OS CRITÉRIOS
					if(grupoMonitoramentoExame.getCriterios() != null) {
						for(int j=0; j < grupoMonitoramentoExame.getCriterios().size(); j++) {
							grupoMonitoramentoExame.getCriterios().set(j, 
									session.get(Criterio.class, grupoMonitoramentoExame.getCriterios().get(j).getId()));
						}
					}
				}
			
			return grupoMonitoramento;
		};
	}
	
	public GrupoMonitoramento getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<GrupoMonitoramento> getListFunctionLoad(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
	private GrupoMonitoramento loadTipoGrupoMonitoramento(GrupoMonitoramento grupoMonitoramento) {
		if(grupoMonitoramento.getTipoGrupoMonitoramento() != null)
			Hibernate.initialize(grupoMonitoramento.getTipoGrupoMonitoramento());
		return grupoMonitoramento;
	}
	
	private GrupoMonitoramento loadGrupoMonitoramentoExames(GrupoMonitoramento grupoMonitoramento) {
		if(grupoMonitoramento.getGrupoMonitoramentoExames() != null)
			Hibernate.initialize(grupoMonitoramento.getGrupoMonitoramentoExames());
		return grupoMonitoramento;
	}
	
	private GrupoMonitoramento loadEmpregados(GrupoMonitoramento grupoMonitoramento) {
		if(grupoMonitoramento.getEmpregados() != null)
			Hibernate.initialize(grupoMonitoramento.getEmpregados());
		return grupoMonitoramento;
	}
	
	protected Function<Pair<GrupoMonitoramento,Session>,GrupoMonitoramento> getFunctionBeforeSave(){
		return this.functionBeforeSave;
	}
}
