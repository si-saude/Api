package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.RespostaFichaColetaFilter;
import br.com.saude.api.model.entity.po.RespostaFichaColeta;

public class RespostaFichaColetaBuilder extends GenericEntityBuilder<RespostaFichaColeta, RespostaFichaColetaFilter> {
	
	public static RespostaFichaColetaBuilder newInstance(RespostaFichaColeta respostaFichaColeta) {
		return new RespostaFichaColetaBuilder(respostaFichaColeta);
	}
	
	public static RespostaFichaColetaBuilder newInstance(List<RespostaFichaColeta> respostaFichaColetas) {
		return new RespostaFichaColetaBuilder(respostaFichaColetas);
	}
	
	private RespostaFichaColetaBuilder(RespostaFichaColeta respostaFichaColeta) {
		super(respostaFichaColeta);
	}
	
	private RespostaFichaColetaBuilder(List<RespostaFichaColeta> respostaFichaColetas) {
		super(respostaFichaColetas);
	}

	@Override
	protected void initializeFunctions() {}

	@Override
	protected RespostaFichaColeta clone(RespostaFichaColeta respostaFichaColeta) {
		RespostaFichaColeta cloneRespostaFichaColeta = new RespostaFichaColeta();
		
		cloneRespostaFichaColeta.setId(respostaFichaColeta.getId());
		cloneRespostaFichaColeta.setVersion(respostaFichaColeta.getVersion());
		cloneRespostaFichaColeta.setConteudo(respostaFichaColeta.getConteudo());
		
		if ( respostaFichaColeta.getItens() != null )
			cloneRespostaFichaColeta.setItens(ItemRespostaFichaColetaBuilder
					.newInstance(respostaFichaColeta.getItens()).getEntityList());
		
		if ( respostaFichaColeta.getPergunta() != null )
			cloneRespostaFichaColeta.setPergunta(PerguntaFichaColetaBuilder
					.newInstance(respostaFichaColeta.getPergunta()).getEntity());
		
		return cloneRespostaFichaColeta;
	}

	@Override
	public RespostaFichaColeta cloneFromFilter(RespostaFichaColetaFilter filter) {
		return null;
	}
}
