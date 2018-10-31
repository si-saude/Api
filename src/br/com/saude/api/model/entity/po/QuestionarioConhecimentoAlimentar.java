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

@Entity
public class QuestionarioConhecimentoAlimentar {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "atendimento_id")
	private Atendimento atendimento;
	
	@OneToMany(mappedBy="questionario", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<RespostaQuestionarioConhecimentoAlimentar> respostas;
	
	@Version
	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public List<RespostaQuestionarioConhecimentoAlimentar> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<RespostaQuestionarioConhecimentoAlimentar> respostas) {
		this.respostas = respostas;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
