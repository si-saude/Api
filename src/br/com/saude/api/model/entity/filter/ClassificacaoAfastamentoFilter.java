package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class ClassificacaoAfastamentoFilter extends GenericFilter {
	private String descricao;
	private BooleanFilter geraAfastamento;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BooleanFilter getGeraAfastamento() {
		return this.geraAfastamento;
	}
	public void setGeraAfastamento(BooleanFilter geraAfastamento) {
		this.geraAfastamento = geraAfastamento;
	}
}
