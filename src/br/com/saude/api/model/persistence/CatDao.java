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
	
}
