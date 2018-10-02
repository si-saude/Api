package br.com.saude.api.model.creation.builder.example;

import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.ClassificacaoGravidadeFilter;
import br.com.saude.api.model.entity.po.ClassificacaoGravidade;

public class ClassificacaoGravidadeExampleBuilder 
	extends GenericExampleBuilder<ClassificacaoGravidade,ClassificacaoGravidadeFilter> {

	public static ClassificacaoGravidadeExampleBuilder newInstance(ClassificacaoGravidadeFilter filter) {
		return new ClassificacaoGravidadeExampleBuilder(filter);
	}
	
	private ClassificacaoGravidadeExampleBuilder(ClassificacaoGravidadeFilter filter) {
		super(filter);
	}
	
	private void addTitulo() {
		if(this.filter.getTitulo() != null)
			this.criterions.add(Restrictions.ilike("titulo", Helper.filterLike(this.filter.getTitulo())));
	}
	
	@Override
	protected void createExample() {
		addTitulo();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}