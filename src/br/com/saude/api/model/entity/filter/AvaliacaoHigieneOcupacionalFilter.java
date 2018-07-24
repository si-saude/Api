package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.GenericFilter;

public class AvaliacaoHigieneOcupacionalFilter extends GenericFilter {
	private EmpregadoFilter empregado;
	
	private BooleanFilter brigada;
	private BooleanFilter espacoConfinado;
	private BooleanFilter usoVoluntario;
	private BooleanFilter naoBarbeado;
	private BooleanFilter naoUtilizaMascara;
	private BooleanFilter testeSensibilidadeInsatisfatorio;
	private BooleanFilter ensaioVedacao;
	
	private BooleanFilter concordaDescricaoAprhoGhe;
	private BooleanFilter naoConcordaAgentesRiscos;
	private BooleanFilter naoConcordaAtividades;
	private BooleanFilter naoConcordaFrequenciaExposicaoRiscos;
	private BooleanFilter naoConcordaCategoriaRiscos;
	
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public BooleanFilter isNaoConcordaCategoriaRiscos() {
		return naoConcordaCategoriaRiscos;
	}
	public void setNaoConcordaCategoriaRiscos(BooleanFilter naoConcordaCategoriaRiscos) {
		this.naoConcordaCategoriaRiscos = naoConcordaCategoriaRiscos;
	}
	public BooleanFilter isNaoConcordaFrequenciaExposicaoRiscos() {
		return naoConcordaFrequenciaExposicaoRiscos;
	}
	public void setNaoConcordaFrequenciaExposicaoRiscos(BooleanFilter naoConcordaFrequenciaExposicaoRiscos) {
		this.naoConcordaFrequenciaExposicaoRiscos = naoConcordaFrequenciaExposicaoRiscos;
	}
	public BooleanFilter isNaoConcordaAtividades() {
		return naoConcordaAtividades;
	}
	public void setNaoConcordaAtividades(BooleanFilter naoConcordaAtividades) {
		this.naoConcordaAtividades = naoConcordaAtividades;
	}
	public BooleanFilter isNaoConcordaAgentesRiscos() {
		return naoConcordaAgentesRiscos;
	}
	public void setNaoConcordaAgentesRiscos(BooleanFilter naoConcordaAgentesRiscos) {
		this.naoConcordaAgentesRiscos = naoConcordaAgentesRiscos;
	}
	public BooleanFilter isConcordaDescricaoAprhoGhe() {
		return concordaDescricaoAprhoGhe;
	}
	public void setConcordaDescricaoAprhoGhe(BooleanFilter concordaDescricaoAprhoGhe) {
		this.concordaDescricaoAprhoGhe = concordaDescricaoAprhoGhe;
	}
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
