package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

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

	@Override
	public PerfilExampleBuilder example() {
		if(this.filter != null) {
			this.criterions = new ArrayList<Criterion>();
			this.entity = new Perfil();
			addTitulo();
			this.criterions.add(Example.create(this.entity).enableLike().ignoreCase());
		}
		return this;
	}
	
	private void addTitulo() {
		if(this.filter.getTitulo()!= null)
			this.entity.setTitulo(Helper.filterLike(this.filter.getTitulo()));
	}
}
