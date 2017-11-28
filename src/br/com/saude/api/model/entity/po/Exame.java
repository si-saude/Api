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
public class Exame {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Código do Exame.")
	@Size(max = 10, message="Tamanho máximo para Código: 10")
	@Column(unique=true)
	private String codigo;
	
	
	@NotNull(message="É necessário informar a Descrição do Exame.")
	@Size(max = 150, message="Tamanho máximo para Descrição do Exame: 150")
	private String descricao;
	
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
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	
	@Override
	public boolean equals(Object exame) {
		return ((Exame)exame).id == this.id && this.id > 0;
	}
}
