package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

public class Aso {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Empregado/Convocação do ASO.")
	@ManyToOne(fetch=FetchType.LAZY)
	private EmpregadoConvocacao empregadoConvocacao;
	
	@NotNull(message="É necessário informar a Validade do ASO.")
	private Date validade;
	
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
}
