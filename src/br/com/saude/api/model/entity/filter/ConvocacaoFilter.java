package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class ConvocacaoFilter extends GenericFilter {
	
	private String titulo;
	private String tipo;
	private ProfissiogramaFilter profissiograma;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public ProfissiogramaFilter getProfissiograma() {
		return profissiograma;
	}
	public void setProfissiograma(ProfissiogramaFilter profissiograma) {
		this.profissiograma = profissiograma;
	}
}
