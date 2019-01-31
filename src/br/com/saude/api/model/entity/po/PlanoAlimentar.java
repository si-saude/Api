package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PlanoAlimentar {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="planoAlimentar", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<RefeicaoPlano> refeicoes;
	
	@NotNull(message="É necessário informar o Objetivo.")
	@Size(max = 1024, message="Tamanho máximo para Objetico: 1024")
	private String objetivo;
	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "atendimento_id")
	private Atendimento atendimento;
	
	private float ne;
	
	private float tmb;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<RefeicaoPlano> getRefeicoes() {
		return refeicoes;
	}

	public void setRefeicoes(List<RefeicaoPlano> refeicoes) {
		this.refeicoes = refeicoes;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public float getNe() {
		return ne;
	}

	public void setNe(float ne) {
		this.ne = ne;
	}

	public float getTmb() {
		return tmb;
	}

	public void setTmb(float tmb) {
		this.tmb = tmb;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
}
