package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ClassificacaoAfastamento {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar Descrição da Classificação do Afastamento.")
	@Size(max = 512, message="Tamanho máximo para Descrição da Classificação do Afastamento: 512")
	private String descricao;
	
	private boolean geraAfastamento;
	
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

	public boolean isGeraAfastamento() {
		return geraAfastamento;
	}

	public void setGeraAfastamento(boolean geraAfastamento) {
		this.geraAfastamento = geraAfastamento;
	}
}
