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
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Aso {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Empregado do ASO.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Empregado empregado;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "atendimento_id")
	@NotNull(message="É necessário informar o Atendimento do ASO.")
	private Atendimento atendimento;
	
	@NotNull(message="É necessário informar a Data do ASO.")
	private Date data;
	
	@NotNull(message="É necessário informar a Validade do ASO.")
	private Date validade;
	
	@NotNull(message="É necessário informar o Status do ASO.")
	@Size(max = 32, message="Tamanho máximo para Status do ASO: 32")
	private String status;
	
	@OneToMany(mappedBy="aso", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<AsoAlteracao> asoAlteracoes;
	
	private boolean conforme;
	
	@OneToMany(mappedBy="aso", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Aptidao> aptidoes;
	
	@OneToMany(mappedBy="aso", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<AsoAvaliacao> asoAvaliacoes;

	@Size(max = 1024, message="Tamanho máximo para Não Conformidade do ASO: 1024")
	private String naoConformidades;
	
	@Version
	private long version;
	
	@Transient
	private Usuario usuario;
	
	private boolean impressoSd2000;
	
	private boolean pendente;
	
	private boolean convocado;
	
	private Date dataRestricao;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="exame_aso", 
				joinColumns = {@JoinColumn(name="aso_id")}, 
				inverseJoinColumns = {@JoinColumn(name="exame_id")})
	private List<Exame> examesConvocacao;
	
	private boolean ausenciaExames;
	
	public List<AsoAvaliacao> getAsoAvaliacoes() {
		return asoAvaliacoes;
	}

	public void setAsoAvaliacoes(List<AsoAvaliacao> asoAvaliacoes) {
		this.asoAvaliacoes = asoAvaliacoes;
	}
	
	public boolean isImpressoSd2000() {
		return impressoSd2000;
	}

	public void setImpressoSd2000(boolean impressoSd2000) {
		this.impressoSd2000 = impressoSd2000;
	}

	public boolean isPendente() {
		return pendente;
	}

	public void setPendente(boolean pendente) {
		this.pendente = pendente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Aptidao> getAptidoes() {
		return aptidoes;
	}

	public void setAptidoes(List<Aptidao> aptidoes) {
		this.aptidoes = aptidoes;
	}
	
	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AsoAlteracao> getAsoAlteracoes() {
		return asoAlteracoes;
	}

	public void setAsoAlteracoes(List<AsoAlteracao> asoAlteracoes) {
		this.asoAlteracoes = asoAlteracoes;
	}
	
	public boolean isConforme() {
		return conforme;
	}

	public void setConforme(boolean conforme) {
		this.conforme = conforme;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getNaoConformidades() {
		return naoConformidades;
	}

	public void setNaoConformidades(String naoConformidades) {
		this.naoConformidades = naoConformidades;
	}
	public List<Exame> getExamesConvocacao() {
		return examesConvocacao;
	}

	public void setExamesConvocacao(List<Exame> examesConvocacao) {
		this.examesConvocacao = examesConvocacao;
	}

	public boolean isAusenciaExames() {
		return ausenciaExames;
	}

	public void setAusenciaExames(boolean ausenciaExames) {
		this.ausenciaExames = ausenciaExames;
	}
	
	public Date getDataRestricao() {
		return dataRestricao;
	}

	public void setDataRestricao(Date dataRestricao) {
		this.dataRestricao = dataRestricao;
	}
	
	public boolean isConvocado() {
		return convocado;
	}

	public void setConvocado(boolean convocado) {
		this.convocado = convocado;
	}
}


