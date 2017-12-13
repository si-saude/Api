package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.Gerencia;
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
		this.functionLoad = resultadoExame -> {
			resultadoExame = loadEmpregadoConvocacao(resultadoExame);
			return resultadoExame;
		};
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
	
	private ResultadoExame loadEmpregadoConvocacao(ResultadoExame resultadoExame) {
		if(resultadoExame.getEmpregadoConvocacao() != null) {
			EmpregadoConvocacao empregadoConvocacao = 
					(EmpregadoConvocacao) Hibernate.unproxy(resultadoExame.getEmpregadoConvocacao());
			
			empregadoConvocacao.setConvocacao((Convocacao) Hibernate.unproxy(empregadoConvocacao.getConvocacao()));
			empregadoConvocacao.setEmpregado((Empregado) Hibernate.unproxy(empregadoConvocacao.getEmpregado()));
			empregadoConvocacao.getEmpregado().setGerencia((Gerencia)Hibernate.unproxy(empregadoConvocacao.getEmpregado().getGerencia()));
			
			resultadoExame.setEmpregadoConvocacao(empregadoConvocacao);
		}
		
		return resultadoExame;
	}
	
	public PagedList<ResultadoExame> getListFunctionLoad(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoad);
	}
}
