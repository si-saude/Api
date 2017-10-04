package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import java.util.List;

import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.CidadeBuilder;
import br.com.saude.api.model.creation.builder.example.CidadeExampleBuilder;
import br.com.saude.api.model.entity.filter.CidadeFilter;
import br.com.saude.api.model.entity.po.Cidade;
import br.com.saude.api.model.persistence.CidadeDao;

public class CidadeBo extends GenericBo<Cidade, CidadeFilter, CidadeDao, CidadeBuilder,
								CidadeExampleBuilder> {
	
	private static CidadeBo instance;
	
	private CidadeBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		
	}
	
	public static CidadeBo getInstance() {
		if(instance==null)
			instance = new CidadeBo();
		return instance;
	}
	
	public PagedList<Cidade> getList(CidadeFilter filter) throws Exception{
		PagedList<Cidade> cidades = CidadeDao.getInstance()
				.getList(CidadeExampleBuilder.newInstance(filter).example());
		cidades.setList(CidadeBuilder.newInstance(cidades.getList()).getEntityList());
		return cidades;
	}
	
	public List<Cidade> getSelectList(CidadeFilter filter) throws Exception{
		PagedList<Cidade> cidades = CidadeDao.getInstance()
				.getList(CidadeExampleBuilder.newInstance(filter).exampleSelectList());
		return CidadeBuilder.newInstance(cidades.getList()).getEntityList();
	}
	
	public Cidade getById(int id) throws Exception {
		Cidade cidade = CidadeDao.getInstance().getById(id);
		return CidadeBuilder.newInstance(cidade).getEntity();
	}
	
	public Cidade save(Cidade cidade) throws Exception {
		cidade = CidadeDao.getInstance().save(cidade);
		return CidadeBuilder.newInstance(cidade).getEntity();
	}
	
	public void delete(int id) {
		CidadeDao.getInstance().delete(id);
	}
}
