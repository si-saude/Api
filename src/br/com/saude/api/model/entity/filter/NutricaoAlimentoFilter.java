package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class NutricaoAlimentoFilter extends GenericFilter {

	private BooleanFilter inativo;
	private String nome;
	
	public BooleanFilter getInativo() {
		return inativo;
	}
	
	public void setInativo(BooleanFilter inativo) {
		this.inativo = inativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
