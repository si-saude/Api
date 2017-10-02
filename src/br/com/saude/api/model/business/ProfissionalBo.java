package br.com.saude.api.model.business;

import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.ProfissionalBuilder;
import br.com.saude.api.model.creation.builder.example.ProfissionalExampleBuilder;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.persistence.ProfissionalDao;

public class ProfissionalBo {
	
	private static ProfissionalBo instance;
	
	private ProfissionalBo() {
		
	}
	
	public static ProfissionalBo getInstance() {
		if(instance==null)
			instance = new ProfissionalBo();
		return instance;
	}
	
	public PagedList<Profissional> getList(ProfissionalFilter filter) throws Exception{
		PagedList<Profissional> profissionais = ProfissionalDao.getInstance()
				.getListLoadEquipeLocalizacaoFuncao(ProfissionalExampleBuilder
												.newInstance(filter).example());
		profissionais.setList(ProfissionalBuilder.newInstance(profissionais.getList())
									.loadEquipe().loadLocalizacao().loadFuncao().getEntityList());
		return profissionais;
	}
	
	public Profissional getById(int id) throws Exception {
		Profissional profissional = ProfissionalDao.getInstance().getByIdLoadAll(id);
		return ProfissionalBuilder.newInstance(profissional)
				.loadFuncao().loadEndereco().loadEquipe().loadLocalizacao()
				.loadCurriculo().loadCurriculo().loadTelefones().loadVacinas().getEntity();
	}
	
	public Profissional save(Profissional profissional) throws Exception {
		profissional = ProfissionalDao.getInstance().save(profissional);
		return ProfissionalBuilder.newInstance(profissional).getEntity();
	}
	
	public void delete(int id) {
		ProfissionalDao.getInstance().delete(id);
	}
}
