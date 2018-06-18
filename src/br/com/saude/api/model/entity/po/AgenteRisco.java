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
public class AgenteRisco {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar Descrição do Agente de Risco.")
	@Size(max = 512, message="Tamanho máximo para Descrição do Agente de Risco: 512")
	@Column(unique=true)
	private String descricao;
	
	@NotNull(message="É necessário informar a Categoria do Agente de Risco.")
	@Size(max = 32, message="Tamanho máximo para Categoria do Agente de Risco: 32")
	private String categoriaAgenteRisco;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCategoriaAgenteRisco() {
		return categoriaAgenteRisco;
	}

	public void setCategoriaAgenteRisco(String categoriaAgenteRisco) {
		this.categoriaAgenteRisco = categoriaAgenteRisco;
	}
	
}
