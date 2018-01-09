package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class EquipeFilter extends GenericFilter {
	private String nome;
	private String abreviacao;
	private ProfissionalFilter coordenador;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	
	public ProfissionalFilter getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(ProfissionalFilter coordenador) {
		this.coordenador = coordenador;
	}
	
}
