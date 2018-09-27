package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.po.Exame;

public class ExameExampleBuilder extends GenericExampleBuilder<Exame,ExameFilter> {

	public static ExameExampleBuilder newInstance(ExameFilter filter) {
		return new ExameExampleBuilder(filter);
	}
	
	private ExameExampleBuilder(ExameFilter filter) {
		super(filter);
	}
	
	private void addCodigo() {
		if(this.filter.getCodigo() != null)
			this.criterions.add(Restrictions.ilike("codigo", Helper.filterLike(this.filter.getCodigo())));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}
	
	private void addId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.eq("id", (int)this.filter.getId()));
	}

	@Override
	protected void createExample() {
		addId();
		addCodigo();
		addDescricao();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}
