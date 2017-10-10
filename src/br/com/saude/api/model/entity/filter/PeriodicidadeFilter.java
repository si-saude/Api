package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class PeriodicidadeFilter extends GenericFilter  {

	private String descricao;
	private int meses;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getMeses() {
		return meses;
	}

	public void setMeses(int meses) {
		this.meses = meses;
	}
}
