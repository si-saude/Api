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
public class Localizacao {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome da Localização.")
	@Size(max = 50, message="Tamanho máximo para Nome da Localização: 50")
	@Column(unique=true)
	private String nome;
	
	@OneToMany(mappedBy="localizacao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<RegraAtendimentoLocalizacao> regraAtendimentoLocalizacoes;
	
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
	
	public List<RegraAtendimentoLocalizacao> getRegraAtendimentoLocalizacoes() {
		return regraAtendimentoLocalizacoes;
	}

	public void setRegraAtendimentoLocalizacoes(List<RegraAtendimentoLocalizacao> regraAtendimentoLocalizacoes) {
		this.regraAtendimentoLocalizacoes = regraAtendimentoLocalizacoes;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
