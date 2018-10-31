package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Criterio {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar Nome do Critério.")
	@Size(max = 256, message="Tamanho máximo para Nome do Critério: 256")
	private String nome;
	
	@NotNull(message="É necessário informar Tipo do Critério.")
	@Size(max = 64, message="Tamanho máximo para Tipo do Critério: 64")
	private String tipo;
	
	@Size(max = 16, message="Tamanho máximo para Operador do Critério: 16")
	private String operador;
	
	@NotNull(message="É necessário informar Valor do Critério.")
	@Size(max = 64, message="Tamanho máximo para Valor do Critério: 64")
	private String valor;
	
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}
	
	
}
