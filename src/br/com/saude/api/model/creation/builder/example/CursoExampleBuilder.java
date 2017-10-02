package br.com.saude.api.model.creation.builder.example;


import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.CursoFilter;
import br.com.saude.api.model.entity.po.Curso;

public class CursoExampleBuilder extends GenericExampleBuilder<Curso,CursoFilter> {

	public static CursoExampleBuilder newInstance(CursoFilter filter) {
		return new CursoExampleBuilder(filter);
	}
	
	private CursoExampleBuilder(CursoFilter filter) {
		super(filter);
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}
	
	private void addValidade() {
		if(this.filter.getValidade() > 0)
			this.entity.setValidade(this.filter.getValidade());
	}

	@Override
	protected void createExample() {
		addNome();
		addDescricao();
		addValidade();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
}
