package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.model.entity.po.Localizacao;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.LocalizacaoFilter;

public class LocalizacaoExampleBuilder extends GenericExampleBuilder<Localizacao,LocalizacaoFilter> {

	public static LocalizacaoExampleBuilder newInstance(LocalizacaoFilter filter) {
		return new LocalizacaoExampleBuilder(filter);
	}
	
	private LocalizacaoExampleBuilder(LocalizacaoFilter filter) {
		super(filter);
	}
	
	private void addNeId() {
		if(this.filter.getId() > 0)
			this.criterions.add(Restrictions.ne("id", (int)this.filter.getId()));
	}
	
	private void addNome() {
		if(this.filter.getNome()!=null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}

	@Override
	protected void createExample() {
		addNome();
	}

	@Override
	protected void createExampleSelectList() {
		addNeId();
	}
}
