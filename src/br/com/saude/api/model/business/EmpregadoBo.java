package br.com.saude.api.model.business;

import java.util.List;

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
		return EmpregadoDao.getInstance()
				.getList(new EmpregadoExampleBuilder(filter).getExample());
	}
	
	public Empregado getById(int id) throws Exception {
		return EmpregadoDao.getInstance().getById(id);
	}
	
	public Empregado save(Empregado empregado) throws Exception {
		return EmpregadoDao.getInstance().save(empregado);
	}
	
	public void delete(int id) {
		EmpregadoDao.getInstance().delete(id);
	}
}
