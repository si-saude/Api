package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;

public class EmpregadoConvocacaoExameDao extends GenericDao<EmpregadoConvocacaoExame> {

	private static EmpregadoConvocacaoExameDao instance;
	
	private EmpregadoConvocacaoExameDao() {
		super();
	}
	
	public static EmpregadoConvocacaoExameDao getInstance() {
		if(instance == null)
			instance = new EmpregadoConvocacaoExameDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
