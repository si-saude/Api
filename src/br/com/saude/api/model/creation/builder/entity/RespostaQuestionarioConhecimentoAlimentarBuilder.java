package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.RespostaQuestionarioConhecimentoAlimentar;

public class RespostaQuestionarioConhecimentoAlimentarBuilder extends 
	GenericEntityBuilder<RespostaQuestionarioConhecimentoAlimentar, GenericFilter> {

	public static RespostaQuestionarioConhecimentoAlimentarBuilder newInstance(RespostaQuestionarioConhecimentoAlimentar respostaQuestionarioConhecimentoAlimentar) {
		return new RespostaQuestionarioConhecimentoAlimentarBuilder(respostaQuestionarioConhecimentoAlimentar);
	}
	
	public static RespostaQuestionarioConhecimentoAlimentarBuilder newInstance(List<RespostaQuestionarioConhecimentoAlimentar> respostaQuestionarioConhecimentoAlimentares) {
		return new RespostaQuestionarioConhecimentoAlimentarBuilder(respostaQuestionarioConhecimentoAlimentares);
	}
	
	private RespostaQuestionarioConhecimentoAlimentarBuilder(RespostaQuestionarioConhecimentoAlimentar respostaQuestionarioConhecimentoAlimentar) {
		super(respostaQuestionarioConhecimentoAlimentar);
	}
	
	protected RespostaQuestionarioConhecimentoAlimentarBuilder(
			List<RespostaQuestionarioConhecimentoAlimentar> entityList) {
		super(entityList);
	}

	@Override
	protected void initializeFunctions() { }

	@Override
	protected RespostaQuestionarioConhecimentoAlimentar clone(RespostaQuestionarioConhecimentoAlimentar respostaQuestionarioConhecimentoAlimentar) {
		RespostaQuestionarioConhecimentoAlimentar cloneRespostaQuestionarioConhecimentoAlimentar = new RespostaQuestionarioConhecimentoAlimentar();
		
		cloneRespostaQuestionarioConhecimentoAlimentar.setId(respostaQuestionarioConhecimentoAlimentar.getId());
		cloneRespostaQuestionarioConhecimentoAlimentar.setVersion(respostaQuestionarioConhecimentoAlimentar.getVersion());
		
		if ( respostaQuestionarioConhecimentoAlimentar.getIndicador() != null )
			cloneRespostaQuestionarioConhecimentoAlimentar.setIndicador(
				IndicadorConhecimentoAlimentarBuilder.newInstance(
					respostaQuestionarioConhecimentoAlimentar.getIndicador())
					.loadItemIndicadorConhecimentoAlimentares().getEntity());
		
		if ( respostaQuestionarioConhecimentoAlimentar.getItem() != null )
			cloneRespostaQuestionarioConhecimentoAlimentar.setItem(
				ItemIndicadorConhecimentoAlimentarBuilder.newInstance(
					respostaQuestionarioConhecimentoAlimentar.getItem()).getEntity());
		
		return cloneRespostaQuestionarioConhecimentoAlimentar;
	}

	@Override
	public RespostaQuestionarioConhecimentoAlimentar cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
