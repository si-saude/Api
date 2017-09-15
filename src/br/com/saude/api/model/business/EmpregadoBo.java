package br.com.saude.api.model.business;

import java.util.List;

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
	
	public List<Empregado> getList(EmpregadoFilter filter) throws Exception{
		List<Empregado> empregados = EmpregadoDao.getInstance()
				.getList(EmpregadoExampleBuilder.newInstance(filter).getExample()); 
		return EmpregadoBuilder.newInstance(empregados).getEntityList();
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
