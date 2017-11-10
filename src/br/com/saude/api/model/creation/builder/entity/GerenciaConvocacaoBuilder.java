package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.GerenciaConvocacao;

public class GerenciaConvocacaoBuilder 
		extends GenericEntityBuilder<GerenciaConvocacao, GenericFilter> {

	public static GerenciaConvocacaoBuilder newInstance(GerenciaConvocacao gerenciaConvocacao) {
		return new GerenciaConvocacaoBuilder(gerenciaConvocacao);
	}
	
	public static GerenciaConvocacaoBuilder newInstance(List<GerenciaConvocacao> gerenciaConvocacoes) {
		return new GerenciaConvocacaoBuilder(gerenciaConvocacoes);
	}
	
	private GerenciaConvocacaoBuilder(List<GerenciaConvocacao> gerenciaConvocacoes) {
		super(gerenciaConvocacoes);
	}

	private GerenciaConvocacaoBuilder(GerenciaConvocacao gerenciaConvocacao) {
		super(gerenciaConvocacao);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected GerenciaConvocacao clone(GerenciaConvocacao gerenciaConvocacao) {
		GerenciaConvocacao newGerenciaConvocacao = new GerenciaConvocacao();
		newGerenciaConvocacao.setId(gerenciaConvocacao.getId());
		newGerenciaConvocacao.setInicio(gerenciaConvocacao.getInicio());
		newGerenciaConvocacao.setFim(gerenciaConvocacao.getFim());
		newGerenciaConvocacao.setSelecionado(gerenciaConvocacao.isSelecionado());
		newGerenciaConvocacao.setVersion(gerenciaConvocacao.getVersion());
		
		if(gerenciaConvocacao.getGerencia() != null)
			newGerenciaConvocacao.setGerencia(GerenciaBuilder
										.newInstance(gerenciaConvocacao.getGerencia())
										.getEntity());
		return newGerenciaConvocacao;
	}

	@Override
	public GerenciaConvocacao cloneFromFilter(GenericFilter filter) {
		return null;
	}

}
