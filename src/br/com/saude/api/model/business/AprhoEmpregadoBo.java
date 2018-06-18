package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.AprhoEmpregadoBuilder;
import br.com.saude.api.model.creation.builder.example.AprhoEmpregadoExampleBuilder;
import br.com.saude.api.model.entity.filter.AprhoEmpregadoFilter;
import br.com.saude.api.model.entity.po.AprhoEmpregado;
import br.com.saude.api.model.persistence.AprhoEmpregadoDao;

public class AprhoEmpregadoBo 
	extends GenericBo<AprhoEmpregado, AprhoEmpregadoFilter, 
		AprhoEmpregadoDao, AprhoEmpregadoBuilder, AprhoEmpregadoExampleBuilder> {
	
	private static AprhoEmpregadoBo instance;
	
	private AprhoEmpregadoBo() {
		super();
	}
	
	public static AprhoEmpregadoBo getInstance() {
		if(instance == null)
			instance = new AprhoEmpregadoBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
	}	
}
