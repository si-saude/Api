package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Cidade;

public class CidadeDao extends GenericDao<Cidade> {
	
	private static CidadeDao instance;
	
	private CidadeDao() {
		super();
	}
	
	public static CidadeDao getInstance() {
		if(instance==null)
			instance = new CidadeDao();
		return instance;
	}
}
