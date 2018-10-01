package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
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
		this.functionLoadAll = cat -> {
			cat = loadProfissionalCaracterizacao(cat);
			cat = loadProfissionalClassificacao(cat);
			cat = loadAgenteCausador(cat);
			cat = loadNaturezaLesao(cat);
			cat = loadParteCorpoAtingida(cat);
			cat = loadMunicipio(cat);
			cat = loadInstalacao(cat);
			cat = loadCnae(cat);
			cat = loadClassificacaoGravidade(cat);
			cat = loadDiagnosticoProvavel(cat);
			return cat;
		};
	}
	
	public Cat getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	private Cat loadProfissionalCaracterizacao(Cat cat) {
		if(cat.getProfissionalCaracterizacao()!=null)
			Hibernate.initialize(cat.getProfissionalCaracterizacao());
		return cat;
	}
	
	private Cat loadProfissionalClassificacao(Cat cat) {
		if(cat.getProfissionalClassificacao()!=null)
			Hibernate.initialize(cat.getProfissionalClassificacao());
		return cat;
	}
	
	private Cat loadAgenteCausador(Cat cat) {
		if(cat.getAgenteCausador()!=null)
			Hibernate.initialize(cat.getAgenteCausador());
		return cat;
	}
	
	private Cat loadParteCorpoAtingida(Cat cat) {
		if(cat.getParteCorpoAtingida()!=null)
			Hibernate.initialize(cat.getParteCorpoAtingida());
		return cat;
	}
	
	private Cat loadNaturezaLesao(Cat cat) {
		if(cat.getNaturezaLesao()!=null)
			Hibernate.initialize(cat.getNaturezaLesao());
		return cat;
	}
	
	private Cat loadMunicipio(Cat cat) {
		if(cat.getMunicipio()!=null)
			Hibernate.initialize(cat.getMunicipio());
		return cat;
	}
	
	private Cat loadInstalacao(Cat cat) {
		if(cat.getInstalacao()!=null)
			Hibernate.initialize(cat.getInstalacao());
		return cat;
	}
	
	private Cat loadCnae(Cat cat) {
		if(cat.getCnae()!=null)
			Hibernate.initialize(cat.getCnae());
		return cat;
	}
	
	private Cat loadClassificacaoGravidade(Cat cat) {
		if(cat.getClassificacaoGravidade()!=null)
			Hibernate.initialize(cat.getClassificacaoGravidade());
		return cat;
	}
	
	private Cat loadDiagnosticoProvavel(Cat cat) {
		if(cat.getDiagnosticoProvavel()!=null)
			Hibernate.initialize(cat.getDiagnosticoProvavel());
		return cat;
	}
}
