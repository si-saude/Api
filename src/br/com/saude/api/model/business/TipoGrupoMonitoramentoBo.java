package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.TipoGrupoMonitoramentoBuilder;
import br.com.saude.api.model.creation.builder.example.TipoGrupoMonitoramentoExampleBuilder;
import br.com.saude.api.model.entity.filter.TipoGrupoMonitoramentoFilter;
import br.com.saude.api.model.entity.po.TipoGrupoMonitoramento;
import br.com.saude.api.model.persistence.TipoGrupoMonitoramentoDao;

public class TipoGrupoMonitoramentoBo 
				extends GenericBo<TipoGrupoMonitoramento, TipoGrupoMonitoramentoFilter, 
								TipoGrupoMonitoramentoDao, TipoGrupoMonitoramentoBuilder, 
								TipoGrupoMonitoramentoExampleBuilder> {

	private static TipoGrupoMonitoramentoBo instance;
	
	private TipoGrupoMonitoramentoBo() {
		super();
	}
	
	public static TipoGrupoMonitoramentoBo getInstance() {
		if(instance==null)
			instance = new TipoGrupoMonitoramentoBo();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
}
