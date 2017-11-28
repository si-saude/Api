package br.com.saude.api.model.persistence;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.po.Aso;

public class AsoDao extends GenericDao<Aso> {

	private static AsoDao instance;
	
	private AsoDao() {
		super();
	}
	
	public static AsoDao getInstance() {
		if(instance==null)
			instance = new AsoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		
	}
	
	@SuppressWarnings("deprecation")
	public Aso getUltimoByEmpregado(Aso aso) {
		Session session = HibernateHelper.getSession();
		
		try {
			aso = (Aso) session.createCriteria(Aso.class)
					.createAlias("empregadoConvocacao", "empregadoConvocacao")
					.createAlias("empregadoConvocacao.empregado", "empregado")
					.add(Restrictions.eq("empregado.id", 
							aso.getEmpregadoConvocacao().getEmpregado().getId()))
					.addOrder(Order.desc("validade"))
					.uniqueResult();
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			session.close();
		}
		
		return aso;
	}
}
