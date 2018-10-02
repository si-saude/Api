package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Empresa;

public class EmpresaDao extends GenericDao<Empresa> {
	
	private static EmpresaDao instance;
	
	private EmpresaDao() {
		super();
	}
	
	public static EmpresaDao getInstance() {
		if(instance==null)
			instance = new EmpresaDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = empresa -> {
			empresa = loadMunicipio(empresa);
			empresa = loadCnaes(empresa);
			return empresa;
		};
	}
	
	private Empresa loadMunicipio(Empresa empresa) {
		if(empresa.getMunicipio() != null)
			Hibernate.initialize(empresa.getMunicipio());
		return empresa;
	}
	
	private Empresa loadCnaes(Empresa empresa) {
		if(empresa.getCnaes() != null)
			Hibernate.initialize(empresa.getCnaes());
		return empresa;
	}
	
	public Empresa getByIdLoadMunicipio(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
