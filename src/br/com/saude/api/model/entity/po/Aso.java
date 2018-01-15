package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
