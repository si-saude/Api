package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Aprho {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="� necess�rio informar a Empresa.")
	@Size(max = 1024, message="Tamanho m�ximo para Empresa: 1024")
	@Column(unique=true)
	private String empresa;	
	
	@NotNull(message="� necess�rio informar a Data.")
	private Date data;
	

	@OneToMany(mappedBy="aprho", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<AprhoItem> aprhoItens;
	
	@ManyToOne(fetch=FetchType.EAGER)
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
	
	public List<AprhoItem> getAprhoItens() {
		return aprhoItens;
	}

	public void setAprhoItens(List<AprhoItem> aprhoItens) {
		this.aprhoItens = aprhoItens;
	}
	
	public Ghe getGhe() {
		return ghe;
	}

	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}	
}
