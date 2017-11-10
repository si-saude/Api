package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class GerenciaConvocacao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Gerência da Convocação.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Gerencia gerencia;
	
	@NotNull(message="É necessário informar a Convocação da Gerência.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Convocacao convocacao;
	
	@NotNull(message="É necessário informar o Início do Cronograma.")
	private Date inicio;
	
	@NotNull(message="É necessário informar o Fim do Cronograma.")
	private Date fim;
	
	@Transient
	private boolean selecionado;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public Convocacao getConvocacao() {
		return convocacao;
	}

	public void setConvocacao(Convocacao convocacao) {
		this.convocacao = convocacao;
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

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
