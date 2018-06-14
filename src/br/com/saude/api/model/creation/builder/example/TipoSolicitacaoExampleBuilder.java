package br.com.saude.api.model.creation.builder.example;

import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.TipoSolicitacaoFilter;
import br.com.saude.api.model.entity.po.TipoSolicitacao;

public class TipoSolicitacaoExampleBuilder extends GenericExampleBuilder<TipoSolicitacao, TipoSolicitacaoFilter>{

	public static TipoSolicitacaoExampleBuilder 
						newInstance(TipoSolicitacaoFilter filter) {
		return new TipoSolicitacaoExampleBuilder(filter);
	}
	
	private TipoSolicitacaoExampleBuilder(TipoSolicitacaoFilter filter) {
		super(filter);
	}

	@Override
	protected void createExample() throws InstantiationException, IllegalAccessException {
		addNome();
	}

	@Override
	protected void createExampleSelectList() {
		
	}
	
	private void addNome() {
		if(this.filter.getNome() != null)
			this.entity.setNome(Helper.filterLike(this.filter.getNome()));
	}
	
}
