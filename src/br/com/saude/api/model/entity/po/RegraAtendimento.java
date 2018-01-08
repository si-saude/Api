package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class RegraAtendimento {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar Nome da Regra de Atendimento.")
	@Size(max = 64, message="Tamanho máximo para Nome da Regra de Atendimento: 64")
	@Column(unique=true)
	private String nome;
	
	@OneToMany(mappedBy="regraAtendimento", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<RegraAtendimentoEquipe> regraAtendimentoEquipes;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<RegraAtendimentoEquipe> getRegraAtendimentoEquipes() {
		return regraAtendimentoEquipes;
	}

	public void setRegraAtendimentoEquipes(List<RegraAtendimentoEquipe> regraAtendimentoEquipes) {
		this.regraAtendimentoEquipes = regraAtendimentoEquipes;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
