package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.ProfissionalBuilder;
import br.com.saude.api.model.creation.builder.example.ProfissionalExampleBuilder;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.CurriculoCurso;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.Telefone;
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
			return builder.loadEquipe().loadLocalizacao().loadCargo();
		};
		
		this.functionLoadAll = builder -> {
			return builder.loadCargo().loadEndereco().loadEquipe()
						.loadLocalizacao().loadProfissionalConselho()
						.loadCurriculo().loadTelefones().loadProfissionalVacinas();
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
	
	@Override
	public Profissional save(Profissional profissional) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, Exception {

		if(profissional.getCurriculo() != null) {
			profissional.getCurriculo().setProfissional(profissional);
			
			for(CurriculoCurso curriculoCurso : profissional.getCurriculo().getCurriculoCursos())
				curriculoCurso.setCurriculo(profissional.getCurriculo());
		}

		if(profissional.getId() > 0) {
			if(profissional.getTelefones() == null)
				profissional.setTelefones(new ArrayList<Telefone>());
		}
		
		profissional.getProfissionalVacinas().forEach(p->p.setProfissional(profissional));
		
		return super.save(profissional);
	}
}
