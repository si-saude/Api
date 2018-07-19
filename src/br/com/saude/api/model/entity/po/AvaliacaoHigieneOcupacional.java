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
	
	@NotNull(message="É necessário informar a Data da Avaliação de Higiene Ocupacional.")
	private Date data;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Localizacao local;
	
	@NotNull(message="É necessário informar o Técnico da Avaliação de Higiene Ocupacional.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Profissional tecnico; 
	
	@NotNull(message="É necessário informar o Início da Avaliação de Higiene Ocupacional.")
	private Date inicio;
	
	@NotNull(message="É necessário informar o Fim da Avaliação de Higiene Ocupacional.")
	private Date fim;
	
	@NotNull(message="É necessário informar o Empregado da Avaliação de Higiene Ocupacional.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Empregado empregado;
	
	private boolean brigada;
	
	private boolean espacoConfinado;
	
	private boolean usoVoluntario;
	
	private String ensaioVedacao;
	
	private boolean naoBarbeado;
	
	private boolean naoUtilizaMascara;
	
	private boolean testeSensibilidadeInsatisfatorio;
	
	@Size(max = 512, message="Tamanho máximo para Observação da Avaliação de Higiene Ocupacional.: 512")
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
	
}
