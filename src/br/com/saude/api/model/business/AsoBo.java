package br.com.saude.api.model.business;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.AsoBuilder;
import br.com.saude.api.model.creation.builder.example.AsoExampleBuilder;
import br.com.saude.api.model.entity.filter.AsoFilter;
import br.com.saude.api.model.entity.po.Aso;
import br.com.saude.api.model.entity.po.AsoAlteracao;
import br.com.saude.api.model.persistence.AsoDao;

public class AsoBo 
	extends GenericBo<Aso, AsoFilter, AsoDao, AsoBuilder, AsoExampleBuilder> {
	
	private static AsoBo instance;
	
	private AsoBo() {
		super();
	}
	
	public static AsoBo getInstance() {
		if(instance==null)
			instance = new AsoBo();
		return instance;
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoad = builder -> {
			return builder.loadAtendimento().loadEmpregado();
		};
		
		this.functionLoadAll = builder -> {
			return this.functionLoad.apply(builder).loadAlteracoes();
		};
	}

	public Aso getUltimoByEmpregado(Aso aso) throws Exception{
		return getDao().getUltimoByEmpregado(aso);
	}
	
	@Override
	public Aso getById(Object id) throws Exception {
		return getByEntity(getDao().getByIdLoadAll(id), this.functionLoadAll);
	}
	
	@Override
	public PagedList<Aso> getList(AsoFilter filter) throws Exception {
		return super.getList(filter, this.functionLoad);
	}
	
	public PagedList<Aso> getListLoadAll(AsoFilter filter) throws Exception {
		return super.getList(getDao().getListLoadAll(getExampleBuilder(filter).example()), 
				this.functionLoadAll);
	}
	
	@Override
	public Aso save(Aso aso) throws Exception {
		
//		if(aso.getAsoAlteracoes() == null)
//			aso.setAsoAlteracoes(new ArrayList<AsoAlteracao>());
//		
//		AsoAlteracao asoAlteracao = new AsoAlteracao();
//		asoAlteracao.setAso(aso);
//		asoAlteracao.setData(Helper.getNow());
//		
		
		return super.save(aso);
	}
}
