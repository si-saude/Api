package br.com.saude.api.model.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ParteCorpoAtingida {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 16, message="Tamanho m�ximo para o C�digo da Parte do Corpo Atingida: 16")
	@NotNull(message="� necess�rio informar o C�digo da Parte do Corpo Atingida.")
	@Column(unique = true)
	private String codigo;
	
	@Size(max = 64, message="Tamanho m�ximo para a Descri��o da Parte do Corpo Atingida: 64")
	@NotNull(message="� necess�rio informar a Descri��o da Parte do Corpo Atingida.")
	private String descricao;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
