package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class ProfissiogramaFilter extends GenericFilter {

	private String nome;
	private BooleanFilter concluido;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BooleanFilter getConcluido() {
		return concluido;
	}

	public void setConcluido(BooleanFilter concluido) {
		this.concluido = concluido;
	}
}
