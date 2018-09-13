package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class RequisitoAso {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Conteúdo do Requisito.")
	@Size(max = 50, message="Tamanho máximo para Conteúdo do Requisito: 50")
	private String conteudo;
	
	@Version
	private long version;
	
	@Transient
	private boolean conforme;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public boolean isConforme() {
		return conforme;
	}

	public void setConforme(boolean conforme) {
		this.conforme = conforme;
	}
}
