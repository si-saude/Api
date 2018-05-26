package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Aprho {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Empresa.")
	@Size(max = 128, message="Tamanho máximo para Empresa: 128")
	@Column(unique=true)
	private String empresa;	
	
	@NotNull(message="É necessário informar a Data.")
	private Date data;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Ghe ghe;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Ghe getGhe() {
		return ghe;
	}

	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}	
}
