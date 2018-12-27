package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.generic.GenericFilter;
import br.com.saude.api.model.entity.po.AvaliacaoFisicaAtividadeFisica;

public class AvaliacaoFisicaAtividadeFisicaBuilder extends GenericEntityBuilder<AvaliacaoFisicaAtividadeFisica, GenericFilter> {
	
	public static AvaliacaoFisicaAtividadeFisicaBuilder newInstance(AvaliacaoFisicaAtividadeFisica avaliacaoFisicaAtividadeFisica) {
		return new AvaliacaoFisicaAtividadeFisicaBuilder(avaliacaoFisicaAtividadeFisica);
	}
	
	public static AvaliacaoFisicaAtividadeFisicaBuilder newInstance(List<AvaliacaoFisicaAtividadeFisica> list) {
		return new AvaliacaoFisicaAtividadeFisicaBuilder(list);
	}
	
	private AvaliacaoFisicaAtividadeFisicaBuilder(AvaliacaoFisicaAtividadeFisica avaliacaoFisicaAtividadeFisica) {
		super(avaliacaoFisicaAtividadeFisica);
	}

	private AvaliacaoFisicaAtividadeFisicaBuilder(List<AvaliacaoFisicaAtividadeFisica> list) {
		super(list);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected AvaliacaoFisicaAtividadeFisica clone(AvaliacaoFisicaAtividadeFisica avaliacaoFisicaAtividadeFisica) {
		AvaliacaoFisicaAtividadeFisica newAvaliacaoFisicaAtividadeFisica = new AvaliacaoFisicaAtividadeFisica();
		
		newAvaliacaoFisicaAtividadeFisica.setId(avaliacaoFisicaAtividadeFisica.getId());
		newAvaliacaoFisicaAtividadeFisica.setVersion(avaliacaoFisicaAtividadeFisica.getVersion());
		newAvaliacaoFisicaAtividadeFisica.setClassificacao(avaliacaoFisicaAtividadeFisica.getClassificacao());
		newAvaliacaoFisicaAtividadeFisica.setDomingo(avaliacaoFisicaAtividadeFisica.isDomingo());
		newAvaliacaoFisicaAtividadeFisica.setMinuto(avaliacaoFisicaAtividadeFisica.getMinuto());
		newAvaliacaoFisicaAtividadeFisica.setObservacao(avaliacaoFisicaAtividadeFisica.getObservacao());
		newAvaliacaoFisicaAtividadeFisica.setQuarta(avaliacaoFisicaAtividadeFisica.isQuarta());
		newAvaliacaoFisicaAtividadeFisica.setQuinta(avaliacaoFisicaAtividadeFisica.isQuinta());
		newAvaliacaoFisicaAtividadeFisica.setSabado(avaliacaoFisicaAtividadeFisica.isSabado());
		newAvaliacaoFisicaAtividadeFisica.setSegunda(avaliacaoFisicaAtividadeFisica.isSegunda());
		newAvaliacaoFisicaAtividadeFisica.setSexta(avaliacaoFisicaAtividadeFisica.isSexta());
		newAvaliacaoFisicaAtividadeFisica.setTerca(avaliacaoFisicaAtividadeFisica.isTerca());
		newAvaliacaoFisicaAtividadeFisica.setTipo(avaliacaoFisicaAtividadeFisica.getTipo());
		newAvaliacaoFisicaAtividadeFisica.setTotalMinuto(avaliacaoFisicaAtividadeFisica.getTotalMinuto());
		
		return newAvaliacaoFisicaAtividadeFisica;
	}

	@Override
	public AvaliacaoFisicaAtividadeFisica cloneFromFilter(GenericFilter filter) {
		return null;
	}
}
