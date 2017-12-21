package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class FeriadoFilter extends GenericFilter {
	private String titulo;
	private DateFilter data;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public DateFilter getData() {
		return data;
	}
	
	public void setData(DateFilter data) {
		this.data = data;
	}
}
