package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.Servico;

public class ServicoDao extends GenericDao<Servico> {

	private static ServicoDao instance;
	
	private ServicoDao() {
		super();
	}
	
	public static ServicoDao getInstance() {
		if(instance == null)
			instance = new ServicoDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {

	}
}
