package br.com.saude.api.model.entity.po;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class Atendimento {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@NotNull(message="� necess�rio informar a Fila de Atendimento.")
	private FilaAtendimentoOcupacional filaAtendimentoOcupacional;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@NotNull(message="� necess�rio informar a Fila de Espera.")
	private FilaEsperaOcupacional filaEsperaOcupacional;
	
	@NotNull(message="� necess�rio informar a Tarefa.")
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Tarefa tarefa;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Aso aso;
	
	@Transient
	private RegraAtendimento regra;
	
	@Version
	private long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public FilaAtendimentoOcupacional getFilaAtendimentoOcupacional() {
		return filaAtendimentoOcupacional;
	}

	public void setFilaAtendimentoOcupacional(FilaAtendimentoOcupacional filaAtendimentoOcupacional) {
		this.filaAtendimentoOcupacional = filaAtendimentoOcupacional;
	}

	public FilaEsperaOcupacional getFilaEsperaOcupacional() {
		return filaEsperaOcupacional;
	}

	public void setFilaEsperaOcupacional(FilaEsperaOcupacional filaEsperaOcupacional) {
		this.filaEsperaOcupacional = filaEsperaOcupacional;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public RegraAtendimento getRegra() {
		return regra;
	}

	public void setRegra(RegraAtendimento regra) {
		this.regra = regra;
	}

	public Aso getAso() {
		return aso;
	}

	public void setAso(Aso aso) {
		this.aso = aso;
	}
}