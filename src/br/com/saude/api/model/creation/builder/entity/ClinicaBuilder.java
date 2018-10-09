package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.ClinicaFilter;
import br.com.saude.api.model.entity.po.Clinica;

public class ClinicaBuilder extends GenericEntityBuilder<Clinica, ClinicaFilter> {

	private Function<Map<String,Clinica>,Clinica> loadExames;
	
	public static ClinicaBuilder newInstance(Clinica clinica) {
		return new ClinicaBuilder(clinica);
	}
	
	public static ClinicaBuilder newInstance(List<Clinica> list) {
		return new ClinicaBuilder(list);
	}
	
	private ClinicaBuilder(Clinica clinica) {
		super(clinica);
	}

	private ClinicaBuilder(List<Clinica> list) {
		super(list);
	}

	@Override
	protected void initializeFunctions() {
		this.loadExames = exames -> {
			if(exames.get("origem").getExames() != null)
				exames.get("destino").setExames(ExameBuilder
						.newInstance(exames.get("origem").getExames()).getEntityList());
			
			return exames.get("destino");
		};
	}
	
	public ClinicaBuilder loadExames() {
		return (ClinicaBuilder) this.loadProperty(this.loadExames);
	}

	@Override
	protected Clinica clone(Clinica clinica) {
		Clinica newClinica = new Clinica();
		
		newClinica.setId(clinica.getId());
		newClinica.setNome(clinica.getNome());
		newClinica.setUf(clinica.getUf());
		newClinica.setVersion(clinica.getVersion());
		
		return newClinica;
	}

	@Override
	public Clinica cloneFromFilter(ClinicaFilter filter) {
		return null;
	}

}
