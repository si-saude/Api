package br.com.saude.api.model.business;

import java.util.List;

import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.FuncaoBuilder;
import br.com.saude.api.model.creation.builder.example.FuncaoExampleBuilder;
import br.com.saude.api.model.entity.filter.FuncaoFilter;
import br.com.saude.api.model.entity.po.Funcao;
import br.com.saude.api.model.persistence.FuncaoDao;

public class FuncaoBo {
	
	private static FuncaoBo instance;
	
	private FuncaoBo() {
		
	}
	
	public static FuncaoBo getInstance() {
		if(instance==null)
			instance = new FuncaoBo();
		return instance;
	}
	
	public PagedList<Funcao> getList(FuncaoFilter filter) throws Exception{
		PagedList<Funcao> funcoes = FuncaoDao.getInstance()
				.getList(FuncaoExampleBuilder.newInstance(filter).example());
		funcoes.setList(FuncaoBuilder.newInstance(funcoes.getList()).getEntityList());
		return funcoes;
	}
	
	public List<Funcao> getSelectList(FuncaoFilter filter) throws Exception{
		PagedList<Funcao> funcoes = FuncaoDao.getInstance()
				.getList(FuncaoExampleBuilder.newInstance(filter).exampleSelectList());
		return FuncaoBuilder.newInstance(funcoes.getList()).getEntityList();
	}
	
	public Funcao getById(int id) throws Exception {
		Funcao funcao = FuncaoDao.getInstance().getById(id);
		return FuncaoBuilder.newInstance(funcao).getEntity();
	}
	
	public Funcao save(Funcao funcao) throws Exception {
		funcao = FuncaoDao.getInstance().save(funcao);
		return FuncaoBuilder.newInstance(funcao).getEntity();
	}
	
	public void delete(int id) {
		FuncaoDao.getInstance().delete(id);
	}
}
