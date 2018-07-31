package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Cat;

public class CatDao extends GenericDao<Cat> {

	private static CatDao instance;
	
	private CatDao() {
		super();
	}
	
	public static CatDao getInstance() {
		if(instance==null)
			instance = new CatDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoad = cat -> {
			cat = loadEmpregado(cat);
			return cat;
		};
		
		this.functionLoadAll = cat -> {
			cat = this.functionLoad.apply(cat);
			cat = loadDiagnostico(cat);
			cat = loadEmpresa(cat);
			cat = loadGerencia(cat);
			cat = loadParteCorpoAtingida(cat);
			cat = loadAgenteCausador(cat);
			cat = loadNaturezaLesao(cat);
			cat = loadBase(cat);
			
			return cat;
		};
	}
	
	public PagedList<Cat> getListFunctionLoad(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoad);
	}
	
	public Cat getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	private Cat loadEmpregado(Cat cat) {
		if(cat.getEmpregado()!=null)
			Hibernate.initialize(cat.getEmpregado());
		return cat;
	}
	
	private Cat loadGerencia(Cat cat) {
		if(cat.getGerencia()!=null)
			Hibernate.initialize(cat.getGerencia());
		return cat;
	}
	
	private Cat loadDiagnostico(Cat cat) {
		if(cat.getDiagnostico()!=null)
			Hibernate.initialize(cat.getDiagnostico());
		return cat;
	}
	
	private Cat loadEmpresa(Cat cat) {
		if(cat.getEmpresa()!=null)
			Hibernate.initialize(cat.getEmpresa());
		return cat;
	}
	
	private Cat loadParteCorpoAtingida(Cat cat) {
		if(cat.getParteCorpoAtingida()!=null)
			Hibernate.initialize(cat.getParteCorpoAtingida());
		return cat;
	}
	
	private Cat loadAgenteCausador(Cat cat) {
		if(cat.getAgenteCausador()!=null)
			Hibernate.initialize(cat.getAgenteCausador());
		return cat;
	}
	
	private Cat loadNaturezaLesao(Cat cat) {
		if(cat.getNaturezaLesao()!=null)
			Hibernate.initialize(cat.getNaturezaLesao());
		return cat;
	}
	
	private Cat loadBase(Cat cat) {
		if (cat.getBase() != null)
			Hibernate.initialize(cat.getBase());
		return cat;
	}

}
