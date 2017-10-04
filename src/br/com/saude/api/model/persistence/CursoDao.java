package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Curso;

public class CursoDao extends GenericDao<Curso> {
	
	private static CursoDao instance;
	
	private CursoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static CursoDao getInstance() {
		if(instance==null)
			instance = new CursoDao();
		return instance;
	}
}
