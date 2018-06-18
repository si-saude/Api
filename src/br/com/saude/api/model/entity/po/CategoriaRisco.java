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
public class CategoriaRisco {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Descrição da Categoria de Risco.")
	@Size(max = 128, message="Tamanho máximo para Descrição da Categoria de Risco: 128")
	@Column(unique=true)
	private String descricao;
	
	@NotNull(message="É necessário informar a Observação da Categoria de Risco.")
	@Size(max = 128, message="Tamanho máximo para Observação da Categoria de Risco: 128")
	private String observacao;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}	
}
