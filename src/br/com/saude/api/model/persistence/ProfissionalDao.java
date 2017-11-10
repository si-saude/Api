package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Curriculo;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.ProfissionalConselho;

public class ProfissionalDao extends GenericDao<Profissional> {

	private static ProfissionalDao instance;
	
	private ProfissionalDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = profissional -> {
			profissional = loadEquipe(profissional);
			profissional = loadLocalizacao(profissional);
			profissional = loadEmpregado(profissional);
			return profissional;
		};
		
		this.functionLoadAll = profissional -> {
			profissional = this.functionLoad.apply(profissional);
			profissional = loadCurriculo(profissional);
			profissional = loadProfissionalConselho(profissional);
			return profissional;
		};
		
		this.functionBeforeSave = pair -> {
			Profissional profissional = pair.getValue0();
			Session session = pair.getValue1();
			
			profissional.setEmpregado(session.get(Empregado.class, profissional.getEmpregado().getId()));
		
			return profissional;
		};
	}
	
	public static ProfissionalDao getInstance() {
		if(instance == null)
			instance = new ProfissionalDao();
		return instance;
	}
	
	public Profissional getByIdLoadAll(Object id) throws Exception {
		return this.getById(id, this.functionLoadAll);
	}
	
	public PagedList<Profissional> 
		getListLoadEquipeLocalizacaoFuncao(GenericExampleBuilder<?,?> exampleBuilder) throws Exception{
		return this.getList(exampleBuilder, this.functionLoad);
	}
	
	private Profissional loadEmpregado(Profissional profissional) {
		if(profissional.getEmpregado()!=null)
			Hibernate.initialize(profissional.getEmpregado());
		return profissional;
	}
	
	private Profissional loadCurriculo(Profissional profissional) {
		if(profissional.getCurriculo()!=null) {
			profissional.setCurriculo((Curriculo)Hibernate.unproxy(profissional.getCurriculo()));
			Hibernate.initialize(profissional.getCurriculo().getCurriculoCursos());
		}
		return profissional;
	}
	
	private Profissional loadProfissionalConselho(Profissional profissional) {
		if(profissional.getProfissionalConselho()!=null)
			profissional.setProfissionalConselho((ProfissionalConselho)Hibernate.unproxy(profissional.getProfissionalConselho()));
		return profissional;
	}
	
	private Profissional loadEquipe(Profissional profissional) {
		if(profissional.getEquipe()!=null)
			Hibernate.initialize(profissional.getEquipe());
		return profissional;
	}
	
	private Profissional loadLocalizacao(Profissional profissional) {
		if(profissional.getLocalizacao()!=null)
			Hibernate.initialize(profissional.getLocalizacao());
		return profissional;
	}
	
	public PagedList<Profissional> getListFunctionLoad(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoad);
	}
}
