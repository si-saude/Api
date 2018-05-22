package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class CategoriaRiscoFilter extends GenericFilter {

	private String descricao;
	private String observacao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
