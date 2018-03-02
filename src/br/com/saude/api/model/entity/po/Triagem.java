package br.com.saude.api.model.entity.po;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class Triagem {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar o Indicador Sast da Triagem.")
	private IndicadorSast indicadorSast;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Atendimento atendimento;
	
	private int indice = -1;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private RiscoEmpregado riscoEmpregado;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IndicadorSast getIndicadorSast() {
		return indicadorSast;
	}

	public void setIndicadorSast(IndicadorSast indicadorSast) {
		this.indicadorSast = indicadorSast;
	}
	
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public RiscoEmpregado getRiscoEmpregado() {
		return riscoEmpregado;
	}

	public void setRiscoEmpregado(RiscoEmpregado riscoEmpregado) {
		this.riscoEmpregado = riscoEmpregado;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
