package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Curriculo {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Histórico do Currículo.")
	@Size(max = 4096, message="Tamanho máximo para Histórico do Currículo: 4096")
	private String historico;
	
	@Size(max = 512, message="Tamanho máximo para Formação Acadêmica: 512")
	private String formacao;
	
	@Size(max = 512, message="Tamanho máximo para Atuação Profissional: 512")
	private String atuacao;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "profissional_id")
	private Profissional profissional;
	
	@OneToMany(mappedBy="curriculo", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<CurriculoCurso> curriculoCursos;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getAtuacao() {
		return atuacao;
	}

	public void setAtuacao(String atuacao) {
		this.atuacao = atuacao;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public List<CurriculoCurso> getCurriculoCursos() {
		return curriculoCursos;
	}

	public void setCurriculoCursos(List<CurriculoCurso> curriculoCursos) {
		this.curriculoCursos = curriculoCursos;
	}
	
}
