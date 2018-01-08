package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class ItemResultadoExameFilter extends GenericFilter {
	private ResultadoExameFilter resultadoExame;
	private String titulo;
	private String resultado;
	
	public ResultadoExameFilter getResultadoExame() {
		return resultadoExame;
	}
	public void setResultadoExame(ResultadoExameFilter resultadoExame) {
		this.resultadoExame = resultadoExame;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
