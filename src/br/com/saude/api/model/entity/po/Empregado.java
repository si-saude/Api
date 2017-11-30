package br.com.saude.api.model.entity.po;

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
public class Empregado {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 16, message="Tamanho máximo para Chave de Empregado: 16")
	private String chave;
	
	@Size(max = 32, message="Tamanho máximo para Matrícula de Empregado: 32")
	private String matricula;
	
	@Size(max = 16, message="Tamanho máximo para Ramal de Empregado: 16")
	private String ramal;
	
	@Size(max = 16, message="Tamanho máximo para Status de Empregado: 16")
	private String status;
	
	@Size(max = 16, message="Tamanho máximo para Estado Civil de Empregado: 16")
	private String estadoCivil;
	
	@Size(max = 128, message="Tamanho máximo para Escolaridade de Empregado: 128")
	private String escolaridade;
	
	@Size(max = 32, message="Tamanho máximo para Vínculo de Empregado: 32")
	@NotNull(message="É necessário informar o Vínculo de Empregado.")
	private String vinculo;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@ManyToOne(fetch=FetchType.LAZY)
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
	
	@OneToMany(mappedBy="empregado", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<EmpregadoVacina> empregadoVacinas;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="grupomonitoramento_empregado", 
				joinColumns = {@JoinColumn(name="empregado_id")}, 
				inverseJoinColumns = {@JoinColumn(name="grupomonitoramento_id")})
	private List<GrupoMonitoramento> grupoMonitoramentos;
	
	@OneToMany(mappedBy="empregado", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<HistoricoGrupoMonitoramento> historicoGrupoMonitoramentos;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "profissional_id")
	private Profissional profissional;
	
	@Transient
	private Map<Integer,Integer> foto;
	
	@Transient
	private String fotoBase64;

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
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getEscolaridade() {
		return escolaridade;
	}
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	public String getVinculo() {
		return vinculo;
	}
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
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
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
	public Map<Integer,Integer> getFoto() {
		return foto;
	}
	public void setFoto(Map<Integer,Integer> foto) {
		this.foto = foto;
	}
	public String getFotoBase64() {
		return fotoBase64;
	}
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
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
	
	@Override
	public boolean equals(Object empregado) {
		return ((Empregado)empregado).id == this.id && this.id > 0;
	}

}
