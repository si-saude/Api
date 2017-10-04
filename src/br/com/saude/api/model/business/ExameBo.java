package br.com.saude.api.model.business;

import br.com.saude.api.generic.GenericBo;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.ExameBuilder;
import br.com.saude.api.model.creation.builder.example.ExameExampleBuilder;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.persistence.ExameDao;

public class ExameBo extends GenericBo<Exame, ExameFilter, ExameDao, ExameBuilder, 
								ExameExampleBuilder>{
	
	private static ExameBo instance;
	
	private ExameBo() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = builder -> {
			return builder.loadEmpregado();
		};
	}
	
	public static ExameBo getInstance() {
		if(instance == null)
			instance = new ExameBo();
		return instance;
	}
	
	public PagedList<Exame> getListLoadEmpregado(ExameFilter filter) throws Exception{
		return getList(getDao().getListLoadEmpregado(getExampleBuilder(filter).example()),
						this.functionLoadAll);
	}
	
	public Exame getByIdLoadEmpregado(int id) throws Exception {
		return getByEntity(getDao().getByIdLoadEmpregado(id),this.functionLoadAll);
	}
}
