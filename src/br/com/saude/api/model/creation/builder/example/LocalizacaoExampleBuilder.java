package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.model.entity.po.Localizacao;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
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
	public LocalizacaoExampleBuilder example() {
		return (LocalizacaoExampleBuilder) super.example();
	}
	
	public LocalizacaoExampleBuilder exampleSelectList() {
		if(this.filter!=null) {
			createExampleSelectList();
			this.criterions.add(getExample());
		}
		return this;
	}

	@Override
	protected void createExample() {
		this.criterions = new ArrayList<Criterion>();
		this.entity = new Localizacao();
		addNome();
	}
	
	private void createExampleSelectList() {
		this.criterions = new ArrayList<Criterion>();
		this.entity = new Localizacao();
		addNeId();
		this.filter.setPageNumber(1);
		this.filter.setPageSize(Integer.MAX_VALUE);
	}
}
