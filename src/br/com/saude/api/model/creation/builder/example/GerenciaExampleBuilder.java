package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

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
	
	private void addNeId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.ne("id", (int)this.filter.getId()));
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
		addCodigo();
		addDescricao();
	}
	
	@Override
	protected void createExampleSelectList() {
		addNeId();
	}
}
