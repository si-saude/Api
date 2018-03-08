package br.com.saude.api.model.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Equipe {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome da Equipe.")
	@Size(max = 50, message="Tamanho máximo para Nome da Equipe: 50")
	@Column(unique=true)
	private String nome;
	
	@Size(max = 3, message="Tamanho máximo para Abreviação da Equipe: 3")
	private String abreviacao;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Profissional coordenador;
	
	private int prioridadeSast;
	
	@Version
	private long version;
	
	@Override
	public boolean equals(Object equipe) {
		return ((Equipe)equipe).id == this.id && this.id > 0;
	}

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

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	
	public Profissional getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Profissional coordenador) {
		this.coordenador = coordenador;
	}
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public int getPrioridadeSast() {
		return prioridadeSast;
	}

	public void setPrioridadeSast(int prioridadeSast) {
		this.prioridadeSast = prioridadeSast;
	}
}
