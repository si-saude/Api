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
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
public class Profissional {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "empregado_id")
	private Empregado empregado;
	
	private Date dataAso;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Localizacao localizacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Equipe equipe;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="profissional_equipe", 
	joinColumns = {@JoinColumn(name="profissional_id")}, 
	inverseJoinColumns = {@JoinColumn(name="equipe_id")})
	private List<Equipe> equipes;
	
	
	@Size(max = 12, message="Tamanho máximo para MI do Profissional: 12")
	private String mi;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Curriculo curriculo;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private ProfissionalConselho profissionalConselho;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="servico_profissional", 
				joinColumns = {@JoinColumn(name="profissional_id")}, 
				inverseJoinColumns = {@JoinColumn(name="servico_id")})
	private List<Servico> servicos;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}
	
	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}
	
	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getMi() {
		return mi;
	}

	public void setMi(String mi) {
		this.mi = mi;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}

	public ProfissionalConselho getProfissionalConselho() {
		return profissionalConselho;
	}

	public void setProfissionalConselho(ProfissionalConselho profissionalConselho) {
		this.profissionalConselho = profissionalConselho;
	}

	public Date getDataAso() {
		return dataAso;
	}

	public void setDataAso(Date dataAso) {
		this.dataAso = dataAso;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
}
