package br.com.saude.api.model.persistence;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.model.entity.po.RelatorioMedico;

public class RelatorioMedicoDao extends GenericDao<RelatorioMedico> {
	
	private static RelatorioMedicoDao instance;
	
	private RelatorioMedicoDao() {
		super();
	}

	public static RelatorioMedicoDao getInstance() {
		if (instance == null)
			instance = new RelatorioMedicoDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() { }

}
