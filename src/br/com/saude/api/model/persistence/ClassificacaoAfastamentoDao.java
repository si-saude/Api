package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.ClassificacaoAfastamento;

public class ClassificacaoAfastamentoDao extends GenericDao<ClassificacaoAfastamento> {
	
	private static ClassificacaoAfastamentoDao instance;
	
	private ClassificacaoAfastamentoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static ClassificacaoAfastamentoDao getInstance() {
		if(instance==null)
			instance = new ClassificacaoAfastamentoDao();
		return instance;
	}

}
