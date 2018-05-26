package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.model.creation.builder.entity.TipoSolicitacaoBuilder;
import br.com.saude.api.model.creation.builder.example.TipoSolicitacaoExampleBuilder;
import br.com.saude.api.model.entity.filter.TipoSolicitacaoFilter;
import br.com.saude.api.model.entity.po.TipoSolicitacao;
import br.com.saude.api.model.persistence.TipoSolicitacaoDao;

public class TipoSolicitacaoBo extends GenericBo<TipoSolicitacao, TipoSolicitacaoFilter, 
TipoSolicitacaoDao, TipoSolicitacaoBuilder, TipoSolicitacaoExampleBuilder> {
	
	private static TipoSolicitacaoBo instance;
	
	private TipoSolicitacaoBo() {
		super();
	}
	
	public static TipoSolicitacaoBo getInstance() {
		if(instance==null)
			instance = new TipoSolicitacaoBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {}

}
