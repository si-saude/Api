package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class IntervencaoFilter extends GenericFilter {
	private String descricao;
	private EquipeFilter equipe;
	private BooleanFilter inativo;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public EquipeFilter getEquipe() {
		return equipe;
	}
	public void setEquipe(EquipeFilter equipe) {
		this.equipe = equipe;
	}
	public BooleanFilter getInativo() {
		return inativo;
	}
	public void setInativo(BooleanFilter inativo) {
		this.inativo = inativo;
	}

}
