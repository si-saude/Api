package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.FuncaoBuilder;
import br.com.saude.api.model.creation.builder.example.FuncaoExampleBuilder;
import br.com.saude.api.model.entity.filter.FuncaoFilter;
import br.com.saude.api.model.entity.po.Funcao;
import br.com.saude.api.model.persistence.FuncaoDao;

public class FuncaoBo 
	extends GenericBo<Funcao, FuncaoFilter, FuncaoDao, FuncaoBuilder, FuncaoExampleBuilder> {

	private static FuncaoBo instance;
	
	private FuncaoBo() {
		super();
	}
	
	public static FuncaoBo getInstance() {
		if(instance==null)
			instance = new FuncaoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
