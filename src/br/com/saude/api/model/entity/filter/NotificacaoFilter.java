package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class NotificacaoFilter extends GenericFilter {

	private EquipeFilter equipe;
	private String descricao;
	
	public EquipeFilter getEquipe() {
		return equipe;
	}
	
	public void setEquipe(EquipeFilter equipe) {
		this.equipe = equipe;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
