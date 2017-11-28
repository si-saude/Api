package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.ResultadoExame;
import br.com.saude.api.model.persistence.ResultadoExameDao;

public class ResultadoExameBo extends GenericBo<ResultadoExame, GenericFilter, ResultadoExameDao, 
											GenericEntityBuilder<ResultadoExame,GenericFilter>, 
											GenericExampleBuilder<ResultadoExame,GenericFilter>> {
	private static ResultadoExameBo instance;
	
	private ResultadoExameBo() {
		super();
	}
	
	public static ResultadoExameBo getInstance() {
		if(instance==null)
			instance = new ResultadoExameBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	public ResultadoExame getUltimoByEmpregadoExame(ResultadoExame resultadoExame) throws Exception {
		return getDao().getUltimoByEmpregadoExame(resultadoExame);
	}
}
