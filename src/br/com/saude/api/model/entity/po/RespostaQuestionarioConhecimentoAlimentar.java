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
public class RespostaQuestionarioConhecimentoAlimentar {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="É necessário informar o Questionário da Resposta.")
	private QuestionarioConhecimentoAlimentar questionario;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private IndicadorConhecimentoAlimentar indicador;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private ItemIndicadorConhecimentoAlimentar item;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public QuestionarioConhecimentoAlimentar getQuestionario() {
		return questionario;
	}

	public void setQuestionario(QuestionarioConhecimentoAlimentar questionario) {
		this.questionario = questionario;
	}

	public IndicadorConhecimentoAlimentar getIndicador() {
		return indicador;
	}

	public void setIndicador(IndicadorConhecimentoAlimentar indicador) {
		this.indicador = indicador;
	}

	public ItemIndicadorConhecimentoAlimentar getItem() {
		return item;
	}

	public void setItem(ItemIndicadorConhecimentoAlimentar item) {
		this.item = item;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
