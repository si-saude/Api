package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class EixoFilter extends GenericFilter {
	private String titulo;
	private EquipeFilter equipe;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public EquipeFilter getEquipe() {
		return equipe;
	}
	
	public void setEquipe(EquipeFilter equipe) {
		this.equipe = equipe;
	}
		
}
