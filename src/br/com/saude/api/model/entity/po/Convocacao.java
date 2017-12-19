package br.com.saude.api.model.entity.po;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Convocacao {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="É necessário informar o Título da Convocação.")
	@Size(max = 128, message="Tamanho máximo para Título da Convocação: 128")
	@Column(unique=true)
	private String titulo;
	
	@NotNull(message="É necessário informar o Tipo da Convocação.")
	@Size(max = 64, message="Tamanho máximo para Tipo da Convocação: 64")
	private String tipo;
	
	@NotNull(message="É necessário informar o Profissiograma da Convocação.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Profissiograma profissiograma;
	
	@OneToMany(mappedBy="convocacao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<GerenciaConvocacao> gerenciaConvocacoes;
	
	@OneToMany(mappedBy="convocacao", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<EmpregadoConvocacao> empregadoConvocacoes;
	
	@NotNull(message="É necessário informar o Início do Cronograma.")
	private Date inicio;
	
	@NotNull(message="É necessário informar o Fim do Cronograma.")
	private Date fim;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Profissiograma getProfissiograma() {
		return profissiograma;
	}

	public void setProfissiograma(Profissiograma profissiograma) {
		this.profissiograma = profissiograma;
	}

	public List<GerenciaConvocacao> getGerenciaConvocacoes() {
		return gerenciaConvocacoes;
	}

	public void setGerenciaConvocacoes(List<GerenciaConvocacao> gerenciaConvocacoes) {
		this.gerenciaConvocacoes = gerenciaConvocacoes;
	}

	public List<EmpregadoConvocacao> getEmpregadoConvocacoes() {
		return empregadoConvocacoes;
	}

	public void setEmpregadoConvocacoes(List<EmpregadoConvocacao> empregadoConvocacoes) {
		this.empregadoConvocacoes = empregadoConvocacoes;
	}
	
	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
