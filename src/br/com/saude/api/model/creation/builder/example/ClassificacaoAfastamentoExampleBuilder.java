package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ClassificacaoAfastamentoFilter;
import br.com.saude.api.model.entity.po.ClassificacaoAfastamento;

public class ClassificacaoAfastamentoExampleBuilder extends GenericExampleBuilder<ClassificacaoAfastamento,ClassificacaoAfastamentoFilter> {

	public static ClassificacaoAfastamentoExampleBuilder newInstance(ClassificacaoAfastamentoFilter filter) {
		return new ClassificacaoAfastamentoExampleBuilder(filter);
	}
	
	private ClassificacaoAfastamentoExampleBuilder(ClassificacaoAfastamentoFilter filter) {
		super(filter);
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.criterions.add(Restrictions.ilike("descricao", Helper.filterLike(this.filter.getDescricao())));
	}
	
	protected void addGeraAfastamento() {
		this.entity.setGeraAfastamento(this.addBoolean("geraAfastamento", this.filter.getGeraAfastamento()));
	}

	@Override
	protected void createExample() {
		addDescricao();
		addGeraAfastamento();
	}

	@Override
	protected void createExampleSelectList() {
		addDescricao();
		addGeraAfastamento();
	}

}
