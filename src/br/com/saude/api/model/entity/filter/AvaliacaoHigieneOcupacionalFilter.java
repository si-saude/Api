package br.com.saude.api.model.entity.filter;

import br.com.saude.api.generic.BooleanFilter;
import br.com.saude.api.generic.DateFilter;
import br.com.saude.api.generic.GenericFilter;

public class AvaliacaoHigieneOcupacionalFilter extends GenericFilter {
	private EmpregadoFilter empregado;
	
	private DateFilter inicio;
	private DateFilter fim;
	private BooleanFilter brigada;
	private BooleanFilter espacoConfinado;
	private BooleanFilter usoVoluntario;
	private BooleanFilter naoBarbeado;
	private BooleanFilter naoUtilizaMascara;
	private BooleanFilter testeSensibilidadeInsatisfatorio;
	
	private BooleanFilter concordaDescricaoAprhoGhe;
	private BooleanFilter naoConcordaAgentesRiscos;
	private BooleanFilter naoConcordaAtividades;
	private BooleanFilter naoConcordaFrequenciaExposicaoRiscos;
	private BooleanFilter naoConcordaCategoriaRiscos;
	private BooleanFilter HOconcordaDescricaoAprhoGhe;
	
	private BooleanFilter fiscalSopSg;
	private BooleanFilter opEcolEcomp;
	private BooleanFilter outros;
	private BooleanFilter ensaioVedacaoRealizado;
	
	public EmpregadoFilter getEmpregado() {
		return empregado;
	}
	public void setEmpregado(EmpregadoFilter empregado) {
		this.empregado = empregado;
	}
	public BooleanFilter getHOconcordaDescricaoAprhoGhe() {
		return HOconcordaDescricaoAprhoGhe;
	}
	public void setHOconcordaDescricaoAprhoGhe(BooleanFilter hOconcordaDescricaoAprhoGhe) {
		HOconcordaDescricaoAprhoGhe = hOconcordaDescricaoAprhoGhe;
	}
	public BooleanFilter getFiscalSopSg() {
		return fiscalSopSg;
	}
	public void setFiscalSopSg(BooleanFilter fiscalSopSg) {
		this.fiscalSopSg = fiscalSopSg;
	}
	public BooleanFilter getOpEcolEcomp() {
		return opEcolEcomp;
	}
	public void setOpEcolEcomp(BooleanFilter opEcolEcomp) {
		this.opEcolEcomp = opEcolEcomp;
	}
	public BooleanFilter getOutros() {
		return outros;
	}
	public void setOutros(BooleanFilter outros) {
		this.outros = outros;
	}
	public BooleanFilter getEnsaioVedacaoRealizado() {
		return ensaioVedacaoRealizado;
	}
	public void setEnsaioVedacaoRealizado(BooleanFilter ensaioVedacaoRealizado) {
		this.ensaioVedacaoRealizado = ensaioVedacaoRealizado;
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
	public DateFilter getInicio() {
		return inicio;
	}
	public void setInicio(DateFilter inicio) {
		this.inicio = inicio;
	}
	public DateFilter getFim() {
		return fim;
	}
	public void setFim(DateFilter fim) {
		this.fim = fim;
	}
	
}
