package br.com.saude.api.model.business;

import java.util.List;

import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.LocalizacaoBuilder;
import br.com.saude.api.model.creation.builder.example.LocalizacaoExampleBuilder;
import br.com.saude.api.model.entity.filter.LocalizacaoFilter;
import br.com.saude.api.model.entity.po.Localizacao;
import br.com.saude.api.model.persistence.LocalizacaoDao;

public class LocalizacaoBo {

	private static LocalizacaoBo instance;
	
	private LocalizacaoBo() {
		
	}
	
	public static LocalizacaoBo getInstance() {
		if(instance==null)
			instance = new LocalizacaoBo();
		return instance;
	}
	
	public PagedList<Localizacao> getList(LocalizacaoFilter filter) throws Exception{
		PagedList<Localizacao> localizacoes = LocalizacaoDao.getInstance()
				.getList(LocalizacaoExampleBuilder.newInstance(filter).example());
		localizacoes
			.setList(LocalizacaoBuilder.newInstance(localizacoes.getList()).getEntityList());
		return localizacoes;
	}
	
	public List<Localizacao> getSelectList(LocalizacaoFilter filter) throws Exception{
		PagedList<Localizacao> localizacoes = LocalizacaoDao.getInstance()
				.getList(LocalizacaoExampleBuilder.newInstance(filter).exampleSelectList());
		return LocalizacaoBuilder.newInstance(localizacoes.getList()).getEntityList();
	}
	
	public Localizacao getById(int id) throws Exception {
		Localizacao localizacao = LocalizacaoDao.getInstance().getById(id);
		return LocalizacaoBuilder.newInstance(localizacao).getEntity();
	}
	
	public Localizacao save(Localizacao localizacao) throws Exception {
		localizacao = LocalizacaoDao.getInstance().save(localizacao);
		return LocalizacaoBuilder.newInstance(localizacao).getEntity();
	}
	
	public void delete(int id) {
		LocalizacaoDao.getInstance().delete(id);
	}
}
