package br.com.saude.api.model.persistence;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Curriculo;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.ProfissionalConselho;
import br.com.saude.api.model.entity.po.Telefone;
import br.com.saude.api.model.entity.po.Vacina;

public class ProfissionalDao extends GenericDao<Profissional> {

	private static ProfissionalDao instance;
	
	private ProfissionalDao() {
		super();
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void initializeFunctions() {
		this.functionLoad = profissional -> {
			profissional = loadEquipe(profissional);
			profissional = loadLocalizacao(profissional);
			profissional = loadCargo(profissional);
			return profissional;
		};
		
		this.functionLoadAll = profissional -> {
			profissional = this.functionLoad.apply(profissional);
			profissional = loadTelefones(profissional);
			profissional = loadProfissionalVacinas(profissional);
			profissional = loadEndereco(profissional);
			profissional = loadCurriculo(profissional);
			profissional = loadProfissionalConselho(profissional);
			return profissional;
		};
		
		this.functionBeforeSave = pair -> {
			Profissional profissional = pair.getValue0();
			Session session = pair.getValue1();
			
			//REMOVE REGISTROS DE TELEFONE �RF�OS
			if(profissional.getId() > 0) {				
				List<Telefone> telefones = (List<Telefone>)session.createCriteria(Telefone.class)
						.createAlias("profissionais", "profissional")
						.add(Restrictions.eq("profissional.id", profissional.getId()))
						.list();
				telefones.forEach(t->{
					if(!profissional.getTelefones().contains(t))
						session.remove(t);
				});
			}
			
			//CARREGAR AS VACINAS
			if(profissional.getProfissionalVacinas() != null)
				for(int i=0; i < profissional.getProfissionalVacinas().size(); i++)
					profissional.getProfissionalVacinas().get(i)
						.setVacina(session.get(Vacina.class, 
											profissional.getProfissionalVacinas().get(i).getId()));
			
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
	
	private Profissional loadProfissionalVacinas(Profissional profissional) {
		if(profissional.getProfissionalVacinas()!=null)
			Hibernate.initialize(profissional.getProfissionalVacinas());
		return profissional;
	}
	
	private Profissional loadEndereco(Profissional profissional) {
		if(profissional.getEndereco()!=null)
			Hibernate.initialize(profissional.getEndereco());
		return profissional;
	}
	
	private Profissional loadCurriculo(Profissional profissional) {
		if(profissional.getCurriculo()!=null) {
			profissional.setCurriculo((Curriculo)Hibernate.unproxy(profissional.getCurriculo()));
			Hibernate.initialize(profissional.getCurriculo().getCurriculoCursos());
		}
		return profissional;
	}
	
	private Profissional loadProfissionalConselho(Profissional profissional) {
		if(profissional.getProfissionalConselho()!=null)
			profissional.setProfissionalConselho((ProfissionalConselho)Hibernate.unproxy(profissional.getProfissionalConselho()));
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
	
	private Profissional loadCargo(Profissional profissional) {
		if(profissional.getCargo()!=null)
			Hibernate.initialize(profissional.getCargo());
		return profissional;
	}
}
