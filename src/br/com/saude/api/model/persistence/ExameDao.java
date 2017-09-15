package br.com.saude.api.model.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Exame;

public class ExameDao extends GenericDao<Exame> {
	private static ExameDao instance;
	
	private ExameDao(){
		super();
	}
	
	public static ExameDao getInstance() {
		if(instance == null)
			instance = new ExameDao();
		return instance;
	}
	
	public Exame getByIdLoadEmpregado(Object id) throws Exception {
		return this.getById(id, "loadEmpregado");
	}
	
	public List<Exame> getListLoadEmpregado(List<Criterion> criterions) throws Exception{
		return this.getList(criterions, "loadEmpregado");
	}
	
	@SuppressWarnings("unused")
	private Exame loadEmpregado(Exame exame) {
		if(exame.getEmpregado() != null)
			Hibernate.initialize(exame.getEmpregado());
		return exame;
	}
}
