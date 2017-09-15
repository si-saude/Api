package br.com.saude.api.model.business;

import java.util.List;

import br.com.saude.api.model.creation.builder.entity.ExameBuilder;
import br.com.saude.api.model.creation.builder.example.ExameExampleBuilder;
import br.com.saude.api.model.entity.filter.ExameFilter;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.persistence.ExameDao;

public class ExameBo {
	
	private static ExameBo instance;
	
	private ExameBo() {
		
	}
	
	public static ExameBo getInstance() {
		if(instance == null)
			instance = new ExameBo();
		return instance;
	}
	
	public List<Exame> getListLoadEmpregado(ExameFilter filter) throws Exception{
		List<Exame> exames = ExameDao.getInstance()
				.getList(ExameExampleBuilder.newInstance(filter).getExample()); 
		
		return ExameBuilder.newInstance(exames).getEntityList();
	}
	
	public List<Exame> getList(ExameFilter filter) throws Exception{
		List<Exame> exames = ExameDao.getInstance()
				.getListLoadEmpregado(ExameExampleBuilder.newInstance(filter).getExample()); 
		
		return ExameBuilder.newInstance(exames).loadEmpregado().getEntityList();
	}
	
	public Exame getById(int id) throws Exception {
		Exame exame = ExameDao.getInstance().getById(id); 
		
		return ExameBuilder.newInstance(exame).getEntity();
	}
	
	public Exame getByIdLoadEmpregado(int id) throws Exception {
		Exame exame = ExameDao.getInstance().getByIdLoadEmpregado(id); 
		
		return ExameBuilder.newInstance(exame).loadEmpregado().getEntity();
	}
	
	public Exame save(Exame exame) throws Exception {
		exame = ExameDao.getInstance().save(exame); 
		
		return ExameBuilder.newInstance(exame).getEntity();
	}
	
	public void delete(int id) {
		ExameDao.getInstance().delete(id);
	}
}
