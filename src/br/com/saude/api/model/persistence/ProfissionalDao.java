package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.Telefone;

public class ProfissionalDao extends GenericDao<Profissional> {

	private static ProfissionalDao instance;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	private ProfissionalDao() {
		super();
		
		//CRIAÇÃO DAS FUNÇÕES
		this.functionLoad = profissional -> {
			profissional = loadEquipe(profissional);
			profissional = loadLocalizacao(profissional);
			profissional = loadFuncao(profissional);
			return profissional;
		};
		
		this.functionLoadAll = profissional -> {
			profissional = loadTelefones(profissional);
			profissional = loadVacinas(profissional);
			profissional = loadEndereco(profissional);
			profissional = loadEquipe(profissional);
			profissional = loadLocalizacao(profissional);
			profissional = loadFuncao(profissional);
			profissional = loadCurriculo(profissional);
			profissional = loadProfissionalConselho(profissional);
			return profissional;
		};
		
		this.functionBeforeSave = pair -> {
			Profissional profissional = pair.getValue0();
			Session session = pair.getValue1();
			
			//REMOVE REGISTROS DE TELEFONE ÓRFÃOS
			if(profissional.getId() > 0) {
				if(profissional.getTelefones() == null)
					profissional.setTelefones(new ArrayList<Telefone>());
				
				List<Telefone> telefones = (List<Telefone>)session.createCriteria(Telefone.class)
						.createAlias("profissionais", "profissional")
						.add(Restrictions.eq("profissional.id", profissional.getId()))
						.list();
				telefones.forEach(t->{
					if(!profissional.getTelefones().contains(t))
						session.remove(t);
				});
			}
			
			//SETA O PROFISSIONAL NAS VACINAS
			profissional.getVacinas().forEach(v->v.setProfissional(profissional));
			return profissional;
		};
	}
	
	public static ProfissionalDao getInstance() {
		if(instance == null)
			instance = new ProfissionalDao();
		return instance;
	}
	
	public Profissional getByIdLoadAll(Object id) throws Exception {
		return this.getById(id, this.functionLoadAll);
	}
	
	public PagedList<Profissional> 
		getListLoadEquipeLocalizacaoFuncao(GenericExampleBuilder<?,?> exampleBuilder) throws Exception{
		return this.getList(exampleBuilder, this.functionLoad);
	}
	
	private Profissional loadTelefones(Profissional profissional) {
		if(profissional.getTelefones()!=null)
			Hibernate.initialize(profissional.getTelefones());
		return profissional;
	}
	
	private Profissional loadVacinas(Profissional profissional) {
		if(profissional.getVacinas()!=null)
			Hibernate.initialize(profissional.getVacinas());
		return profissional;
	}
	
	private Profissional loadEndereco(Profissional profissional) {
		if(profissional.getEndereco()!=null)
			Hibernate.initialize(profissional.getEndereco());
		return profissional;
	}
	
	private Profissional loadCurriculo(Profissional profissional) {
		if(profissional.getCurriculo()!=null)
			Hibernate.initialize(profissional.getCurriculo());
		return profissional;
	}
	
	private Profissional loadProfissionalConselho(Profissional profissional) {
		if(profissional.getProfissionalConselho()!=null)
			Hibernate.initialize(profissional.getProfissionalConselho());
		return profissional;
	}
	
	private Profissional loadEquipe(Profissional profissional) {
		if(profissional.getEquipe()!=null)
			Hibernate.initialize(profissional.getEquipe());
		return profissional;
	}
	
	private Profissional loadLocalizacao(Profissional profissional) {
		if(profissional.getLocalizacao()!=null)
			Hibernate.initialize(profissional.getLocalizacao());
		return profissional;
	}
	
	private Profissional loadFuncao(Profissional profissional) {
		if(profissional.getFuncao()!=null)
			Hibernate.initialize(profissional.getFuncao());
		return profissional;
	}
}
