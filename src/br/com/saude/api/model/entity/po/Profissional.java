package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Profissional {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="� necess�rio informar o Nome do Profissional.")
	@Size(max = 220, message="Tamanho m�ximo para Nome do Profissional: 220")
	private String nome;
	
	private Date dataNascimento;
	
	@Size(max = 10, message="Tamanho m�ximo para Matr�cula do Profissional: 10")
	private String matricula;
	
	@Size(max = 8, message="Tamanho m�ximo para Chave do Profissional: 8")
	private String chave;
	
	@Size(max = 12, message="Tamanho m�ximo para Ramal do Profissional: 12")
	private String ramal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Localizacao localizacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Equipe equipe;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="� necess�rio informar a Ger�ncia do Profissional.")
	private Gerencia gerencia;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="� necess�rio informar a Fun��o do Profissional.")
	private Funcao funcao;
	
	@Size(max = 12, message="Tamanho m�ximo para MI do Profissional: 12")
	private String mi;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="telefone_profissional")
	private List<Telefone> telefones;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="endereco_profissional")
	private List<Endereco> enderecos;
	
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	
}
