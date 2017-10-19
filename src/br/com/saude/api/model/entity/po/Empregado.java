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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Empregado {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Nome do Empregado(a).")
	@Size(max = 400, message="Tamanho máximo para Nome: 400")
	private String nome;
	
	@Column(unique=true)
	@Size(max = 11, message="Tamanho máximo para Cpf do Empregado: 11")
	@NotNull(message="É necessário informar o Cpf do Empregado(a).")
	private String cpf;
	
	private Date dataNascimento;
	
	@Size(max = 16, message="Tamanho máximo para Chave de Empregado: 16")
	private String chave;
	
	@Size(max = 32, message="Tamanho máximo para Matrícula de Empregado: 32")
	private String matricula;
	
	@Size(max = 32, message="Tamanho máximo para RG de Empregado: 32")
	private String rg;
	
	@Size(max = 16, message="Tamanho máximo para Sexo de Empregado: 16")
	private String sexo;
	
	@Size(max = 16, message="Tamanho máximo para Ramal de Empregado: 16")
	private String ramal;
	
	@Size(max = 16, message="Tamanho máximo para Status de Empregado: 16")
	private String status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar a Cargo de Empregado.")
	private Cargo cargo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar a Função de Empregado.")
	private Funcao funcao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Regime regime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar a Gerência de Empregado.")
	private Gerencia gerencia;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Base base;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Ghe ghe;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Ghee ghee;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="instalacao_empregado", 
				joinColumns = {@JoinColumn(name="empregado_id")}, 
				inverseJoinColumns = {@JoinColumn(name="instalacao_id")})
	private List<Instalacao> instalacoes;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="telefone_empregado", 
				joinColumns = {@JoinColumn(name="empregado_id")}, 
				inverseJoinColumns = {@JoinColumn(name="telefone_id")})
	private List<Telefone> telefones;
	
	@OneToMany(mappedBy="empregado", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<EmpregadoVacina> empregadoVacinas;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="grupomonitoramento_empregado", 
				joinColumns = {@JoinColumn(name="empregado_id")}, 
				inverseJoinColumns = {@JoinColumn(name="grupomonitoramento_id")})
	private List<GrupoMonitoramento> grupoMonitoramentos;
	
	@OneToMany(mappedBy="empregado", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<HistoricoGrupoMonitoramento> historicoGrupoMonitoramentos;
	
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public Regime getRegime() {
		return regime;
	}
	public void setRegime(Regime regime) {
		this.regime = regime;
	}
	public Gerencia getGerencia() {
		return gerencia;
	}
	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}
	public Base getBase() {
		return base;
	}
	public void setBase(Base base) {
		this.base = base;
	}
	public Ghe getGhe() {
		return ghe;
	}
	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}
	
	public Ghee getGhee() {
		return ghee;
	}
	public void setGhee(Ghee ghee) {
		this.ghee = ghee;
	}
	public List<Instalacao> getInstalacoes() {
		return instalacoes;
	}
	public void setInstalacoes(List<Instalacao> instalacoes) {
		this.instalacoes = instalacoes;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public List<EmpregadoVacina> getEmpregadoVacinas() {
		return empregadoVacinas;
	}
	public void setEmpregadoVacinas(List<EmpregadoVacina> empregadoVacinas) {
		this.empregadoVacinas = empregadoVacinas;
	}
	public List<GrupoMonitoramento> getGrupoMonitoramentos() {
		return grupoMonitoramentos;
	}
	public void setGrupoMonitoramentos(List<GrupoMonitoramento> grupoMonitoramentos) {
		this.grupoMonitoramentos = grupoMonitoramentos;
	}
	public List<HistoricoGrupoMonitoramento> getHistoricoGrupoMonitoramentos() {
		return historicoGrupoMonitoramentos;
	}
	public void setHistoricoGrupoMonitoramentos(List<HistoricoGrupoMonitoramento> historicoGrupoMonitoramentos) {
		this.historicoGrupoMonitoramentos = historicoGrupoMonitoramentos;
	}
}
