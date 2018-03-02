package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class RiscoPotencial {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Data do Risco Potencial.")
	private Date data;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Empregado empregado;
	
	@OneToMany(mappedBy="riscoPotencial", fetch=FetchType.LAZY, orphanRemoval=true)
	private List<RiscoEmpregado> riscoEmpregados;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public List<RiscoEmpregado> getRiscoEmpregados() {
		return riscoEmpregados;
	}

	public void setRiscoEmpregados(List<RiscoEmpregado> riscoEmpregados) {
		this.riscoEmpregados = riscoEmpregados;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
