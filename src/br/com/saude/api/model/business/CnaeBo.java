package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.CnaeBuilder;
import br.com.saude.api.model.creation.builder.example.CnaeExampleBuilder;
import br.com.saude.api.model.entity.filter.CnaeFilter;
import br.com.saude.api.model.entity.po.Cnae;
import br.com.saude.api.model.persistence.CnaeDao;

public class CnaeBo extends GenericBo<Cnae, CnaeFilter, CnaeDao, CnaeBuilder, CnaeExampleBuilder> {
	private static CnaeBo instance;
	
	private CnaeBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadEmpresa();
		};
	}
	
	public static CnaeBo getInstance() {
		if(instance==null)
			instance = new CnaeBo();
		return instance;
	}
	
	@Override
	public Cnae getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	public PagedList<Cnae> getListAll(CnaeFilter filter) throws Exception {
		return super.getList(getDao().getListFunctionLoadAll(getExampleBuilder(filter).example()), this.functionLoadAll);
	}
}
