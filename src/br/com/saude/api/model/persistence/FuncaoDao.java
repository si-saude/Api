package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Funcao;

public class FuncaoDao extends GenericDao<Funcao> {
	
	private static FuncaoDao instance;
	
	private FuncaoDao() {
		super();
	}
	
	public static FuncaoDao getInstance() {
		if(instance == null)
			instance = new FuncaoDao();
		return instance;
	}
}
