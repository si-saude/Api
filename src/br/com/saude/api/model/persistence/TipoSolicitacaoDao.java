package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.TipoSolicitacao;

public class TipoSolicitacaoDao extends GenericDao<TipoSolicitacao> {

	private static TipoSolicitacaoDao instance;
	
	private TipoSolicitacaoDao() {
		super();
	}
	
	public static TipoSolicitacaoDao getInstance() {
		if(instance==null)
			instance = new TipoSolicitacaoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}

}
