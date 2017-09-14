package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Empregado;

public class EmpregadoDao extends GenericDao<Empregado>  {
	private static EmpregadoDao instance;
	
	private EmpregadoDao(){
		super();
	}
	
	public static EmpregadoDao getInstance() {
		if(instance == null)
			instance = new EmpregadoDao();
		return instance;
	}
}
