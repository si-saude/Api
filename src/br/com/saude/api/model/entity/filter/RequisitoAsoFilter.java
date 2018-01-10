package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class RequisitoAsoFilter extends GenericFilter {

	private String conteudo;

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}
