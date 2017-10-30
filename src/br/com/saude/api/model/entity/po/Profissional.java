package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Profissional {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome do Profissional.")
	@Size(max = 220, message="Tamanho máximo para Nome do Profissional: 220")
	private String nome;
	
	private Date dataNascimento;
	
	private Date dataAso;
	
	@Size(max = 10, message="Tamanho máximo para Matrícula do Profissional: 10")
	private String matricula;
	
	@Size(max = 8, message="Tamanho máximo para Chave do Profissional: 8")
	private String chave;
	
	@Size(max = 12, message="Tamanho máximo para Ramal do Profissional: 12")
	private String ramal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Localizacao localizacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Equipe equipe;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar a Cargo do Profissional.")
	private Cargo cargo;
	
	@Size(max = 12, message="Tamanho máximo para MI do Profissional: 12")
	private String mi;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="telefone_profissional", 
				joinColumns = {@JoinColumn(name="profissional_id")}, 
				inverseJoinColumns = {@JoinColumn(name="telefone_id")})
	private List<Telefone> telefones;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Curriculo curriculo;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private ProfissionalConselho profissionalConselho;
	
	@OneToMany(mappedBy="profissional", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ProfissionalVacina> profissionalVacinas;
	
	@Transient
	private Map<Integer,Integer> assinatura;
	
	@Transient
	private String assinaturaBase64;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
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

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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

	public List<ProfissionalVacina> getProfissionalVacinas() {
		return profissionalVacinas;
	}

	public void setProfissionalVacinas(List<ProfissionalVacina> profissionalVacinas) {
		this.profissionalVacinas = profissionalVacinas;
	}

	public Date getDataAso() {
		return dataAso;
	}

	public void setDataAso(Date dataAso) {
		this.dataAso = dataAso;
	}

	public Map<Integer,Integer> getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(Map<Integer,Integer> assinatura) {
		this.assinatura = assinatura;
	}

	public String getAssinaturaBase64() {
		return assinaturaBase64;
	}

	public void setAssinaturaBase64(String assinaturaBase64) {
		this.assinaturaBase64 = assinaturaBase64;
	}
}
