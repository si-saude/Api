package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.TipoGrupoMonitoramentoFilter;
import br.com.saude.api.model.entity.po.TipoGrupoMonitoramento;

public class TipoGrupoMonitoramentoBuilder  
		extends GenericEntityBuilder<TipoGrupoMonitoramento, TipoGrupoMonitoramentoFilter> {

	public static TipoGrupoMonitoramentoBuilder newInstance(TipoGrupoMonitoramento tipo) {
		return new TipoGrupoMonitoramentoBuilder(tipo);
	}
	
	public static TipoGrupoMonitoramentoBuilder newInstance(List<TipoGrupoMonitoramento> tipos) {
		return new TipoGrupoMonitoramentoBuilder(tipos);
	}
	
	private TipoGrupoMonitoramentoBuilder(TipoGrupoMonitoramento tipo) {
		super(tipo);
	}

	private TipoGrupoMonitoramentoBuilder(List<TipoGrupoMonitoramento> tipos) {
		super(tipos);
	}

	@Override
	protected TipoGrupoMonitoramento clone(TipoGrupoMonitoramento tipo) {
		TipoGrupoMonitoramento newTipo = new TipoGrupoMonitoramento();
		
		newTipo.setId(tipo.getId());
		newTipo.setDescricao(tipo.getDescricao());
		newTipo.setNome(tipo.getNome());
		newTipo.setVersion(tipo.getVersion());
		
		return newTipo;
	}

	@Override
	public TipoGrupoMonitoramento cloneFromFilter(TipoGrupoMonitoramentoFilter filter) {
		return null;
	}

}
