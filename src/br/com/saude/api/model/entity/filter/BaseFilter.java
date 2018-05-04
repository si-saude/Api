package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class BaseFilter extends GenericFilter {

	private String nome;
	private String uf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
}
