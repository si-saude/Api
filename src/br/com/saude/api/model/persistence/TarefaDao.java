package br.com.saude.api.model.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.creation.builder.entity.TarefaBuilder;
import br.com.saude.api.model.entity.filter.TarefaFilter;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.model.entity.po.Triagem;
import br.com.saude.api.util.constant.GrupoServico;
import br.com.saude.api.util.constant.StatusTarefa;

public class TarefaDao extends GenericDao<Tarefa> {

	private static TarefaDao instance;
	
	private TarefaDao() {
		super();
	}
	
	public static TarefaDao getInstance() {
		if(instance == null)
			instance = new TarefaDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	@SuppressWarnings("deprecation")
	public void gerarCancelamentoAtendimento() {
		Session session = HibernateHelper.getSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			
			StringBuilder queryBuilder = new StringBuilder("UPDATE tarefa t ");
			queryBuilder.append(" SET status = '"+StatusTarefa.getInstance().CANCELADA+"' ");
			queryBuilder.append(" FROM servico s ");
			queryBuilder.append(" WHERE t.servico_id = s.id ");
			queryBuilder.append(" and t.status = '"+StatusTarefa.getInstance().ABERTA+"' ");
			queryBuilder.append(" and s.grupo = '"+GrupoServico.ATENDIMENTO_OCUPACIONAL+"' ");
			queryBuilder.append(" and t.inicio < date_trunc('day', now()) ");
			queryBuilder.append(" and not exists (select 1 from filaesperaocupacional fe " + 
					"		  where fe.empregado_id = t.cliente_id " + 
					"		    and date_trunc('day', t.inicio) = date_trunc('day', fe.horarioCheckin)) ");
			
			session.createSQLQuery(queryBuilder.toString()).executeUpdate();
			
			transaction.commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			HibernateHelper.close(session);
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public PagedList<Tarefa> getListAtendimentoAvulso(GenericExampleBuilder<Tarefa,TarefaFilter> exampleBuilder) throws Exception {
		List<Tarefa> list;
		PagedList<Tarefa> pagedList = new PagedList<Tarefa>();
		List<Criterion> criterions = exampleBuilder.getCriterions();
		Session session = HibernateHelper.getSession();
		
		try {
			Criteria criteria = session.createCriteria(this.entityType,"main");
			criteria.setFirstResult((exampleBuilder.getPageNumber() - 1) * exampleBuilder.getPageSize());
			criteria.setMaxResults(exampleBuilder.getPageSize());
			
			if(criterions != null)
				for(Criterion criterion : criterions)
					criteria.add(criterion);
			
			criteria = Helper.loopCriterias(criteria, exampleBuilder.getCriterias());			
			criteria = finishCriteria(criteria,exampleBuilder);
			
			criteria.add(Restrictions.or(Restrictions.or(Restrictions.eq("status", StatusTarefa.getInstance().ABERTA), 
														Restrictions.eq("status", StatusTarefa.getInstance().EXECUCAO)),
										Restrictions.and(Restrictions.eq("status", StatusTarefa.getInstance().CONCLUIDA), 
										Restrictions.conjunction().add(Subqueries.notExists(
												DetachedCriteria.forClass(Triagem.class,"t")
													.createAlias("t.atendimento", "a", JoinType.INNER_JOIN)
													.createAlias("a.tarefa", "tt", JoinType.INNER_JOIN)
													.setProjection(Projections.id())
													.add(Restrictions.eqProperty("tt.id", "main.id")))))));
			list = criteria.list();			
			
			pagedList.setTotal(getCount(session, exampleBuilder));
			pagedList.setList(TarefaBuilder.newInstance(list).getEntityList());
			pagedList.setPageNumber(exampleBuilder.getPageNumber());
			pagedList.setPageSize(exampleBuilder.getPageSize());
			
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		return pagedList;
	}
}
