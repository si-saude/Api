package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CargoFilter;
import br.com.saude.api.model.entity.po.Cargo;

public class CargoExampleBuilder extends GenericExampleBuilder<Cargo,CargoFilter> {

	public static CargoExampleBuilder newInstance(CargoFilter filter) {
		return new CargoExampleBuilder(filter);
	}
	
	private CargoExampleBuilder(CargoFilter filter) {
		super(filter);
	}
	
	private void addNeId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.ne("id", (int)this.filter.getId()));
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addOperador() {
		if(this.filter.getOperador() != null)
			this.entity.setOperador(Helper.filterLike(this.filter.getOperador()));
	}

	@Override
	protected void createExample() {
		addNome();
		addOperador();
	}

	@Override
	protected void createExampleSelectList() {
		addNeId();
	}
	
}
