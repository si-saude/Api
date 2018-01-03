package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class RegraAtendimentoEquipeRequisito {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Equipe do Requisito.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Equipe equipe;
	
	@NotNull(message="É necessário informar a Regra de Atendimento do Requisito.")
	@ManyToOne(fetch=FetchType.LAZY)
	private RegraAtendimentoEquipe regraAtendimentoEquipe;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public RegraAtendimentoEquipe getRegraAtendimentoEquipe() {
		return regraAtendimentoEquipe;
	}

	public void setRegraAtendimentoEquipe(RegraAtendimentoEquipe regraAtendimentoEquipe) {
		this.regraAtendimentoEquipe = regraAtendimentoEquipe;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
