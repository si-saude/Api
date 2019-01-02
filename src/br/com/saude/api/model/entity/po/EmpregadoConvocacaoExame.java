package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@Size(max = 1048, message="Tamanho máximo para Laboratório da Vacina: 1048")
	private String resultado;
	
	private boolean conforme;
	
	private boolean exigeRelatorio;
	
	private boolean opcional;
	
	private Date realizacao;
	
	private Date recebimento;
	
	private Date auditoria;
	
	private boolean resultadoConforme;
	
	@Transient
	private boolean resultadoInicializado;
	
	@Version
	private long version;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isResultadoConforme() {
		return resultadoConforme;
	}

	public void setResultadoConforme(boolean resultadoConforme) {
		this.resultadoConforme = resultadoConforme;
	}

	public String getResultado() {
		return resultado;
	}
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
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

	public boolean isExigeRelatorio() {
		return exigeRelatorio;
	}

	public void setExigeRelatorio(boolean exigeRelatorio) {
		this.exigeRelatorio = exigeRelatorio;
	}

	public boolean isOpcional() {
		return opcional;
	}

	public void setOpcional(boolean opcional) {
		this.opcional = opcional;
	}

	@Override
	public boolean equals(Object e) {
		return ((EmpregadoConvocacaoExame)e).id == this.id && this.id > 0;
	}

	public boolean isResultadoInicializado() {
		return resultadoInicializado;
	}

	public void setResultadoInicializado(boolean resultadoInicializado) {
		this.resultadoInicializado = resultadoInicializado;
	}
}
