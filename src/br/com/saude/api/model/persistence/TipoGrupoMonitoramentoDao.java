package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.TipoGrupoMonitoramento;

public class TipoGrupoMonitoramentoDao extends GenericDao<TipoGrupoMonitoramento> {

	private static TipoGrupoMonitoramentoDao instance;
	
	private TipoGrupoMonitoramentoDao() {
		super();
	}
	
	public static TipoGrupoMonitoramentoDao getInstance() {
		if(instance==null)
			instance = new TipoGrupoMonitoramentoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
