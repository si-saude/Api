package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Clinica;

public class ClinicaDao extends GenericDao<Clinica> {

	public static ClinicaDao instance;
	
	private ClinicaDao() {
		super();
	}
	
	public static ClinicaDao getInstance() {
		if(instance == null)
			instance = new ClinicaDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = clinica -> {
			
			if(clinica.getExames() != null)
				Hibernate.initialize(clinica.getExames());
			
			return clinica;
		};
	}
	
	@Override
	public Clinica getById(Object id) throws Exception {
		return super.getById(id, this.functionLoadAll);
	}
	
	public PagedList<Clinica> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
}
