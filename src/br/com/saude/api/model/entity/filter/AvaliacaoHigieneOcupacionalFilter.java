package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class AvaliacaoHigieneOcupacionalFilter extends GenericFilter {
	private BooleanFilter brigada;
	private BooleanFilter espacoConfinado;
	private BooleanFilter usoVoluntario;
	private BooleanFilter naoBarbeado;
	private BooleanFilter naoUtilizaMascara;
	private BooleanFilter testeSensibilidadeInsatisfatorio;
	private BooleanFilter ensaioVedacao;
	
	public BooleanFilter isBrigada() {
		return brigada;
	}
	public void setBrigada(BooleanFilter brigada) {
		this.brigada = brigada;
	}
	public BooleanFilter isEnsaioVedacao() {
		return ensaioVedacao;
	}
	public void setEnsaioVedacao(BooleanFilter ensaioVedacao) {
		this.ensaioVedacao = ensaioVedacao;
	}
	public BooleanFilter isEspacoConfinado() {
		return espacoConfinado;
	}
	public void setEspacoConfinado(BooleanFilter espacoConfinado) {
		this.espacoConfinado = espacoConfinado;
	}
	public BooleanFilter isUsoVoluntario() {
		return usoVoluntario;
	}
	public void setUsoVoluntario(BooleanFilter usoVoluntario) {
		this.usoVoluntario = usoVoluntario;
	}
	public BooleanFilter isNaoBarbeado() {
		return naoBarbeado;
	}
	public void setNaoBarbeado(BooleanFilter naoBarbeado) {
		this.naoBarbeado = naoBarbeado;
	}
	public BooleanFilter isNaoUtilizaMascara() {
		return naoUtilizaMascara;
	}
	public void setNaoUtilizaMascara(BooleanFilter naoUtilizaMascara) {
		this.naoUtilizaMascara = naoUtilizaMascara;
	}
	public BooleanFilter isTesteSensibilidadeInsatisfatorio() {
		return testeSensibilidadeInsatisfatorio;
	}
	public void setTesteSensibilidadeInsatisfatorio(BooleanFilter testeSensibilidadeInsatisfatorio) {
		this.testeSensibilidadeInsatisfatorio = testeSensibilidadeInsatisfatorio;
	}
}
