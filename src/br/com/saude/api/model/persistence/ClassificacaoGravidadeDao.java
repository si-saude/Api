package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.ClassificacaoGravidade;

public class ClassificacaoGravidadeDao extends GenericDao<ClassificacaoGravidade> {
	private static ClassificacaoGravidadeDao instance;
	
	private ClassificacaoGravidadeDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static ClassificacaoGravidadeDao getInstance() {
		if(instance==null)
			instance = new ClassificacaoGravidadeDao();
		return instance;
	}
}
