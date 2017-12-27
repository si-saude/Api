package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Demanda {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="� necess�rio informar o Servi�o da Demanda.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Servico servico;
	
	@NotNull(message="� necess�rio informar a Equipe da Demanda.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Equipe equipe;
	
	@Min(value=1, message="Valor m�nimo para Tempo M�dio da Demanda: 1")
	private int tempoMedio;
	
	@Version
	private long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public int getTempoMedio() {
		return tempoMedio;
	}

	public void setTempoMedio(int tempoMedio) {
		this.tempoMedio = tempoMedio;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
