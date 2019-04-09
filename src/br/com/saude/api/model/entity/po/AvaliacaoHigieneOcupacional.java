package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AvaliacaoHigieneOcupacional {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="É necessário informar o Início da Avaliação de Higiene Ocupacional.")
	private Date inicio;
	
	@NotNull(message="É necessário informar o Fim da Avaliação de Higiene Ocupacional.")
	private Date fim;

	@NotNull(message="É necessário informar o Profissional da Avaliação de Higiene Ocupacional.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Profissional profissional;
	
	@NotNull(message="É necessário informar o Empregado da Avaliação de Higiene Ocupacional.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Empregado empregado;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar a Gerência do Empregado.")
	private Gerencia gerencia;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private QuestionarioVedacaoMascara questionarioVedacaoMascara;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar a Função do Empregado.")
	private Cargo cargo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Ghe ghe;
	
	private String aprho;
	
	private boolean brigada;
	
	private boolean espacoConfinado;
	
	private boolean fiscalSopSg;
	
	private boolean opEcolEcomp;

	private boolean outros;
	
	private boolean usoVoluntario;
	
	private boolean ensaioVedacaoRealizado;
	
	@Size(max = 64, message="Tamanho máximo para Ensaio Vedação da Avaliação de Higiene Ocupacional.: 64")
	private String ensaioVedacao;
	
	private boolean naoBarbeado;
	
	private boolean naoUtilizaMascara;
	
	private boolean testeSensibilidadeInsatisfatorio;
	
	private boolean concordaDescricaoAprhoGhe;
	
	private boolean hOconcordaDescricaoAprhoGhe;
	
	private boolean naoConcordaAgentesRiscos;
	
	private boolean naoConcordaAtividades;
	
	private boolean naoConcordaFrequenciaExposicaoRiscos;
	
	private boolean naoConcordaCategoriaRiscos;
	
	@Size(max = 512, message="Tamanho máximo para Motivo da Análise Preliminar da Avaliação de Higiene Ocupacional.: 512")
	private String motivoAnalisePreliminar;
	
	@Size(max = 512, message="Tamanho máximo para Observação. : 512")
	private String observacaoGHE;
	
	@Size(max = 512, message="Tamanho máximo para Observação.: 512")
	private String justificativaHO;
	
	
	@Size(max = 512, message="Tamanho máximo para Observação da Avaliação de Higiene Ocupacional.: 512")
	private String observacao;
	
	@Version
	private long version;

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public boolean isBrigada() {
		return brigada;
	}

	public void setBrigada(boolean brigada) {
		this.brigada = brigada;
	}

	public boolean isEspacoConfinado() {
		return espacoConfinado;
	}

	public void setEspacoConfinado(boolean espacoConfinado) {
		this.espacoConfinado = espacoConfinado;
	}

	public boolean isUsoVoluntario() {
		return usoVoluntario;
	}

	public void setUsoVoluntario(boolean usoVoluntario) {
		this.usoVoluntario = usoVoluntario;
	}

	public boolean isNaoBarbeado() {
		return naoBarbeado;
	}

	public void setNaoBarbeado(boolean naoBarbeado) {
		this.naoBarbeado = naoBarbeado;
	}

	public boolean isNaoUtilizaMascara() {
		return naoUtilizaMascara;
	}

	public void setNaoUtilizaMascara(boolean naoUtilizaMascara) {
		this.naoUtilizaMascara = naoUtilizaMascara;
	}

	public boolean isTesteSensibilidadeInsatisfatorio() {
		return testeSensibilidadeInsatisfatorio;
	}

	public void setTesteSensibilidadeInsatisfatorio(boolean testeSensibilidadeInsatisfatorio) {
		this.testeSensibilidadeInsatisfatorio = testeSensibilidadeInsatisfatorio;
	}
	
	public String getEnsaioVedacao() {
		return ensaioVedacao;
	}

	public void setEnsaioVedacao(String ensaioVedacao) {
		this.ensaioVedacao = ensaioVedacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public boolean isConcordaDescricaoAprhoGhe() {
		return concordaDescricaoAprhoGhe;
	}

	public void setConcordaDescricaoAprhoGhe(boolean concordaDescricaoAprhoGhe) {
		this.concordaDescricaoAprhoGhe = concordaDescricaoAprhoGhe;
	}

	public boolean isNaoConcordaAgentesRiscos() {
		return naoConcordaAgentesRiscos;
	}

	public void setNaoConcordaAgentesRiscos(boolean naoConcordaAgentesRiscos) {
		this.naoConcordaAgentesRiscos = naoConcordaAgentesRiscos;
	}

	public boolean isNaoConcordaAtividades() {
		return naoConcordaAtividades;
	}

	public void setNaoConcordaAtividades(boolean naoConcordaAtividades) {
		this.naoConcordaAtividades = naoConcordaAtividades;
	}

	public boolean isNaoConcordaFrequenciaExposicaoRiscos() {
		return naoConcordaFrequenciaExposicaoRiscos;
	}

	public void setNaoConcordaFrequenciaExposicaoRiscos(boolean naoConcordaFrequenciaExposicaoRiscos) {
		this.naoConcordaFrequenciaExposicaoRiscos = naoConcordaFrequenciaExposicaoRiscos;
	}

	public boolean isNaoConcordaCategoriaRiscos() {
		return naoConcordaCategoriaRiscos;
	}

	public void setNaoConcordaCategoriaRiscos(boolean naoConcordaCategoriaRiscos) {
		this.naoConcordaCategoriaRiscos = naoConcordaCategoriaRiscos;
	}

	public String getMotivoAnalisePreliminar() {
		return motivoAnalisePreliminar;
	}

	public void setMotivoAnalisePreliminar(String motivoAnalisePreliminar) {
		this.motivoAnalisePreliminar = motivoAnalisePreliminar;
	}

	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}
	
	public QuestionarioVedacaoMascara getQuestionarioVedacaoMascara() {
		return questionarioVedacaoMascara;
	}

	public void setQuestionarioVedacaoMascara(QuestionarioVedacaoMascara questionarioVedacaoMascara) {
		this.questionarioVedacaoMascara = questionarioVedacaoMascara;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Ghe getGhe() {
		return ghe;
	}

	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}

	public String getAprho() {
		return aprho;
	}

	public void setAprho(String aprho) {
		this.aprho = aprho;
	}

	public boolean isFiscalSopSg() {
		return fiscalSopSg;
	}

	public void setFiscalSopSg(boolean fiscalSopSg) {
		this.fiscalSopSg = fiscalSopSg;
	}

	public boolean isOpEcolEcomp() {
		return opEcolEcomp;
	}

	public void setOpEcolEcomp(boolean opEcolEcomp) {
		this.opEcolEcomp = opEcolEcomp;
	}

	public boolean isOutros() {
		return outros;
	}

	public void setOutros(boolean outros) {
		this.outros = outros;
	}

	public boolean isEnsaioVedacaoRealizado() {
		return ensaioVedacaoRealizado;
	}

	public void setEnsaioVedacaoRealizado(boolean ensaioVedacaoRealizado) {
		this.ensaioVedacaoRealizado = ensaioVedacaoRealizado;
	}

	public String getObservacaoGHE() {
		return observacaoGHE;
	}

	public void setObservacaoGHE(String observacaoGHE) {
		this.observacaoGHE = observacaoGHE;
	}

	public boolean isHOconcordaDescricaoAprhoGhe() {
		return hOconcordaDescricaoAprhoGhe;
	}

	public void setHOconcordaDescricaoAprhoGhe(boolean hOconcordaDescricaoAprhoGhe) {
		this.hOconcordaDescricaoAprhoGhe = hOconcordaDescricaoAprhoGhe;
	}

	public String getJustificativaHO() {
		return justificativaHO;
	}

	public void setJustificativaHO(String justificativaHO) {
		this.justificativaHO = justificativaHO;
	}		
}
