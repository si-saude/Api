package br.com.saude.api.model.business;

import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.EquipeBuilder;
import br.com.saude.api.model.creation.builder.example.EquipeExampleBuilder;
import br.com.saude.api.model.entity.filter.EquipeFilter;
import br.com.saude.api.model.entity.po.Equipe;
import br.com.saude.api.model.persistence.EquipeDao;

public class EquipeBo {
	
	private static EquipeBo instance;
	
	private EquipeBo() {
		
	}
	
	public static EquipeBo getInstance() {
		if(instance==null)
			instance = new EquipeBo();
		return instance;
	}
	
	public PagedList<Equipe> getList(EquipeFilter filter) throws Exception{
		PagedList<Equipe> equipes = EquipeDao.getInstance()
				.getList(EquipeExampleBuilder.newInstance(filter).example());
		equipes.setList(EquipeBuilder.newInstance(equipes.getList()).getEntityList());
		return equipes;
	}
	
	public Equipe getById(int id) throws Exception {
		Equipe equipe = EquipeDao.getInstance().getById(id);
		return EquipeBuilder.newInstance(equipe).getEntity();
	}
	
	public Equipe save(Equipe equipe) throws Exception {
		equipe = EquipeDao.getInstance().save(equipe);
		return EquipeBuilder.newInstance(equipe).getEntity();
	}
	
	public void delete(int id) {
		EquipeDao.getInstance().delete(id);
	}
}
