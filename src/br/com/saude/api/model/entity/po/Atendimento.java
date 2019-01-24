package br.com.saude.api.model.entity.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Atendimento {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@NotNull(message="É necessário informar a Fila de Atendimento.")
	private FilaAtendimentoOcupacional filaAtendimentoOcupacional;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@NotNull(message="É necessário informar a Fila de Espera.")
	private FilaEsperaOcupacional filaEsperaOcupacional;
	
	@NotNull(message="É necessário informar a Tarefa.")
	@ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Tarefa tarefa;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Aso aso;
	
	@OneToMany(mappedBy="atendimento", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Triagem> triagens;
	
	@OneToOne(fetch=FetchType.LAZY)
	private QuestionarioConhecimentoAlimentar questionario;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Recordatorio recordatorio;
	
	@OneToOne(fetch=FetchType.LAZY)
	private AvaliacaoFisica avaliacaoFisica;
	
	@Transient
	private List<Triagem> triagensTodosAtendimentos;
	
	@Size(max = 128, message="Tamanho máximo para o Tipo do Atendimento: 128")
	private String tipo;
	
	@Version
	private long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public FilaAtendimentoOcupacional getFilaAtendimentoOcupacional() {
		return filaAtendimentoOcupacional;
	}

	public void setFilaAtendimentoOcupacional(FilaAtendimentoOcupacional filaAtendimentoOcupacional) {
		this.filaAtendimentoOcupacional = filaAtendimentoOcupacional;
	}

	public FilaEsperaOcupacional getFilaEsperaOcupacional() {
		return filaEsperaOcupacional;
	}

	public void setFilaEsperaOcupacional(FilaEsperaOcupacional filaEsperaOcupacional) {
		this.filaEsperaOcupacional = filaEsperaOcupacional;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	public List<Triagem> getTriagens() {
		return triagens;
	}

	public void setTriagens(List<Triagem> triagens) {
		this.triagens = triagens;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Aso getAso() {
		return aso;
	}

	public void setAso(Aso aso) {
		this.aso = aso;
	}

	public List<Triagem> getTriagensTodosAtendimentos() {
		return triagensTodosAtendimentos;
	}

	public void setTriagensTodosAtendimentos(List<Triagem> triagensTodosAtendimentos) {
		this.triagensTodosAtendimentos = triagensTodosAtendimentos;
	}

	public QuestionarioConhecimentoAlimentar getQuestionario() {
		return questionario;
	}

	public void setQuestionario(QuestionarioConhecimentoAlimentar questionario) {
		this.questionario = questionario;
	}

	public Recordatorio getRecordatorio() {
		return recordatorio;
	}

	public void setRecordatorio(Recordatorio recordatorio) {
		this.recordatorio = recordatorio;
	}
	
	public AvaliacaoFisica getAvaliacaoFisica() {
		return avaliacaoFisica;
	}

	public void setAvaliacaoFisica(AvaliacaoFisica avaliacaoFisica) {
		this.avaliacaoFisica = avaliacaoFisica;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
