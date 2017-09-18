package br.com.saude.api.model.business;

import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.EmpregadoBuilder;
import br.com.saude.api.model.creation.builder.example.EmpregadoExampleBuilder;
import br.com.saude.api.model.entity.filter.EmpregadoFilter;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.persistence.EmpregadoDao;

public class EmpregadoBo {
	
	private static EmpregadoBo instance;
	
	private EmpregadoBo() {
		
	}
	
	public static EmpregadoBo getInstance() {
		if(instance == null)
			instance = new EmpregadoBo();
		return instance;
	}
	
	public PagedList<Empregado> getList(EmpregadoFilter filter) throws Exception{
		PagedList<Empregado> empregados = EmpregadoDao.getInstance()
				.getList(EmpregadoExampleBuilder.newInstance(filter).example());
		empregados.setList(EmpregadoBuilder.newInstance(empregados.getList()).getEntityList());
		return empregados;
	}
	
	public Empregado getById(int id) throws Exception {
		Empregado empregado = EmpregadoDao.getInstance().getById(id); 
		return EmpregadoBuilder.newInstance(empregado).getEntity();
	}
	
	public Empregado save(Empregado empregado) throws Exception {
		empregado = EmpregadoDao.getInstance().save(empregado);
		return EmpregadoBuilder.newInstance(empregado).getEntity(); 
	}
	
	public void delete(int id) {
		EmpregadoDao.getInstance().delete(id);
	}
}
