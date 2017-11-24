package br.com.saude.api.model.persistence;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.po.ResultadoExame;

public class ResultadoExameDao extends GenericDao<ResultadoExame> {

	private static ResultadoExameDao instance;
	
	private ResultadoExameDao() {
		super();
	}
	
	public static ResultadoExameDao getInstance() {
		if(instance==null)
			instance = new ResultadoExameDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	@SuppressWarnings("deprecation")
	public ResultadoExame getUltimoByEmpregadoExame(ResultadoExame resultadoExame) {
		Session session = HibernateHelper.getSession();
		
		try {
			resultadoExame = (ResultadoExame) session.createCriteria(ResultadoExame.class)
					.createAlias("empregadoConvocacao", "empregadoConvocacao")
					.createAlias("empregadoConvocacao.empregado", "empregado")
					.createAlias("exame", "exame")
					.add(Restrictions.eq("empregado.id", 
							resultadoExame.getEmpregadoConvocacao().getEmpregado().getId()))
					.add(Restrictions.eq("exame.id", resultadoExame.getExame().getId()))
					.addOrder(Order.desc("data"))
					.uniqueResult();
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			session.close();
		}
		
		return resultadoExame;
	}
}
