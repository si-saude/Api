package br.com.saude.api.model.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class NaturezaLesao {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Código da Natureza da Lesão.")
	@Column(unique = true)
	private String codigo;
	
	@NotNull(message="É necessário informar a Descrição da Natureza da Lesão.")
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
