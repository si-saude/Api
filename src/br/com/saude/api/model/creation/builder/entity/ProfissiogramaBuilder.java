package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ProfissiogramaFilter;
import br.com.saude.api.model.entity.po.Profissiograma;

public class ProfissiogramaBuilder extends GenericEntityBuilder<Profissiograma, ProfissiogramaFilter> {

	private Function<Map<String,Profissiograma>,Profissiograma> loadGrupoMonitoramentoProfissiogramas;
	
	public static ProfissiogramaBuilder newInstance(Profissiograma profissiograma) {
		return new ProfissiogramaBuilder(profissiograma);
	}
	
	public static ProfissiogramaBuilder newInstance(List<Profissiograma> profissiogramas) {
		return new ProfissiogramaBuilder(profissiogramas);
	}
	
	private ProfissiogramaBuilder(Profissiograma profissiograma) {
		super(profissiograma);
	}

	private ProfissiogramaBuilder(List<Profissiograma> profissiogramas) {
		super(profissiogramas);
	}

	@Override
	protected void initializeFunctions() {
		this.loadGrupoMonitoramentoProfissiogramas = profissiogramas -> {
			if(profissiogramas.get("origem").getGrupoMonitoramentoProfissiogramas() != null)
				profissiogramas.get("destino").setGrupoMonitoramentoProfissiogramas(
						GrupoMonitoramentoProfissiogramaBuilder
						.newInstance(profissiogramas.get("origem").getGrupoMonitoramentoProfissiogramas())
						.loadGrupoMonitoramentoProfissiogramaExames()
						.getEntityList());
			return profissiogramas.get("destino");
		};
	}

	@Override
	protected Profissiograma clone(Profissiograma profissiograma) {
		Profissiograma newProfissiograma = new Profissiograma();
		
		newProfissiograma.setId(profissiograma.getId());
		newProfissiograma.setNome(profissiograma.getNome());
		newProfissiograma.setConcluido(profissiograma.isConcluido());
		newProfissiograma.setVersion(profissiograma.getVersion());
		
		return newProfissiograma;
	}
	
	public ProfissiogramaBuilder loadGrupoMonitoramentoProfissiogramas() {
		return (ProfissiogramaBuilder) this.loadProperty(this.loadGrupoMonitoramentoProfissiogramas);
	}

	@Override
	public Profissiograma cloneFromFilter(ProfissiogramaFilter filter) {
		return null;
	}

}
