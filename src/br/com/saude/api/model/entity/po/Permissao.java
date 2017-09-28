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
public class Permissao {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Size(max = 100)
	private String funcionalidade;
	
	private boolean leitura;
	
	private boolean escrita;
	
	@NotNull(message="� necess�rio informar o Perfil da Permiss�o.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Perfil perfil;
	
	@Version
	private long version;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isLeitura() {
		return leitura;
	}
	public void setLeitura(boolean leitura) {
		this.leitura = leitura;
	}
	public boolean isEscrita() {
		return escrita;
	}
	public void setEscrita(boolean escrita) {
		this.escrita = escrita;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getFuncionalidade() {
		return funcionalidade;
	}
	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}
