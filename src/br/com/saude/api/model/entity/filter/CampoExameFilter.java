package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class CampoExameFilter extends GenericFilter {
	private ExameFilter exame;
	private String codigo;
	private String titulo;
	private int numeroLinhas;
	
	public ExameFilter getExame() {
		return exame;
	}
	public void setExame(ExameFilter exame) {
		this.exame = exame;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getNumeroLinhas() {
		return numeroLinhas;
	}
	public void setNumeroLinhas(int numeroLinhas) {
		this.numeroLinhas = numeroLinhas;
	}
}
