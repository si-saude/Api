package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class EmpresaFilter extends GenericFilter {
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}
