package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@NotNull(message="É necessário informar a Empresa.")
	@Size(max = 1024, message="Tamanho máximo para Empresa: 1024")
	private String empresa;	
	
	@NotNull(message="É necessário informar a Revisão.")
	@Size(max = 16, message="Tamanho máximo para Revisão: 16")
	private String revisao;

	@NotNull(message="É necessário informar a Data de Criação.")
	private Date data;
	
	@NotNull(message="É necessário informar a Data de Revisão.")
	private Date dataRevisao;

	@OneToMany(mappedBy="aprho", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<AprhoItem> aprhoItens;
	
	@OneToMany(mappedBy="aprho", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<AprhoEmpregado> aprhoEmpregados;

	@ManyToOne(fetch=FetchType.EAGER)
	private Ghe ghe;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Profissional aprovador;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="aprho_elaborador", 
	joinColumns = {@JoinColumn(name="aprho_id")}, 
	inverseJoinColumns = {@JoinColumn(name="profissional_id")})
	private List<Profissional> elaboradores;
	
	@Version
	private long version;

	public Profissional getAprovador() {
		return aprovador;
	}

	public void setAprovador(Profissional aprovador) {
		this.aprovador = aprovador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRevisao() {
		return revisao;
	}

	public void setRevisao(String revisao) {
		this.revisao = revisao;
	}

	public List<Profissional> getElaboradores() {
		return elaboradores;
	}

	public void setElaboradores(List<Profissional> elaboradores) {
		this.elaboradores = elaboradores;
	}

	public Date getDataRevisao() {
		return dataRevisao;
	}

	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
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
	
	public List<AprhoEmpregado> getAprhoEmpregados() {
		return aprhoEmpregados;
	}

	public void setAprhoEmpregados(List<AprhoEmpregado> aprhoEmpregados) {
		this.aprhoEmpregados = aprhoEmpregados;
	}
	
	public Ghe getGhe() {
		return ghe;
	}

	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}	
}
