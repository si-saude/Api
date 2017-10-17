package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.GheeFilter;
import br.com.saude.api.model.entity.po.Ghee;

public class GheeExampleBuilder extends GenericExampleBuilder<Ghee, GheeFilter> {

	public static GheeExampleBuilder newInstance(GheeFilter filter) {
		return new GheeExampleBuilder(filter);
	}
	
	private GheeExampleBuilder(GheeFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addCodigo();
		addDataCriacao();
		addDataDesativacao();
		addDescricao();
		addNome();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addDataCriacao() {
		this.addData("dataCriacao", this.filter.getDataCriacao());
	}
	
	private void addDataDesativacao() {
		this.addData("dataDesativacao", this.filter.getDataDesativacao());
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
	private void addCodigo() {
		if(this.filter.getCodigo() != null)
			this.entity.setCodigo(Helper.filterLike(this.filter.getCodigo()));
	}
	
	private void addDescricao() {
		if(this.filter.getDescricao() != null)
			this.entity.setDescricao(Helper.filterLike(this.filter.getDescricao()));
	}
}
