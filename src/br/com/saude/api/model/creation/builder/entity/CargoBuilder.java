package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.CargoFilter;
import br.com.saude.api.model.entity.po.Cargo;

public class CargoBuilder extends GenericEntityBuilder<Cargo,CargoFilter> {

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
	protected Cargo clone(Cargo cargo) {
		Cargo newCargo = new Cargo();
		
		newCargo.setId(cargo.getId());
		newCargo.setNome(cargo.getNome());
		newCargo.setVersion(cargo.getVersion());
		
		return newCargo;
	}
	
	public CargoBuilder loadCursos() {
		if(this.entity != null) {
			this.newEntity = loadCursos(this.entity,this.newEntity);
		}else {
			for(Cargo cargo:this.entityList) {
				Cargo newCargo = this.newEntityList.stream()
						.filter(e->e.getId() == cargo.getId())
						.iterator().next();
				newCargo = loadCursos(cargo,newCargo);
			}
		}
		return this;
	}
	
	private Cargo loadCursos(Cargo origem,Cargo destino) {
		if(origem.getCursos() != null) {
			destino.setCursos(CursoBuilder.newInstance(origem.getCursos()).getEntityList());
		}
		return destino;
	}
	
	public CargoBuilder loadVacinas() {
		if(this.entity != null) {
			this.newEntity = loadCursos(this.entity,this.newEntity);
		}else {
			for(Cargo cargo:this.entityList) {
				Cargo newCargo = this.newEntityList.stream()
						.filter(e->e.getId() == cargo.getId())
						.iterator().next();
				newCargo = loadVacinas(cargo,newCargo);
			}
		}
		return this;
	}
	
	private Cargo loadVacinas(Cargo origem,Cargo destino) {
		if(origem.getVacinas() != null) {
			destino.setVacinas(VacinaBuilder.newInstance(origem.getVacinas()).getEntityList());
		}
		return destino;
	}

	@Override
	public Cargo cloneFromFilter(CargoFilter filter) {
		return null;
	}

}
