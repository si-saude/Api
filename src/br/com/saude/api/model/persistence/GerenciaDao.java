package br.com.saude.api.model.persistence;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.model.entity.filter.GerenciaFilter;
import br.com.saude.api.model.entity.po.Gerencia;

public class GerenciaDao extends GenericDao<Gerencia> {

	private static GerenciaDao instance;
	
	private GerenciaDao() {
		super();
	}
	
	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = gerencia -> {
			gerencia = loadGerente(gerencia);
			gerencia = loadSecretario1(gerencia);
			gerencia = loadSecretario2(gerencia);
			return gerencia;
		};
	}
	
	public static GerenciaDao getInstance() {
		if(instance==null)
			instance = new GerenciaDao();
		return instance;
	}
	
	@Override
	protected Criteria finishCriteria(Criteria criteria, GenericExampleBuilder<?, ?> gerenciaExampleBuilder) {
		
		GerenciaFilter filter = (GerenciaFilter) gerenciaExampleBuilder.getFilter();
		
		if(filter.getCodigoCompleto() != null) {
			String[] gerencias = filter.getCodigoCompleto().split("/");
			
			criteria.createAlias("gerencia", "gerencia1", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("gerencia1.gerencia", "gerencia2", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("gerencia2.gerencia", "gerencia3", JoinType.LEFT_OUTER_JOIN);
			
			Criterion or = null;
			int i = 0;
			for(int x = gerencias.length - 1; x <= 3; x++) {
				Criterion and = null;
				for(int y = i; y < gerencias.length + i; y++) {
					if(and == null) {
						and = Restrictions.ilike(checarCondicao("gerencia"+y),gerencias[x-y]);
					}else {
						and = Restrictions.and(and, Restrictions.ilike(checarCondicao("gerencia"+y),Helper.filterLike(gerencias[x-y])));
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
	
	private String checarCondicao(String condicao) {
		if(condicao.contains("0"))
			return "codigo";
		return condicao+".codigo";
	}
	
	private Gerencia loadGerente(Gerencia gerencia) {
		if(gerencia.getGerente()!=null)
			Hibernate.initialize(gerencia.getGerente());
		return gerencia;
	}
	
	private Gerencia loadSecretario1(Gerencia gerencia) {
		if(gerencia.getSecretario1()!=null)
			Hibernate.initialize(gerencia.getSecretario1());
		return gerencia;
	}
	
	private Gerencia loadSecretario2(Gerencia gerencia) {
		if(gerencia.getSecretario2()!=null)
			Hibernate.initialize(gerencia.getSecretario2());
		return gerencia;
	}
	
	public Gerencia getByIdLoadAll(Object id) throws Exception {
		return this.getById(id,this.functionLoadAll);
	}
}
