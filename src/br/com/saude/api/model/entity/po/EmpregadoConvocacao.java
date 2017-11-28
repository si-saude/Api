package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class EmpregadoConvocacao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Empregado da Convocação.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Empregado empregado;
	
	@NotNull(message="É necessário informar a Convocação do Empregado.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Convocacao convocacao;
	
	@OneToMany(mappedBy="empregadoConvocacao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<EmpregadoConvocacaoExame> empregadoConvocacaoExames;
	
	private boolean auditado;
	
	private boolean convocado;
	
	@Transient
	private boolean selecionado;
	
	@Transient
	private boolean divergente;
	
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

	public Convocacao getConvocacao() {
		return convocacao;
	}

	public void setConvocacao(Convocacao convocacao) {
		this.convocacao = convocacao;
	}

	public boolean isAuditado() {
		return auditado;
	}

	public void setAuditado(boolean auditado) {
		this.auditado = auditado;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public boolean isDivergente() {
		return divergente;
	}

	public void setDivergente(boolean divergente) {
		this.divergente = divergente;
	}

	public boolean isConvocado() {
		return convocado;
	}

	public void setConvocado(boolean convocado) {
		this.convocado = convocado;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public List<EmpregadoConvocacaoExame> getEmpregadoConvocacaoExames() {
		return empregadoConvocacaoExames;
	}

	public void setEmpregadoConvocacaoExames(List<EmpregadoConvocacaoExame> empregadoConvocacaoExames) {
		this.empregadoConvocacaoExames = empregadoConvocacaoExames;
	}
}
