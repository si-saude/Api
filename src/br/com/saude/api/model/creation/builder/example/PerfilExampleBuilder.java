package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.PerfilFilter;
import br.com.saude.api.model.entity.po.Perfil;

public class PerfilExampleBuilder extends GenericExampleBuilder<Perfil,PerfilFilter> {

	public static PerfilExampleBuilder newInstance(PerfilFilter filter) {
		return new PerfilExampleBuilder(filter);
	}
	
	private PerfilExampleBuilder(PerfilFilter filter) {
		super(filter);
	}

	private void addTitulo() {
		if(this.filter.getTitulo()!= null)
			this.entity.setTitulo(Helper.filterLike(this.filter.getTitulo()));
	}
	
	@Override
	public PerfilExampleBuilder example() {
		return (PerfilExampleBuilder) super.example();
	}
	

	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.entity = new Perfil();
		addTitulo();
	}
}
