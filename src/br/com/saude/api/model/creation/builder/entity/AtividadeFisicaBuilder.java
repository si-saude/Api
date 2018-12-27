package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AtividadeFisicaFilter;
import br.com.saude.api.model.entity.po.AtividadeFisica;

public class AtividadeFisicaBuilder extends GenericEntityBuilder<AtividadeFisica, AtividadeFisicaFilter> {

	public static AtividadeFisicaBuilder newInstance(AtividadeFisica atividadeFisica) {
		return new AtividadeFisicaBuilder(atividadeFisica);
	}
	
	public static AtividadeFisicaBuilder newInstance(List<AtividadeFisica> atividadeFisicas) {
		return new AtividadeFisicaBuilder(atividadeFisicas);
	}
	
	private AtividadeFisicaBuilder(AtividadeFisica atividadeFisica) {
		super(atividadeFisica);
	}
	
	private AtividadeFisicaBuilder(List<AtividadeFisica> atividadeFisicas) {
		super(atividadeFisicas);
	}

	@Override
	protected void initializeFunctions() {}

	@Override
	protected AtividadeFisica clone(AtividadeFisica atividadeFisica) {
		AtividadeFisica newAtividadeFisica = new AtividadeFisica();
		
		newAtividadeFisica.setId(atividadeFisica.getId());
		newAtividadeFisica.setVersion(atividadeFisica.getVersion());
		newAtividadeFisica.setDescricao(atividadeFisica.getDescricao());
		
		return newAtividadeFisica;
	}
	
	@Override
	public AtividadeFisica cloneFromFilter(AtividadeFisicaFilter filter) {
		return null;
	}

}
