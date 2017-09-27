package br.com.saude.api.model.business;

import java.util.List;

import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.CursoBuilder;
import br.com.saude.api.model.creation.builder.example.CursoExampleBuilder;
import br.com.saude.api.model.entity.filter.CursoFilter;
import br.com.saude.api.model.entity.po.Curso;
import br.com.saude.api.model.persistence.CursoDao;

public class CursoBo {

	private static CursoBo instance;
	
	private CursoBo() {
		
	}
	
	public static CursoBo getInstance() {
		if(instance==null)
			instance = new CursoBo();
		return instance;
	}
	
	public PagedList<Curso> getList(CursoFilter filter) throws Exception{
		PagedList<Curso> cursos = CursoDao.getInstance()
				.getList(CursoExampleBuilder.newInstance(filter).example());
		cursos.setList(CursoBuilder.newInstance(cursos.getList()).getEntityList());
		return cursos;
	}
	
	public List<Curso> getSelectList(CursoFilter filter) throws Exception{
		PagedList<Curso> cursos = CursoDao.getInstance()
				.getList(CursoExampleBuilder.newInstance(filter).exampleSelectList());
		return CursoBuilder.newInstance(cursos.getList()).getEntityList();
	}
	
	public Curso getById(int id) throws Exception {
		Curso curso = CursoDao.getInstance().getById(id);
		return CursoBuilder.newInstance(curso).getEntity();
	}
	
	public Curso save(Curso curso) throws Exception {
		curso = CursoDao.getInstance().save(curso);
		return CursoBuilder.newInstance(curso).getEntity();
	}
	
	public void delete(int id) {
		CursoDao.getInstance().delete(id);
	}
}
