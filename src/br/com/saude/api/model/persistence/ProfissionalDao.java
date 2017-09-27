package br.com.saude.api.model.persistence;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.example.ProfissionalExampleBuilder;
import br.com.saude.api.model.entity.filter.ProfissionalFilter;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.Telefone;

public class ProfissionalDao extends GenericDao<Profissional> {

	private static ProfissionalDao instance;
	
	private ProfissionalDao() {
		super();
	}
	
	public static ProfissionalDao getInstance() {
		if(instance == null)
			instance = new ProfissionalDao();
		return instance;
	}
	
	public Profissional getByIdLoadAll(Object id) throws Exception {
		return this.getById(id, "loadAll");
	}
	
	public PagedList<Profissional> 
		getListLoadEquipeLocalizacaoGerenciaFuncao(ProfissionalExampleBuilder exampleBuilder) throws Exception{
		return this.getList(exampleBuilder, "loadEquipeLocalizacaoGerenciaFuncao");
	}
	
	@Override
	protected Criteria finishCriteria(Criteria criteria, GenericExampleBuilder<?, ?> profissionalExampleBuilder) {
		ProfissionalFilter filter = (ProfissionalFilter)profissionalExampleBuilder.getFilter();
		
		if(filter.getGerencia() != null && filter.getGerencia().getCodigoCompleto() != null) {
			String[] gerencias = filter.getGerencia().getCodigoCompleto().split("/");
			
			criteria.createAlias("gerencia", "gerencia0", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("gerencia0.gerencia", "gerencia1", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("gerencia1.gerencia", "gerencia2", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("gerencia2.gerencia", "gerencia3", JoinType.LEFT_OUTER_JOIN);
			
			Criterion or = null;
			int i = 0;
			for(int x = gerencias.length - 1; x <= 3; x++) {
				Criterion and = null;
				for(int y = i; y < gerencias.length + i; y++) {
					if(and == null) {
						and = Restrictions.ilike("gerencia"+y+".codigo",gerencias[x-y]);
					}else {
						and = Restrictions.and(and, Restrictions.ilike("gerencia"+y+".codigo",Helper.filterLike(gerencias[x-y])));
					}
				}
				
				if(or == null) {
					or = and;
				}else {
					or = Restrictions.or(or, and);
				}
				i++;
			}
			
			criteria.add(or);
		}
		return criteria;
	}
	
	@SuppressWarnings("unused")
	private Profissional loadEquipeLocalizacaoGerenciaFuncao(Profissional profissional) {
		profissional = loadEquipe(profissional);
		profissional = loadLocalizacao(profissional);
		profissional = loadGerencia(profissional);
		profissional = loadFuncao(profissional);
		return profissional;
	}
	
	@SuppressWarnings("unused")
	private Profissional loadAll(Profissional profissional) {
		profissional = loadTelefones(profissional);
		profissional = loadEndereco(profissional);
		profissional = loadEquipe(profissional);
		profissional = loadLocalizacao(profissional);
		profissional = loadGerencia(profissional);
		profissional = loadFuncao(profissional);
		return profissional;
	}
	
	private Profissional loadTelefones(Profissional profissional) {
		if(profissional.getTelefones()!=null)
			Hibernate.initialize(profissional.getTelefones());
		return profissional;
	}
	
	private Profissional loadEndereco(Profissional profissional) {
		if(profissional.getEndereco()!=null)
			Hibernate.initialize(profissional.getEndereco());
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
	
	private Profissional loadGerencia(Profissional profissional) {
		if(profissional.getGerencia()!=null)
			Hibernate.initialize(profissional.getGerencia());
		return profissional;
	}
	
	private Profissional loadFuncao(Profissional profissional) {
		if(profissional.getFuncao()!=null)
			Hibernate.initialize(profissional.getFuncao());
		return profissional;
	}
	
	@Override
	public Profissional save(Profissional profissional) throws Exception {
		return super.save(profissional,"beforeCommitSave");
	}
	
	@SuppressWarnings({"unused" })
	private Profissional beforeCommitSave(Profissional profissional, Session session) {		
		if(profissional.getTelefones() != null)
			for(int i=0; i < profissional.getTelefones().size(); i++)
				profissional.getTelefones().set(i, session.get(Telefone.class, profissional.getTelefones().get(i).getId()));
		
		return profissional;
	}
}
