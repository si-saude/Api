package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.ProfissionalVacina;

public class ProfissionalVacinaBuilder extends GenericEntityBuilder<ProfissionalVacina,GenericFilter> {

	private Function<Map<String,ProfissionalVacina>,ProfissionalVacina> loadVacina;
	
	public static ProfissionalVacinaBuilder newInstance(ProfissionalVacina profissionalVacina) {
		return new ProfissionalVacinaBuilder(profissionalVacina);
	}
	
	public static ProfissionalVacinaBuilder newInstance(List<ProfissionalVacina> profissionalVacinas) {
		return new ProfissionalVacinaBuilder(profissionalVacinas);
	}
	
	private ProfissionalVacinaBuilder(ProfissionalVacina profissionalVacina) {
		super(profissionalVacina);
	}

	private ProfissionalVacinaBuilder(List<ProfissionalVacina> profissionalVacinas) {
		super(profissionalVacinas);
	}

	@Override
	protected ProfissionalVacina clone(ProfissionalVacina profissionalVacina) {
		ProfissionalVacina newProfissionalVacina = new ProfissionalVacina();
		
		newProfissionalVacina.setId(profissionalVacina.getId());
		newProfissionalVacina.setData(profissionalVacina.getData());
		newProfissionalVacina.setDose(profissionalVacina.getDose());
		newProfissionalVacina.setLaboratorio(profissionalVacina.getLaboratorio());
		newProfissionalVacina.setLote(profissionalVacina.getLote());
		newProfissionalVacina.setProximaDose(profissionalVacina.getProximaDose());
		newProfissionalVacina.setVersion(profissionalVacina.getVersion());
		
		return newProfissionalVacina;
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadVacina = profissionalVacinas -> {
			if(profissionalVacinas.get("origem").getVacina()!= null) {
				profissionalVacinas.get("destino").setVacina(VacinaBuilder.newInstance(profissionalVacinas.get("origem").getVacina()).getEntity());
			}
			return profissionalVacinas.get("destino");
		};
	}
	
	public ProfissionalVacinaBuilder loadVacina() {
		return (ProfissionalVacinaBuilder) this.loadProperty(this.loadVacina);
	}

	@Override
	public ProfissionalVacina cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
