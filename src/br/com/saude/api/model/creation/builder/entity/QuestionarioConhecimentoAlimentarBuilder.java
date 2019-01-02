package br.com.saude.api.model.creation.builder.entity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.QuestionarioConhecimentoAlimentarFilter;
import br.com.saude.api.model.entity.po.QuestionarioConhecimentoAlimentar;

public class QuestionarioConhecimentoAlimentarBuilder extends 
	GenericEntityBuilder<QuestionarioConhecimentoAlimentar, QuestionarioConhecimentoAlimentarFilter> {
	
	private Function<Map<String,QuestionarioConhecimentoAlimentar>,QuestionarioConhecimentoAlimentar> loadRespostas;

	public static QuestionarioConhecimentoAlimentarBuilder newInstance(QuestionarioConhecimentoAlimentar questionarioConhecimentoAlimentar) {
		return new QuestionarioConhecimentoAlimentarBuilder(questionarioConhecimentoAlimentar);
	}
	
	public static QuestionarioConhecimentoAlimentarBuilder newInstance(List<QuestionarioConhecimentoAlimentar> questionarioConhecimentoAlimentares) {
		return new QuestionarioConhecimentoAlimentarBuilder(questionarioConhecimentoAlimentares);
	}
	
	private QuestionarioConhecimentoAlimentarBuilder(QuestionarioConhecimentoAlimentar questionarioConhecimentoAlimentar) {
		super(questionarioConhecimentoAlimentar);
	}
	
	private QuestionarioConhecimentoAlimentarBuilder(
			List<QuestionarioConhecimentoAlimentar> entityList) {
		super(entityList);
	}

	@Override
	protected void initializeFunctions() { 
		this.loadRespostas = questionarios ->{
			if(questionarios.get("origem").getRespostas() != null) {
				questionarios.get("destino").setRespostas(
						RespostaQuestionarioConhecimentoAlimentarBuilder.newInstance(
								questionarios.get("origem").getRespostas()).getEntityList());
			}
			return questionarios.get("destino");
		};
	}

	@Override
	protected QuestionarioConhecimentoAlimentar clone(QuestionarioConhecimentoAlimentar questionarioConhecimentoAlimentar) {
		QuestionarioConhecimentoAlimentar cloneQuestionarioConhecimentoAlimentar = new QuestionarioConhecimentoAlimentar();
		
		cloneQuestionarioConhecimentoAlimentar.setId(questionarioConhecimentoAlimentar.getId());
		cloneQuestionarioConhecimentoAlimentar.setVersion(questionarioConhecimentoAlimentar.getVersion());
		
		if ( questionarioConhecimentoAlimentar.getAtendimento() != null )
			cloneQuestionarioConhecimentoAlimentar.setAtendimento( AtendimentoBuilder.newInstance(
					questionarioConhecimentoAlimentar.getAtendimento()).loadTarefa().getEntity());
		
		return cloneQuestionarioConhecimentoAlimentar;
	}
	
	public QuestionarioConhecimentoAlimentarBuilder loadRespostas() {
		return (QuestionarioConhecimentoAlimentarBuilder) this.loadProperty(this.loadRespostas);
	}

	@Override
	public QuestionarioConhecimentoAlimentar cloneFromFilter(QuestionarioConhecimentoAlimentarFilter filter) {
		return null;
	}
}
