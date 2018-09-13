package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class ImovelFilter extends GenericFilter {
	private String nome;
	private BaseFilter base;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BaseFilter getBase() {
		return base;
	}
	
	public void setBase(BaseFilter base) {
		this.base = base;
	}
}
