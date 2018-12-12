package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class AtividadeFisicaFilter extends GenericFilter {
	private String descricao;
	private BooleanFilter domingo;
	private BooleanFilter segunda;
	private BooleanFilter terca;
	private BooleanFilter quarta;
	private BooleanFilter quinta;
	private BooleanFilter sexta;
	private BooleanFilter sabado;
	private String classificacao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BooleanFilter getDomingo() {
		return domingo;
	}
	public void setDomingo(BooleanFilter domingo) {
		this.domingo = domingo;
	}
	public BooleanFilter getSegunda() {
		return segunda;
	}
	public void setSegunda(BooleanFilter segunda) {
		this.segunda = segunda;
	}
	public BooleanFilter getTerca() {
		return terca;
	}
	public void setTerca(BooleanFilter terca) {
		this.terca = terca;
	}
	public BooleanFilter getQuarta() {
		return quarta;
	}
	public void setQuarta(BooleanFilter quarta) {
		this.quarta = quarta;
	}
	public BooleanFilter getQuinta() {
		return quinta;
	}
	public void setQuinta(BooleanFilter quinta) {
		this.quinta = quinta;
	}
	public BooleanFilter getSexta() {
		return sexta;
	}
	public void setSexta(BooleanFilter sexta) {
		this.sexta = sexta;
	}
	public BooleanFilter getSabado() {
		return sabado;
	}
	public void setSabado(BooleanFilter sabado) {
		this.sabado = sabado;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}	
}
