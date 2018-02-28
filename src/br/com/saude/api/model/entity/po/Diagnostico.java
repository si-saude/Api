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
public class Diagnostico {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 16, message="Tamanho máximo para Código do Diagnóstivo: 16")
	@NotNull(message="É necessário informar o Código do Diagnóstico.")
	private String codigo;
	
	@Size(max = 300, message="Tamanho máximo para Descrição do Diagnóstivo: 300")
	@NotNull(message="É necessário informar o Descrição do Diagnóstico.")
	private String descricao;
	
	@NotNull(message="É necessário informar o Inativo do Diagnóstico.")
	private boolean inativo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message="É necessário informar o Eixo do Diagnóstico.")
	private Eixo eixo;
	
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

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public Eixo getEixo() {
		return eixo;
	}

	public void setEixo(Eixo eixo) {
		this.eixo = eixo;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
		
}
