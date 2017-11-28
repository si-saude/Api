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
public class ResultadoExame {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Empregado/Convocação do Resultado.")
	@ManyToOne(fetch=FetchType.LAZY)
	private EmpregadoConvocacao empregadoConvocacao;
	
	@NotNull(message="É necessário informar o Exame do Resultado.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Exame exame;
	
	@NotNull(message="É necessário informar a Data do Resultado.")
	private Date data;
	
	private boolean conforme;
	
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

	public boolean isConforme() {
		return conforme;
	}

	public void setConforme(boolean conforme) {
		this.conforme = conforme;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
