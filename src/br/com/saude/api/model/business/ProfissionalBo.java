package br.com.saude.api.model.business;

import java.util.List;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.ProfissionalBuilder;
import br.com.saude.api.model.creation.builder.example.ProfissionalExampleBuilder;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.CurriculoCurso;
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
			return builder.loadEquipe().loadLocalizacao();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder)
						.loadProfissionalConselho()
						.loadCurriculo();
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
		Profissional profissional = getByEntity(getDao().getByIdLoadAll(id), functionLoadAll); 
		return profissional;
	}
	
	@Override
	public Profissional save(Profissional profissional) throws Exception {

		if(profissional.getCurriculo() != null) {
			profissional.getCurriculo().setProfissional(profissional);
			
			for(CurriculoCurso curriculoCurso : profissional.getCurriculo().getCurriculoCursos())
				curriculoCurso.setCurriculo(profissional.getCurriculo());
		}
		
		Profissional newProfissional = super.save(profissional);
		return newProfissional;
	}
	
	@Override
	public List<Profissional> getSelectList(ProfissionalFilter filter) throws Exception {
		return super.getSelectList(this.getDao().getListFunctionLoad(this.getExampleBuilder(filter).exampleSelectList()).getList(), this.functionLoadAll);
	}
	
}
