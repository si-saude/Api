package br.com.saude.api.model.entity.filter;


import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class AprhoFilter extends GenericFilter {

	private String empresa;
	
	private DateFilter data;
	private GheFilter ghe;

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public DateFilter getData() {
		return data;
	}
	public void setData(DateFilter data) {
		this.data = data;
	}
	
	public GheFilter getGhe() {
		return ghe;
	}
	public void setGhe(GheFilter ghe) {
		this.ghe = ghe;
	}
}
