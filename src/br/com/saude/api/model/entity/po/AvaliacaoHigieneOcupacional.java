package br.com.saude.api.model.entity.po;

import java.util.Date;

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
	
	@NotNull(message="� necess�rio informar a Data da Avalia��o de Higiene Ocupacional.")
	private Date data;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Localizacao local;
	
	@NotNull(message="� necess�rio informar o T�cnico da Avalia��o de Higiene Ocupacional.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Profissional tecnico; 
	
	@NotNull(message="� necess�rio informar o In�cio da Avalia��o de Higiene Ocupacional.")
	private Date inicio;
	
	@NotNull(message="� necess�rio informar o Fim da Avalia��o de Higiene Ocupacional.")
	private Date fim;
	
	@NotNull(message="� necess�rio informar o Empregado da Avalia��o de Higiene Ocupacional.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Empregado empregado;
	
	private boolean brigada;
	
	private boolean espacoConfinado;
	
	private boolean usoVoluntario;
	
	@Size(max = 64, message="Tamanho m�ximo para Ensaio Veda��o da Avalia��o de Higiene Ocupacional.: 64")
	private String ensaioVedacao;
	
	private boolean naoBarbeado;
	
	private boolean naoUtilizaMascara;
	
	private boolean testeSensibilidadeInsatisfatorio;
	
	private boolean concordaDescricaoAprhoGhe;
	
	private boolean naoConcordaAgentesRiscos;
	
	private boolean naoConcordaAtividades;
	
	private boolean naoConcordaFrequenciaExposicaoRiscos;
	
	private boolean naoConcordaCategoriaRiscos;
	
	@Size(max = 512, message="Tamanho m�ximo para Motivo da An�lise Preliminar da Avalia��o de Higiene Ocupacional.: 512")
	private String motivoAnalisePreliminar;
	
	@Size(max = 512, message="Tamanho m�ximo para Observa��o da Avalia��o de Higiene Ocupacional.: 512")
	private String observacao;
	
	@Version
	private long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Localizacao getLocal() {
		return local;
	}

	public void setLocal(Localizacao local) {
		this.local = local;
	}

	public Profissional getTecnico() {
		return tecnico;
	}

	public void setTecnico(Profissional tecnico) {
		this.tecnico = tecnico;
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
}
