package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class RegraAtendimentoEquipe {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Regra de Atendimento da Equipe.")
	@ManyToOne(fetch=FetchType.LAZY)
	private RegraAtendimento regraAtendimento;
	
	@NotNull(message="É necessário informar a Equipe da Regra.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Equipe equipe;
	
	private boolean acolhimento;
	
	@OneToMany(mappedBy="regraAtendimentoEquipe", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<RegraAtendimentoEquipeRequisito> regraAtendimentoEquipeRequisitos;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RegraAtendimento getRegraAtendimento() {
		return regraAtendimento;
	}

	public void setRegraAtendimento(RegraAtendimento regraAtendimento) {
		this.regraAtendimento = regraAtendimento;
	}
	
	public List<RegraAtendimentoEquipeRequisito> getRegraAtendimentoEquipeRequisitos() {
		return regraAtendimentoEquipeRequisitos;
	}

	public void setRegraAtendimentoEquipeRequisitos(
			List<RegraAtendimentoEquipeRequisito> regraAtendimentoEquipeRequisitos) {
		this.regraAtendimentoEquipeRequisitos = regraAtendimentoEquipeRequisitos;
	}
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public boolean isAcolhimento() {
		return acolhimento;
	}

	public void setAcolhimento(boolean acolhimento) {
		this.acolhimento = acolhimento;
	}
	
}
