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
public class Tarefa {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="� necess�rio informar o Hor�rio da Tarefa.")
	private Date horario;
	
	private Date atualizacao;
	
	@NotNull(message="� necess�rio informar o Servi�o da Tarefa.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Servico servico;
	
	@NotNull(message="� necess�rio informar o Cliente da Tarefa.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Empregado cliente;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Profissional responsavel;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="� necess�rio informar a Equipe da Tarefa.")
	private Equipe equipe;
	
	@NotNull(message="� necess�rio informar o Status da Tarefa.")
	@Size(max = 64, message="Tamanho m�ximo para Status da Tarefa: 64")
	private String status;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Date getAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(Date atualizacao) {
		this.atualizacao = atualizacao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Empregado getCliente() {
		return cliente;
	}

	public void setCliente(Empregado cliente) {
		this.cliente = cliente;
	}

	public Profissional getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Profissional responsavel) {
		this.responsavel = responsavel;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
