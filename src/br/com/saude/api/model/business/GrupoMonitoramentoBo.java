package br.com.saude.api.model.business;

import java.util.List;
import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.GrupoMonitoramentoBuilder;
import br.com.saude.api.model.creation.builder.example.GrupoMonitoramentoExampleBuilder;
import br.com.saude.api.model.entity.filter.GrupoMonitoramentoFilter;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;
import br.com.saude.api.model.persistence.GrupoMonitoramentoDao;

public class GrupoMonitoramentoBo 
		extends GenericBo<GrupoMonitoramento, GrupoMonitoramentoFilter, 
			GrupoMonitoramentoDao, GrupoMonitoramentoBuilder, GrupoMonitoramentoExampleBuilder> {

	private Function<GrupoMonitoramentoBuilder,GrupoMonitoramentoBuilder> functionLoadGrupoMonitoramentoExames;
	
	private static GrupoMonitoramentoBo instance;
	
	private GrupoMonitoramentoBo() {
		super();
	}
	
	public static GrupoMonitoramentoBo getInstance() {
		if(instance==null)
			instance = new GrupoMonitoramentoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadTipoGrupoMonitoramento();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder)
						.loadGrupoMonitoramentoExames()
						.loadEmpregados();
		};
		
		this.functionLoadGrupoMonitoramentoExames = builder -> {
			return this.functionLoad.apply(builder)
					.loadGrupoMonitoramentoExames();
	};
	}
	
	@Override
	public GrupoMonitoramento getById(Object id) throws Exception {
		return this.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public PagedList<GrupoMonitoramento> getList(GrupoMonitoramentoFilter filter) throws Exception {
		return this.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), 
				this.functionLoad);
	}
	
	public PagedList<GrupoMonitoramento> getListLoadGrupoMonitoramentoExames(GrupoMonitoramentoFilter filter) throws Exception {
		return this.getList(getDao().getListFunctionLoadGrupoMonitoramentoExames(getExampleBuilder(filter).example()), 
				this.functionLoadGrupoMonitoramentoExames);
	}
	
	@Override
	public List<GrupoMonitoramento> getSelectList(GrupoMonitoramentoFilter filter) throws  Exception {
		return this.getSelectList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()).getList(), this.functionLoad);
	}
	
	@Override
	public GrupoMonitoramento save(GrupoMonitoramento grupoMonitoramento) throws Exception {
		
		grupoMonitoramento.getGrupoMonitoramentoExames()
							.forEach(g->g.setGrupoMonitoramento(grupoMonitoramento));
		
		return super.save(grupoMonitoramento);
	}
}
