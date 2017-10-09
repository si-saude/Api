package br.com.saude.api.model.entity.po;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class IndicadorRisco {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar Nome do Indicador de Risco.")
	@Size(max = 256, message="Tamanho máximo para Nome do Indicador de Risco: 256")
	@Column(unique=true)
	private String nome;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar a Periodicidade do Indicador de Risco.")
	private Periodicidade periodicidade;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 0 do Indicador de Risco: 1024")
	private String indice0;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 1 do Indicador de Risco: 1024")
	private String indice1;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 2 do Indicador de Risco: 1024")
	private String indice2;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 3 do Indicador de Risco: 1024")
	private String indice3;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 4 do Indicador de Risco: 1024")
	private String indice4;
	
	@Size(max = 1024, message="Tamanho máximo para Índice 5 do Indicador de Risco: 1024")
	private String indice5;
	
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

	public String getIndice0() {
		return indice0;
	}

	public void setIndice0(String indice0) {
		this.indice0 = indice0;
	}

	public String getIndice1() {
		return indice1;
	}

	public void setIndice1(String indice1) {
		this.indice1 = indice1;
	}

	public String getIndice2() {
		return indice2;
	}

	public void setIndice2(String indice2) {
		this.indice2 = indice2;
	}

	public String getIndice3() {
		return indice3;
	}

	public void setIndice3(String indice3) {
		this.indice3 = indice3;
	}

	public String getIndice4() {
		return indice4;
	}

	public void setIndice4(String indice4) {
		this.indice4 = indice4;
	}

	public String getIndice5() {
		return indice5;
	}

	public void setIndice5(String indice5) {
		this.indice5 = indice5;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Periodicidade getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(Periodicidade periodicidade) {
		this.periodicidade = periodicidade;
	}
}
