package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Funcao {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome da Função.")
	@Size(max = 120, message="Tamanho máximo para Nome da Função: 120")
	private String nome;
	
	@Size(max = 1024, message="Tamanho máximo para Cursos Obrigatórios da Função: 1024")
	private String cursosObrigatorios;
	
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

	public String getCursosObrigatorios() {
		return cursosObrigatorios;
	}

	public void setCursosObrigatorios(String cursosObrigatorios) {
		this.cursosObrigatorios = cursosObrigatorios;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
