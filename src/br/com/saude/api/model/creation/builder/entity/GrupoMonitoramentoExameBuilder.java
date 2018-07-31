package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.GrupoMonitoramentoExame;

public class GrupoMonitoramentoExameBuilder 
	extends GenericEntityBuilder<GrupoMonitoramentoExame, GenericFilter> {

	private Function<Map<String,GrupoMonitoramentoExame>,GrupoMonitoramentoExame> loadExame;
	private Function<Map<String,GrupoMonitoramentoExame>,GrupoMonitoramentoExame> loadPeriodicidade;
	private Function<Map<String,GrupoMonitoramentoExame>,GrupoMonitoramentoExame> loadCriterios;
	
	public static GrupoMonitoramentoExameBuilder newInstance(GrupoMonitoramentoExame grupoMonitoramentoExame) {
		return new GrupoMonitoramentoExameBuilder(grupoMonitoramentoExame);
	}
	
	public static GrupoMonitoramentoExameBuilder newInstance(List<GrupoMonitoramentoExame> grupoMonitoramentoExames) {
		return new GrupoMonitoramentoExameBuilder(grupoMonitoramentoExames);
	}
	
	private GrupoMonitoramentoExameBuilder(List<GrupoMonitoramentoExame> grupoMonitoramentoExames) {
		super(grupoMonitoramentoExames);
	}

	private GrupoMonitoramentoExameBuilder(GrupoMonitoramentoExame grupoMonitoramentoExame) {
		super(grupoMonitoramentoExame);
	}

	@Override
	protected void initializeFunctions() {
		this.loadExame = grupoMonitoramentoExames -> {
			if(grupoMonitoramentoExames.get("origem").getExame() != null)
				grupoMonitoramentoExames.get("destino")
					.setExame(ExameBuilder.newInstance(grupoMonitoramentoExames.get("origem").getExame()).getEntity());
			return grupoMonitoramentoExames.get("destino");
		};
		
		this.loadPeriodicidade = grupoMonitoramentoExames -> {
			if(grupoMonitoramentoExames.get("origem").getPeriodicidade() != null)
				grupoMonitoramentoExames.get("destino")
					.setPeriodicidade(PeriodicidadeBuilder.newInstance(grupoMonitoramentoExames.get("origem").getPeriodicidade()).getEntity());
			return grupoMonitoramentoExames.get("destino");
		};
		
		this.loadCriterios = grupoMonitoramentoExames -> {
			if(grupoMonitoramentoExames.get("origem").getCriterios() != null)
				grupoMonitoramentoExames.get("destino")
					.setCriterios(CriterioBuilder.newInstance(grupoMonitoramentoExames.get("origem").getCriterios()).getEntityList());
			return grupoMonitoramentoExames.get("destino");
		};
	}

	@Override
	protected GrupoMonitoramentoExame clone(GrupoMonitoramentoExame grupoMonitoramentoExame) {
		GrupoMonitoramentoExame newGrupoMonitoramentoExame = new GrupoMonitoramentoExame();
		
		newGrupoMonitoramentoExame.setId(grupoMonitoramentoExame.getId());
		newGrupoMonitoramentoExame.setVersion(grupoMonitoramentoExame.getVersion());
		newGrupoMonitoramentoExame.setOpcional(grupoMonitoramentoExame.isOpcional());
		
		return newGrupoMonitoramentoExame;
	}
	
	public GrupoMonitoramentoExameBuilder loadExame() {
		return (GrupoMonitoramentoExameBuilder) this.loadProperty(this.loadExame);
	}
	
	public GrupoMonitoramentoExameBuilder loadPeriodicidade() {
		return (GrupoMonitoramentoExameBuilder) this.loadProperty(this.loadPeriodicidade);
	}
	
	public GrupoMonitoramentoExameBuilder loadCriterios() {
		return (GrupoMonitoramentoExameBuilder) this.loadProperty(this.loadCriterios);
	}

	@Override
	public GrupoMonitoramentoExame cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
