package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.ProfissionalBuilder;
import br.com.saude.api.model.creation.builder.example.ProfissionalExampleBuilder;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.persistence.ProfissionalDao;

public class ProfissionalBo extends GenericBo<Profissional, ProfissionalFilter, ProfissionalDao, 
												ProfissionalBuilder, ProfissionalExampleBuilder> {
	
	private static ProfissionalBo instance;
	
	private ProfissionalBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadEquipe().loadLocalizacao().loadFuncao();
		};
		
		this.functionLoadAll = builder -> {
			return builder.loadFuncao().loadEndereco().loadEquipe().loadLocalizacao()
						.loadCurriculo().loadCurriculo().loadTelefones().loadVacinas();
		};
	}
	
	public static ProfissionalBo getInstance() {
		if(instance==null)
			instance = new ProfissionalBo();
		return instance;
	}
	
	@Override
	public PagedList<Profissional> getList(ProfissionalFilter filter) throws Exception{
		return getList(getDao().getListLoadEquipeLocalizacaoFuncao(getExampleBuilder(filter).example()), 
						functionLoad);
	}
	
	@Override
	public Profissional getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), functionLoadAll);
	}
}
