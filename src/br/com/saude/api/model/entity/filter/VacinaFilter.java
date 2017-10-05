package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class VacinaFilter extends GenericFilter {
	private String descricao;
	private int doses;
	private int reforco;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getDoses() {
		return doses;
	}
	public void setDoses(int doses) {
		this.doses = doses;
	}
	public int getReforco() {
		return reforco;
	}
	public void setReforco(int reforco) {
		this.reforco = reforco;
	}
}
