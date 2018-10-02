package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.GrupoMonitoramentoProfissiogramaExame;

public class GrupoMonitoramentoProfissiogramaExameBuilder 
	extends GenericEntityBuilder<GrupoMonitoramentoProfissiogramaExame, GenericFilter> {

	private Function<Map<String,GrupoMonitoramentoProfissiogramaExame>,GrupoMonitoramentoProfissiogramaExame> loadCriterios;
	
	public static GrupoMonitoramentoProfissiogramaExameBuilder 
		newInstance(GrupoMonitoramentoProfissiogramaExame entity) {
		return new GrupoMonitoramentoProfissiogramaExameBuilder(entity);
	}
	
	public static GrupoMonitoramentoProfissiogramaExameBuilder 
		newInstance(List<GrupoMonitoramentoProfissiogramaExame> list) {
		return new GrupoMonitoramentoProfissiogramaExameBuilder(list);
	}
	
	private GrupoMonitoramentoProfissiogramaExameBuilder(GrupoMonitoramentoProfissiogramaExame entity) {
		super(entity);
	}

	private GrupoMonitoramentoProfissiogramaExameBuilder(List<GrupoMonitoramentoProfissiogramaExame> list) {
		super(list);
	}

	@Override
	protected void initializeFunctions() {
		
		this.loadCriterios = grupoMonitoramentoProfissiogramaExames -> {
			if(grupoMonitoramentoProfissiogramaExames.get("origem").getCriterios() != null)
				grupoMonitoramentoProfissiogramaExames.get("destino")
					.setCriterios(CriterioBuilder.newInstance(grupoMonitoramentoProfissiogramaExames.get("origem").getCriterios()).getEntityList());
			return grupoMonitoramentoProfissiogramaExames.get("destino");
		};
	}

	@Override
	protected GrupoMonitoramentoProfissiogramaExame clone(GrupoMonitoramentoProfissiogramaExame entity) {
		GrupoMonitoramentoProfissiogramaExame newEntity = new GrupoMonitoramentoProfissiogramaExame();
		
		newEntity.setId(entity.getId());
		newEntity.setOpcional(entity.isOpcional());
		newEntity.setVersion(entity.getVersion());
		
		if(entity.getExame() != null)
			newEntity.setExame(ExameBuilder.newInstance(entity.getExame()).getEntity());
		
		return newEntity;
	}
	
	public GrupoMonitoramentoProfissiogramaExameBuilder loadCriterios() {
		return (GrupoMonitoramentoProfissiogramaExameBuilder) this.loadProperty(this.loadCriterios);
	}

	@Override
	public GrupoMonitoramentoProfissiogramaExame cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
