package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Vacina {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar a Descrição da Vacina.")
	@Size(max = 200, message="Tamanho máximo para Descrição da Vacina: 200")
	@Column(unique=true)
	private String descricao;
	
	@Min(value=1, message="Valor mínimo para Doses da Vacina: 1")
	private int doses;
	
	@Min(value=0, message="Valor mínimo para Reforço da Vacina: 0")
	private int reforco;
	
	@OneToMany(mappedBy="vacina", fetch=FetchType.LAZY)
	private List<ProfissionalVacina> profissionalVacinas;
	
	@OneToMany(mappedBy="vacina", fetch=FetchType.LAZY)
	private List<EmpregadoVacina> empregadoVacinas;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getDoses() {
		return doses;
	}

	public void setDoses(int doses) {
		this.doses = doses;
	}

	public int getReforco() {
		return reforco;
	}

	public void setReforco(int reforco) {
		this.reforco = reforco;
	}

	public List<ProfissionalVacina> getProfissionalVacinas() {
		return profissionalVacinas;
	}

	public void setProfissionalVacinas(List<ProfissionalVacina> profissionalVacinas) {
		this.profissionalVacinas = profissionalVacinas;
	}

	public List<EmpregadoVacina> getEmpregadoVacinas() {
		return empregadoVacinas;
	}

	public void setEmpregadoVacinas(List<EmpregadoVacina> empregadoVacinas) {
		this.empregadoVacinas = empregadoVacinas;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
