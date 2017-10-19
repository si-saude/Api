package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.EmpregadoBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.persistence.EmpregadoDao;

public class EmpregadoBo extends GenericBo<Empregado, EmpregadoFilter, EmpregadoDao, 
											EmpregadoBuilder, EmpregadoExampleBuilder> {
	
	private static EmpregadoBo instance;
	
	private EmpregadoBo() {
		super();
	}
		
	public static EmpregadoBo getInstance() {
		if(instance == null)
			instance = new EmpregadoBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadCargo().loadFuncao().loadGerencia();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder).loadBase()
						.loadRegime().loadGhe().loadGhee()
						.loadInstalacoes().loadTelefones()
						.loadEmpregadoVacinas()
						.loadGrupoMonitoramentos()
						.loadHistoricoGrupoMonitoramentos();
		};
	}
	
	@Override
	public PagedList<Empregado> getList(EmpregadoFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoad(getExampleBuilder(filter).example()), this.functionLoad);
	}
	
	@Override
	public Empregado getById(Object id) throws Exception {
		return super.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public Empregado save(Empregado empregado) throws Exception {
		
		empregado.getEmpregadoVacinas().forEach(e->e.setEmpregado(empregado));
		empregado.getHistoricoGrupoMonitoramentos().forEach(h->h.setEmpregado(empregado));
		
		return super.save(empregado);
	}
}
