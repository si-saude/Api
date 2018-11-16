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
public class Profissiograma {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar Nome do Profissiograma.")
	@Size(max = 128, message="Tamanho máximo para Nome do Profissiograma: 128")
	@Column(unique=true)
	private String nome;
	
	private boolean concluido;
	
	@OneToMany(mappedBy="profissiograma", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<GrupoMonitoramentoProfissiograma> grupoMonitoramentoProfissiogramas;
	
	@Size(max = 32, message="Tamanho máximo para Vínculo de Profissiograma: 32")
	@NotNull(message="É necessário informar o Vínculo de Profissiograma.")
	private String vinculo;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVinculo() {
		return vinculo;
	}
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public List<GrupoMonitoramentoProfissiograma> getGrupoMonitoramentoProfissiogramas() {
		return grupoMonitoramentoProfissiogramas;
	}

	public void setGrupoMonitoramentoProfissiogramas(
			List<GrupoMonitoramentoProfissiograma> grupoMonitoramentoProfissiogramas) {
		this.grupoMonitoramentoProfissiogramas = grupoMonitoramentoProfissiogramas;
	}
}
