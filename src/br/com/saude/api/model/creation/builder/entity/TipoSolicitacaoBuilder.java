package br.com.saude.api.model.creation.builder.entity;

import java.util.List;

import br.com.saude.api.generic.GenericEntityBuilder;
import br.com.saude.api.model.entity.filter.TipoSolicitacaoFilter;
import br.com.saude.api.model.entity.po.TipoSolicitacao;

public class TipoSolicitacaoBuilder extends GenericEntityBuilder<TipoSolicitacao, TipoSolicitacaoFilter> {

	public static TipoSolicitacaoBuilder newInstance(TipoSolicitacao tipo) {
		return new TipoSolicitacaoBuilder(tipo);
	}
	
	public static TipoSolicitacaoBuilder newInstance(List<TipoSolicitacao> tipos) {
		return new TipoSolicitacaoBuilder(tipos);
	}
	
	private TipoSolicitacaoBuilder(TipoSolicitacao tipo) {
		super(tipo);
	}

	private TipoSolicitacaoBuilder(List<TipoSolicitacao> tipos) {
		super(tipos);
	}
	
	@Override
	protected void initializeFunctions() {
		
	}

	@Override
	protected TipoSolicitacao clone(TipoSolicitacao tipo) {
		TipoSolicitacao newTipo = new TipoSolicitacao();
		
		newTipo.setId(tipo.getId());
		newTipo.setNome(tipo.getNome());
		newTipo.setVersion(tipo.getVersion());
		
		return newTipo;
	}

	@Override
	public TipoSolicitacao cloneFromFilter(TipoSolicitacaoFilter filter) {
		return null;
	}

}
