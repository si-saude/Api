package br.com.saude.api.model.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.po.Tarefa;
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
			
			transaction.commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			HibernateHelper.close(session);
		}
	}
}
