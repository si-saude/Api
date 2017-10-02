package br.com.saude.api.model.persistence;


import java.util.function.Function;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.example.ExameExampleBuilder;
import br.com.saude.api.model.entity.po.Exame;

public class ExameDao extends GenericDao<Exame> {
	private static ExameDao instance;
	private Function<Exame,Exame> functionLoad;
	
	private ExameDao(){
		super();
		
		this.functionLoad = exame -> {
			if(exame.getEmpregado() != null)
				Hibernate.initialize(exame.getEmpregado());
			return exame;
		};
	}
	
	public static ExameDao getInstance() {
		if(instance == null)
			instance = new ExameDao();
		return instance;
	}
	
	public Exame getByIdLoadEmpregado(Object id) throws Exception {
		return this.getById(id, this.functionLoad);
	}
	
	public PagedList<Exame> getListLoadEmpregado(ExameExampleBuilder exameExampleBuilder) throws Exception{
		return this.getList(exameExampleBuilder, this.functionLoad);
	}
}
