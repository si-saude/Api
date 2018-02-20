package br.com.saude.api.model.business;

import java.util.function.Function;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.LocalizacaoBuilder;
import br.com.saude.api.model.creation.builder.example.LocalizacaoExampleBuilder;
import br.com.saude.api.model.entity.filter.LocalizacaoFilter;
import br.com.saude.api.model.entity.po.Localizacao;
import br.com.saude.api.model.persistence.LocalizacaoDao;

public class LocalizacaoBo extends GenericBo<Localizacao, LocalizacaoFilter, LocalizacaoDao, 
									LocalizacaoBuilder, LocalizacaoExampleBuilder> {

	private Function<LocalizacaoBuilder, LocalizacaoBuilder> functionLoadRegraAtendimentoLocalizacoes;

	private static LocalizacaoBo instance;
	
	private LocalizacaoBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadRegraAtendimentoLocalizacoes = builder -> {
			return builder.loadRegraAtendimentoLocalizacoes();
		};
	}
	
	public static LocalizacaoBo getInstance() {
		if(instance==null)
			instance = new LocalizacaoBo();
		return instance;
	}
	
	@Override
	public Localizacao getById(Object id) throws Exception {
		return this.getByEntity(getDao().getByIdLoadAll(id), this.functionLoadRegraAtendimentoLocalizacoes);
	}
	
	public PagedList<Localizacao> getListLoadRegraAtendimentoLocalizacoes(LocalizacaoFilter filter)
			throws Exception {
		return this.getList(getDao().getListFunctionLoadRegraAtendimentoLocalizacoes(getExampleBuilder(filter).example()),
				this.functionLoadRegraAtendimentoLocalizacoes);
	}

	@Override
	public Localizacao save(Localizacao localizacao) throws Exception {
		
		localizacao.getRegraAtendimentoLocalizacoes().forEach(r -> r.setLocalizacao(localizacao));
		
		return super.save(localizacao);
	}
	
	
}
