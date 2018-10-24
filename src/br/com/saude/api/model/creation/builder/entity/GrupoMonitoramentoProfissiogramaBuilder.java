package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.GrupoMonitoramentoProfissiograma;

public class GrupoMonitoramentoProfissiogramaBuilder 
	extends GenericEntityBuilder<GrupoMonitoramentoProfissiograma, GenericFilter> {

	private Function<Map<String,GrupoMonitoramentoProfissiograma>,GrupoMonitoramentoProfissiograma> loadGrupoMonitoramentoProfissiogramaExames;
	
	public static GrupoMonitoramentoProfissiogramaBuilder 
		newInstance(GrupoMonitoramentoProfissiograma entity) {
		return new GrupoMonitoramentoProfissiogramaBuilder(entity);
	}
	
	public static GrupoMonitoramentoProfissiogramaBuilder 
		newInstance(List<GrupoMonitoramentoProfissiograma> list) {
		return new GrupoMonitoramentoProfissiogramaBuilder(list);
	}
	
	private GrupoMonitoramentoProfissiogramaBuilder(GrupoMonitoramentoProfissiograma entity) {
		super(entity);
	}
	
	private GrupoMonitoramentoProfissiogramaBuilder(List<GrupoMonitoramentoProfissiograma> list) {
		super(list);
	}

	@Override
	protected void initializeFunctions() {
		this.loadGrupoMonitoramentoProfissiogramaExames = grupoMonitoramentoProfissiogramas -> {
			if(grupoMonitoramentoProfissiogramas.get("origem").getGrupoMonitoramentoProfissiogramaExames() != null)
				grupoMonitoramentoProfissiogramas.get("destino").setGrupoMonitoramentoProfissiogramaExames(
						GrupoMonitoramentoProfissiogramaExameBuilder
						.newInstance(grupoMonitoramentoProfissiogramas.get("origem").getGrupoMonitoramentoProfissiogramaExames())
						.loadCriterios()
						.getEntityList());
			return grupoMonitoramentoProfissiogramas.get("destino");
		};
	}

	@Override
	protected GrupoMonitoramentoProfissiograma clone(GrupoMonitoramentoProfissiograma entity) {
		GrupoMonitoramentoProfissiograma newEntity = new GrupoMonitoramentoProfissiograma();
		
		newEntity.setId(entity.getId());
		newEntity.setVersion(entity.getVersion());
		
		if(entity.getGrupoMonitoramento() != null)
			newEntity.setGrupoMonitoramento(GrupoMonitoramentoBuilder
					.newInstance(entity.getGrupoMonitoramento()).getEntity());
		
		return newEntity;
	}

	@Override
	public GrupoMonitoramentoProfissiograma cloneFromFilter(GenericFilter filter) {
		return null;
	}

	public GrupoMonitoramentoProfissiogramaBuilder loadGrupoMonitoramentoProfissiogramaExames() {
		return (GrupoMonitoramentoProfissiogramaBuilder) this.loadProperty(this.loadGrupoMonitoramentoProfissiogramaExames);
	}

}
