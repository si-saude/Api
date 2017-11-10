package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Telefone {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Número do Telefone.")
	@Size(max = 16, message="Tamanho máximo para Número do Telefone: 16")
	private String numero;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="telefones")
	private List<Fornecedor> fornecedores;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="telefones")
	private List<Pessoa> pessoas;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public boolean equals(Object telefone) {
		return ((Telefone)telefone).id == this.id && this.id > 0;
	}
}
