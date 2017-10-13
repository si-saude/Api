package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.GheFilter;
import br.com.saude.api.model.entity.po.Ghe;

public class GheExampleBuilder extends GenericExampleBuilder<Ghe, GheFilter>{

	public static GheExampleBuilder newInstance(GheFilter filter) {
		return new GheExampleBuilder(filter);
	}
	
	private GheExampleBuilder(GheFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addCodigo();
		addDataCriacao();
		addDataDesativacao();
		addDescricao();
		addDescricaoAmbiente();
		addDescricaoTarefas();
		addDuracaoJornada();
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
	
	private void addDescricaoAmbiente() {
		if(this.filter.getDescricaoAmbiente() != null)
			this.entity.setDescricaoAmbiente(Helper.filterLike(this.filter.getDescricaoAmbiente()));
	}
	
	private void addDescricaoTarefas() {
		if(this.filter.getDescricaoTarefas() != null)
			this.entity.setDescricaoTarefas(Helper.filterLike(this.filter.getDescricaoTarefas()));
	}
	
	private void addDuracaoJornada() {
		if(this.filter.getDuracaoJornada() > 0)
			this.entity.setDuracaoJornada(this.filter.getDuracaoJornada());
	}

}
