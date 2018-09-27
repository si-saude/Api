package br.com.saude.api.model.persistence;

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
	protected void initializeFunctions() {}
}
