package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.GenericFilter;

public class IndicadorRiscoFilter extends GenericFilter {

	private String nome;
	private String indice0;
	private String indice1;
	private String indice2;
	private String indice3;
	private String indice4;
	private String indice5;
	private PeriodicidadeFilter periodicidade;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndice0() {
		return indice0;
	}
	public void setIndice0(String indice0) {
		this.indice0 = indice0;
	}
	public String getIndice1() {
		return indice1;
	}
	public void setIndice1(String indice1) {
		this.indice1 = indice1;
	}
	public String getIndice2() {
		return indice2;
	}
	public void setIndice2(String indice2) {
		this.indice2 = indice2;
	}
	public String getIndice3() {
		return indice3;
	}
	public void setIndice3(String indice3) {
		this.indice3 = indice3;
	}
	public String getIndice4() {
		return indice4;
	}
	public void setIndice4(String indice4) {
		this.indice4 = indice4;
	}
	public String getIndice5() {
		return indice5;
	}
	public void setIndice5(String indice5) {
		this.indice5 = indice5;
	}
	public PeriodicidadeFilter getPeriodicidade() {
		return periodicidade;
	}
	public void setPeriodicidade(PeriodicidadeFilter periodicidade) {
		this.periodicidade = periodicidade;
	}
}
