package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.po.Gerencia;

public class GerenciaExampleBuilder extends GenericExampleBuilder<Gerencia,GerenciaFilter> {

	public static GerenciaExampleBuilder newInstance(GerenciaFilter filter) {
		return new GerenciaExampleBuilder(filter);
	}
	
	private GerenciaExampleBuilder(GerenciaFilter filter) {
		super(filter);
	}
	
	private void addCodigo() {
		if(this.filter.getCodigo() != null)
			this.entity.setCodigo(Helper.filterLike(this.filter.getCodigo()));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}

	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.entity = new Gerencia();
		addCodigo();
		addDescricao();
	}
	
	@Override
	public GerenciaExampleBuilder example() {
		return (GerenciaExampleBuilder)super.example();
	}
}
