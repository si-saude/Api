package br.com.saude.api.model.business;

import java.util.List;

import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.GerenciaBuilder;
import br.com.saude.api.model.creation.builder.example.GerenciaExampleBuilder;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.po.Gerencia;
import br.com.saude.api.model.persistence.GerenciaDao;

public class GerenciaBo {

	private static GerenciaBo instance;
	
	private GerenciaBo() {
		
	}
	
	public static GerenciaBo getInstance() {
		if(instance==null)
			instance = new GerenciaBo();
		return instance;
	}
	
	public PagedList<Gerencia> getList(GerenciaFilter filter) throws Exception{
		PagedList<Gerencia> gerencias = GerenciaDao.getInstance()
				.getList(GerenciaExampleBuilder.newInstance(filter).example());
		gerencias.setList(GerenciaBuilder.newInstance(gerencias.getList()).getEntityList());
		return gerencias;
	}
	
	public List<Gerencia> getSelectList(GerenciaFilter filter) throws Exception{
		PagedList<Gerencia> gerencias = GerenciaDao.getInstance()
				.getList(GerenciaExampleBuilder.newInstance(filter).exampleSelectList());
		return GerenciaBuilder.newInstance(gerencias.getList()).getEntityList();
	}
	
	public Gerencia getById(int id) throws Exception {
		Gerencia gerencia = GerenciaDao.getInstance().getById(id);
		return GerenciaBuilder.newInstance(gerencia).getEntity();
	}
	
	public Gerencia save(Gerencia gerencia) throws Exception {
		gerencia = GerenciaDao.getInstance().save(gerencia);
		return GerenciaBuilder.newInstance(gerencia).getEntity();
	}
	
	public void delete(int id) {
		GerenciaDao.getInstance().delete(id);
	}
}
