package br.com.saude.api.model.entity.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class EmpregadoVacina {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Empregado da Vacina.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Empregado empregado;
	
	@NotNull(message="É necessário informar a Vacina do Profissional.")
	@ManyToOne(fetch=FetchType.EAGER)
	private Vacina vacina;
	
	@NotNull(message="É necessário informar a Data da Vacina.")
	private Date data;
	
	@Size(max = 32, message="Tamanho máximo para Lote da Vacina: 32")
	private String lote;
	
	@Size(max = 128, message="Tamanho máximo para Laboratório da Vacina: 128")
	private String laboratorio;
	
	@Min(value=1, message="Valor mínimo para Dose da Vacina: 1")
	private int dose;
	
	private Date proximaDose;
	
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

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}

	public Date getProximaDose() {
		return proximaDose;
	}

	public void setProximaDose(Date proximaDose) {
		this.proximaDose = proximaDose;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
