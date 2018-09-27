package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class EmpregadoConvocacaoExame {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Empregado do Exame.")
	@ManyToOne(fetch=FetchType.LAZY)
	private EmpregadoConvocacao empregadoConvocacao;
	
	@NotNull(message="É necessário informar o Exame do Empregado.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Exame exame;
	
	private boolean conforme;
	
	private Date realizacao;
	
	private Date recebimento;
	
	private Date auditoria;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmpregadoConvocacao getEmpregadoConvocacao() {
		return empregadoConvocacao;
	}

	public void setEmpregadoConvocacao(EmpregadoConvocacao empregadoConvocacao) {
		this.empregadoConvocacao = empregadoConvocacao;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public boolean isConforme() {
		return conforme;
	}

	public void setConforme(boolean conforme) {
		this.conforme = conforme;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Date getRealizacao() {
		return realizacao;
	}

	public void setRealizacao(Date realizacao) {
		this.realizacao = realizacao;
	}
	
	public Date getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Date recebimento) {
		this.recebimento = recebimento;
	}

	public Date getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Date auditoria) {
		this.auditoria = auditoria;
	}

	@Override
	public boolean equals(Object e) {
		return ((EmpregadoConvocacaoExame)e).id == this.id && this.id > 0;
	}
}
