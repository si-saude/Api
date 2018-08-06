package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class HomologacaoAtestado {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private Date dataEntrega;
	
	private Date dataHomologacao;
	
	@Size(max = 32, message="Tamanho máximo para Status da Homologação: 32")
	private String status;
	
	@Size(max = 2056, message="Tamanho máximo para Observação da Homologação: 2056")
	private String observacao;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "atestado_id")
	@NotNull(message="É necessário informar o Atestado da Homologação.")
	private Atestado atestado;
	
	@NotNull(message="É necessário informar o Empregado do ASO.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Profissional profissional;

	@Version
	private long version;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataHomologacao() {
		return dataHomologacao;
	}

	public void setDataHomologacao(Date dataHomologacao) {
		this.dataHomologacao = dataHomologacao;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Atestado getAtestado() {
		return atestado;
	}

	public void setAtestado(Atestado atestado) {
		this.atestado = atestado;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
