package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Gerencia {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar Código da Gerência.")
	@Size(max = 10, message="Tamanho máximo para Código da Gerência: 10")
	private String codigo;
	
	@Transient
	private String codigoCompleto;
	
	@Size(max = 200, message="Tamanho máximo para Descrição da Gerência: 200")
	private String descricao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Gerencia gerencia;
	
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

	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	public String getCodigoCompleto() {
		if(this.codigoCompleto == null)
			this.codigoCompleto = getCodigoCompleto(codigo);
		return this.codigoCompleto;
	}
	
	private String getCodigoCompleto(String codigo) {
		if(this.gerencia!=null)
			return this.gerencia.getCodigoCompleto(this.gerencia.codigo+"/"+codigo);
		return codigo;
	}
}
