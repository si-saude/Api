package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
public class Email {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="email_empregado", 
	joinColumns = {@JoinColumn(name="email_id")}, 
	inverseJoinColumns = {@JoinColumn(name="empregado_id")})
	private List<Empregado> destinatarios;
	
	@Size(max = 2048, message="Tamanho máximo para Anexos em Email: 2048")
	private String anexos;
	
	private Date dataEnvio;
	
	@Size(max = 2048, message="Tamanho máximo para Conteúdo em Email: 2048")
	private String conteudo;
	
	@Size(max = 128, message="Tamanho máximo para Assunto em Email: 128")
	private String assunto;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Empregado> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<Empregado> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getAnexos() {
		return anexos;
	}

	public void setAnexos(String anexos) {
		this.anexos = anexos;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
