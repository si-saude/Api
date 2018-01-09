package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CampoExame {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Exame de Campo.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Exame exame;
	
	@Size(max = 3, message="Tamanho máximo para Código do Campo: 3")
	private String codigo;

	@Size(max = 150, message="Tamanho máximo para Título do Campo: 150")
	private String titulo;
	
	private int numeroLinhas;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getNumeroLinhas() {
		return numeroLinhas;
	}

	public void setNumeroLinhas(int numeroLinhas) {
		this.numeroLinhas = numeroLinhas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
