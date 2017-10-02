package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.EmpregadoBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.persistence.EmpregadoDao;

public class EmpregadoBo extends GenericBo<Empregado, EmpregadoFilter, EmpregadoDao, 
											EmpregadoBuilder, EmpregadoExampleBuilder> {
	
	private static EmpregadoBo instance;
	
	private EmpregadoBo() {
		super();
	}
	
	public static EmpregadoBo getInstance() {
		if(instance == null)
			instance = new EmpregadoBo();
		return instance;
	}
}
