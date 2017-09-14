package br.com.saude.api.generic;

import java.util.Date;

public class DateFilter {
	private Date inicio;
	private Date fim;
	private TypeFilter typeFilter;
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public TypeFilter getTypeFilter() {
		return typeFilter;
	}
	public void setTypeFilter(TypeFilter typeFilter) {
		this.typeFilter = typeFilter;
	}
}
