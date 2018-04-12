package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.AcompanhamentoFilter;
import br.com.saude.api.model.entity.po.Acompanhamento;

public class AcompanhamentoBuilder extends GenericEntityBuilder<Acompanhamento, AcompanhamentoFilter> {

	public static AcompanhamentoBuilder newInstance(Acompanhamento acompanhamento) {
		return new AcompanhamentoBuilder(acompanhamento);
	}
	
	public static AcompanhamentoBuilder newInstance(List<Acompanhamento> acompanhamentos) {
		return new AcompanhamentoBuilder(acompanhamentos);
	}
	
	private AcompanhamentoBuilder(Acompanhamento acompanhamento) {
		super(acompanhamento);
	}
	
	private AcompanhamentoBuilder(List<Acompanhamento> acompanhamentos) {
		super(acompanhamentos);
	}

	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected Acompanhamento clone(Acompanhamento acompanhamento) {
		
		Acompanhamento newAcompanhamento = new Acompanhamento();
		newAcompanhamento.setId(acompanhamento.getId());
		newAcompanhamento.setDescricao(acompanhamento.getDescricao());
		newAcompanhamento.setVersion(acompanhamento.getVersion());
		
		return newAcompanhamento;
	}

	@Override
	public Acompanhamento cloneFromFilter(AcompanhamentoFilter filter) {
		return null;
	}
}
