package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Equipe;

public class EquipeDao extends GenericDao<Equipe> {
	private static EquipeDao instance;
	
	private EquipeDao() {
		super();
	}
	
	public static EquipeDao getInstance() {
		if(instance==null)
			instance = new EquipeDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = equipe -> {
			equipe = loadCoordenador(equipe);
			return equipe;
		};
	}
	
	private Equipe loadCoordenador(Equipe equipe) {
		if(equipe.getCoordenador()!=null) {
			Hibernate.initialize(equipe.getCoordenador());
		}
		return equipe;
	}
	
	public Equipe getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Equipe> getListFunctionLoad(GenericExampleBuilder<?,?> exampleBuilder) throws Exception {
		return getList(exampleBuilder,this.functionLoad);
	}
}
