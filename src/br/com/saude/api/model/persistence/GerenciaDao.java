package br.com.saude.api.model.persistence;

import org.hibernate.Criteria;
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
			
			Criteria gerenciaCriteria = criteria;
			
			for(int i = 3; i>= 0; i--) {
				if(i<3)
					gerenciaCriteria = gerenciaCriteria.createCriteria("gerencia", JoinType.LEFT_OUTER_JOIN);
				if(gerencias.length > i)
					gerenciaCriteria.add(Restrictions.ilike("codigo", Helper.filterLike(gerencias[i])));
			}
		}
		
		return criteria;
	}
}
