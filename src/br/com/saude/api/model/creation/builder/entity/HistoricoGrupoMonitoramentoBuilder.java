package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.HistoricoGrupoMonitoramento;

public class HistoricoGrupoMonitoramentoBuilder
		extends GenericEntityBuilder<HistoricoGrupoMonitoramento, GenericFilter>{

	private Function<Map<String,HistoricoGrupoMonitoramento>,HistoricoGrupoMonitoramento> loadGrupoMonitoramento;
	
	public static HistoricoGrupoMonitoramentoBuilder newInstance(HistoricoGrupoMonitoramento historico) {
		return new HistoricoGrupoMonitoramentoBuilder(historico);
	}
	
	public static HistoricoGrupoMonitoramentoBuilder newInstance(List<HistoricoGrupoMonitoramento> historicos) {
		return new HistoricoGrupoMonitoramentoBuilder(historicos);
	}
	
	private HistoricoGrupoMonitoramentoBuilder(List<HistoricoGrupoMonitoramento> historicos) {
		super(historicos);
	}

	private HistoricoGrupoMonitoramentoBuilder(HistoricoGrupoMonitoramento historico) {
		super(historico);
	}

	@Override
	protected void initializeFunctions() {
		this.loadGrupoMonitoramento = historicos -> {
			if(historicos.get("origem").getGrupoMonitoramento()!= null) {
				historicos.get("destino").setGrupoMonitoramento(GrupoMonitoramentoBuilder
												.newInstance(historicos.get("origem").getGrupoMonitoramento())
												.getEntity());
			}
			return historicos.get("destino");
		};
	}

	@Override
	protected HistoricoGrupoMonitoramento clone(HistoricoGrupoMonitoramento historico) {
		HistoricoGrupoMonitoramento newHistorico = new HistoricoGrupoMonitoramento();
		
		newHistorico.setId(historico.getId());
		newHistorico.setDataRemocao(historico.getDataRemocao());
		newHistorico.setVersion(historico.getVersion());
		
		return newHistorico;
	}
	
	public HistoricoGrupoMonitoramentoBuilder loadGrupoMonitoramento() {
		return (HistoricoGrupoMonitoramentoBuilder) this.loadProperty(this.loadGrupoMonitoramento);
	}

	@Override
	public HistoricoGrupoMonitoramento cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
