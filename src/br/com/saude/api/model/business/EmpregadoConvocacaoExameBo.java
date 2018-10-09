package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.EmpregadoConvocacaoExameBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoConvocacaoExameExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoConvocacaoExameFilter;
import br.com.saude.api.model.entity.po.EmpregadoConvocacaoExame;
import br.com.saude.api.model.persistence.EmpregadoConvocacaoExameDao;

public class EmpregadoConvocacaoExameBo 
	extends GenericBo<EmpregadoConvocacaoExame, EmpregadoConvocacaoExameFilter, 
		EmpregadoConvocacaoExameDao, EmpregadoConvocacaoExameBuilder, EmpregadoConvocacaoExameExampleBuilder> {

	private static EmpregadoConvocacaoExameBo instance;
	
	private EmpregadoConvocacaoExameBo() {
		super();
	}
	
	public static EmpregadoConvocacaoExameBo getInstance() {
		if(instance == null)
			instance = new EmpregadoConvocacaoExameBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {

		
	}
}
