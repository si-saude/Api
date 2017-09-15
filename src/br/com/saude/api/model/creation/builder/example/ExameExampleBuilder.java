package br.com.saude.api.model.creation.builder.example;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

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
			this.entity.setCodigo(Helper.filterLike(this.filter.getCodigo()));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}

	@Override
	public List<Criterion> getExample() {
		if(this.filter != null) {
			this.criterions = new ArrayList<Criterion>();
			this.entity = new Exame();
			addCodigo();
			addDescricao();
			this.criterions.add(Example.create(this.entity).enableLike().ignoreCase());
			return this.criterions;			
		}else
			return null;
	}
}
