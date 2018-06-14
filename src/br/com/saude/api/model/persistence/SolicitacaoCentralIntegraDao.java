package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.SolicitacaoCentralIntegra;

public class SolicitacaoCentralIntegraDao extends GenericDao<SolicitacaoCentralIntegra>  {
	
	private static SolicitacaoCentralIntegraDao instance;
	
	private SolicitacaoCentralIntegraDao(){
		super();
	}
	
	public static SolicitacaoCentralIntegraDao getInstance() {
		if(instance == null)
			instance = new SolicitacaoCentralIntegraDao();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = solicitacao -> {
			solicitacao = loadTipoSolicitacao(solicitacao);
			solicitacao = loadTarefa(solicitacao);
			return solicitacao;
		};
	}
	
	private SolicitacaoCentralIntegra loadTipoSolicitacao(SolicitacaoCentralIntegra solicitacao) {
		if(solicitacao.getTarefa()!=null)
			Hibernate.initialize(solicitacao.getTipoSolicitacao());
		return solicitacao;
	}
	
	private SolicitacaoCentralIntegra loadTarefa(SolicitacaoCentralIntegra solicitacao) {
		if(solicitacao.getTarefa() != null)
			Hibernate.initialize(solicitacao.getTarefa());
		return solicitacao;
	}

	@Override
	public SolicitacaoCentralIntegra getById(Object id) throws Exception {
		return super.getById(id, this.functionLoad);
	}

	@Override
	public PagedList<SolicitacaoCentralIntegra> getList(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoad);
	}
	
}
