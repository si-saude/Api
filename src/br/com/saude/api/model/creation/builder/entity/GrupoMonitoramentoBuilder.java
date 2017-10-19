package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.GrupoMonitoramentoFilter;
import br.com.saude.api.model.entity.po.GrupoMonitoramento;

public class GrupoMonitoramentoBuilder 
		extends GenericEntityBuilder<GrupoMonitoramento, GrupoMonitoramentoFilter> {

	private Function<Map<String,GrupoMonitoramento>,GrupoMonitoramento> loadGrupoMonitoramentoExames;
	private Function<Map<String,GrupoMonitoramento>,GrupoMonitoramento> loadTipoGrupoMonitoramento;
	private Function<Map<String,GrupoMonitoramento>,GrupoMonitoramento> loadEmpregados;
	
	public static GrupoMonitoramentoBuilder newInstance(GrupoMonitoramento grupoMonitoramento) {
		return new GrupoMonitoramentoBuilder(grupoMonitoramento);
	}
	
	public static GrupoMonitoramentoBuilder newInstance(List<GrupoMonitoramento> grupoMonitoramentos) {
		return new GrupoMonitoramentoBuilder(grupoMonitoramentos);
	}
	
	private GrupoMonitoramentoBuilder(List<GrupoMonitoramento> grupoMonitoramentos) {
		super(grupoMonitoramentos);
	}

	private GrupoMonitoramentoBuilder(GrupoMonitoramento grupoMonitoramento) {
		super(grupoMonitoramento);
	}

	@Override
	protected void initializeFunctions() {
		this.loadGrupoMonitoramentoExames = grupoMonitoramentos -> {
			if(grupoMonitoramentos.get("origem").getGrupoMonitoramentoExames() != null)
				grupoMonitoramentos.get("destino").setGrupoMonitoramentoExames(
						GrupoMonitoramentoExameBuilder
						.newInstance(grupoMonitoramentos.get("origem").getGrupoMonitoramentoExames())
						.loadExame().loadCriterios().getEntityList());
			return grupoMonitoramentos.get("destino");
		};
		
		this.loadTipoGrupoMonitoramento = grupoMonitoramentos -> {
			if(grupoMonitoramentos.get("origem").getTipoGrupoMonitoramento() != null)
				grupoMonitoramentos.get("destino").setTipoGrupoMonitoramento(
						TipoGrupoMonitoramentoBuilder
						.newInstance(grupoMonitoramentos.get("origem").getTipoGrupoMonitoramento())
						.getEntity());
			return grupoMonitoramentos.get("destino");
		};
		
		this.loadEmpregados = grupoMonitoramentos -> {
			if(grupoMonitoramentos.get("origem").getEmpregados() != null)
				grupoMonitoramentos.get("destino").setEmpregados(EmpregadoBuilder
												.newInstance(grupoMonitoramentos.get("origem").getEmpregados())
												.getEntityList());
			return grupoMonitoramentos.get("destino");
		};
	}

	@Override
	protected GrupoMonitoramento clone(GrupoMonitoramento grupoMonitoramento) {
		GrupoMonitoramento newGrupoMonitoramento = new GrupoMonitoramento();
		
		newGrupoMonitoramento.setId(grupoMonitoramento.getId());
		newGrupoMonitoramento.setNome(grupoMonitoramento.getNome());
		newGrupoMonitoramento.setVersion(grupoMonitoramento.getVersion());
		
		return newGrupoMonitoramento;
	}
	
	public GrupoMonitoramentoBuilder loadGrupoMonitoramentoExames() {
		return (GrupoMonitoramentoBuilder) this.loadProperty(this.loadGrupoMonitoramentoExames);
	}
	
	public GrupoMonitoramentoBuilder loadTipoGrupoMonitoramento() {
		return (GrupoMonitoramentoBuilder) this.loadProperty(this.loadTipoGrupoMonitoramento);
	}
	
	public GrupoMonitoramentoBuilder loadEmpregados() {
		return (GrupoMonitoramentoBuilder) this.loadProperty(this.loadEmpregados);
	}

	@Override
	public GrupoMonitoramento cloneFromFilter(GrupoMonitoramentoFilter filter) {
		return null;
	}

}
