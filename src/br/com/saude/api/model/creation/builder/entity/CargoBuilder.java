package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CargoFilter;
import br.com.saude.api.model.entity.po.Cargo;

public class CargoBuilder extends GenericEntityBuilder<Cargo,CargoFilter> {

	private Function<Map<String,Cargo>,Cargo> loadCursos;
	private Function<Map<String,Cargo>,Cargo> loadVacinas;
	
	public static CargoBuilder newInstance(Cargo cargo) {
		return new CargoBuilder(cargo);
	}
	
	public static CargoBuilder newInstance(List<Cargo> cargos) {
		return new CargoBuilder(cargos);
	}
	
	private CargoBuilder(List<Cargo> cargos) {
		super(cargos);
	}

	private CargoBuilder(Cargo cargo) {
		super(cargo);
	}
	
	@Override
	protected void initializeFunctions() {
		this.loadCursos = cargos ->{
			if(cargos.get("origem").getCursos() != null) {
				cargos.get("destino").setCursos(CursoBuilder.newInstance(cargos.get("origem").getCursos()).getEntityList());
			}
			return cargos.get("destino");
		};
		
		this.loadVacinas = cargos -> {
			if(cargos.get("origem").getVacinas() != null) {
				cargos.get("destino").setVacinas(VacinaBuilder.newInstance(cargos.get("origem").getVacinas()).getEntityList());
			}
			return cargos.get("destino");
		};
	}

	@Override
	protected Cargo clone(Cargo cargo) {
		Cargo newCargo = new Cargo();
		
		newCargo.setId(cargo.getId());
		newCargo.setNome(cargo.getNome());
		newCargo.setVersion(cargo.getVersion());
		
		return newCargo;
	}
	
	public CargoBuilder loadCursos() {
		return (CargoBuilder) this.loadProperty(this.loadCursos);
	}
	
	public CargoBuilder loadVacinas() {
		return (CargoBuilder) this.loadProperty(this.loadVacinas);
	}

	@Override
	public Cargo cloneFromFilter(CargoFilter filter) {
		return null;
	}

}
