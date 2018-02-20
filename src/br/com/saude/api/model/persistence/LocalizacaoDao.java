package br.com.saude.api.model.persistence;

import java.util.function.Function;

import org.hibernate.Hibernate;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Localizacao;

public class LocalizacaoDao extends GenericDao<Localizacao> {
	
	private Function<Localizacao, Localizacao> functionLoadRegraAtendimentoLocalizacoes;
	
	private static LocalizacaoDao instance;
	
	private LocalizacaoDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadRegraAtendimentoLocalizacoes = localizacao -> {
			localizacao = loadRegraAtendimentoLocalizacoes(localizacao);
			return localizacao;
		};
	}
	
	public static LocalizacaoDao getInstance() {
		if(instance==null)
			instance = new LocalizacaoDao();
		return instance;
	}
	
	private Localizacao loadRegraAtendimentoLocalizacoes( Localizacao localizacao ) {
		if ( localizacao.getRegraAtendimentoLocalizacoes() != null ) {
			Hibernate.initialize(localizacao.getRegraAtendimentoLocalizacoes());
			
			localizacao.getRegraAtendimentoLocalizacoes().forEach(r -> {
				if ( r.getServicos() != null )
					Hibernate.initialize(r.getServicos());
			});
		}
		return localizacao;
	}

	public Localizacao getByIdLoadAll(Object id) throws Exception {
		return super.getById(id, this.functionLoadRegraAtendimentoLocalizacoes);
	}
	
	public PagedList<Localizacao> getListFunctionLoadRegraAtendimentoLocalizacoes(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadRegraAtendimentoLocalizacoes);
	}
}
