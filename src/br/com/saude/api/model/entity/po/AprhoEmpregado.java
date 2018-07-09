package br.com.saude.api.model.entity.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class AprhoEmpregado {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Aprho.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Aprho aprho;
	
	@NotNull(message="É necessário informar o Empregado.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Empregado empregado;
	
	@Version
	private long version;
	
	private boolean atual;
	
	private boolean entrevistado;
	
	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aprho getAprho() {
		return aprho;
	}

	public void setAprho(Aprho aprho) {
		this.aprho = aprho;
	}

	public boolean isAtual() {
		return atual;
	}

	public void setAtual(boolean atual) {
		this.atual = atual;
	}

	public boolean isEntrevistado() {
		return entrevistado;
	}

	public void setEntrevistado(boolean entrevistado) {
		this.entrevistado = entrevistado;
	}

	
}
