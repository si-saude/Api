package br.com.saude.api.model.persistence;

import java.util.function.Function;

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
import br.com.saude.api.model.entity.po.Pessoa;
import br.com.saude.api.model.entity.po.ResultadoExame;

public class ResultadoExameDao extends GenericDao<ResultadoExame> {

	private Function<ResultadoExame,ResultadoExame> loadItemResultadoExames;
	
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
		
		this.functionLoadAll = resultadoExame -> {
			resultadoExame = this.functionLoad.apply(resultadoExame);
			resultadoExame = loadItemResultadoExames(resultadoExame);
			return resultadoExame;
		};
		
		this.loadItemResultadoExames = resultadoExame -> {
			resultadoExame = loadItemResultadoExames(resultadoExame);
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
					.add(Restrictions.eq("acao", "REALIZADO"))
					.addOrder(Order.desc("data"))
					.uniqueResult();
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		return resultadoExame;
	}
	
	public ResultadoExame getByIdLoadAll(Object id) throws Exception {
		return this.getById(id, this.functionLoadAll);
	}
	
	private ResultadoExame loadEmpregadoConvocacao(ResultadoExame resultadoExame) {
		if(resultadoExame.getEmpregadoConvocacao() != null) {
			EmpregadoConvocacao empregadoConvocacao = 
					(EmpregadoConvocacao) Hibernate.unproxy(resultadoExame.getEmpregadoConvocacao());
			
			empregadoConvocacao.setConvocacao((Convocacao) Hibernate.unproxy(empregadoConvocacao.getConvocacao()));
			empregadoConvocacao.setEmpregado((Empregado) Hibernate.unproxy(empregadoConvocacao.getEmpregado()));
			empregadoConvocacao.getEmpregado().setPessoa((Pessoa) Hibernate.unproxy(empregadoConvocacao.getEmpregado().getPessoa()));
			empregadoConvocacao.getEmpregado().setGerencia((Gerencia)Hibernate.unproxy(empregadoConvocacao.getEmpregado().getGerencia()));
			
			resultadoExame.setEmpregadoConvocacao(empregadoConvocacao);
		}
		
		return resultadoExame;
	}
	
	private ResultadoExame loadItemResultadoExames(ResultadoExame resultadoExame) {
		if (resultadoExame.getItemResultadoExames() != null)
			Hibernate.initialize(resultadoExame.getItemResultadoExames());
		
		return resultadoExame;
	}
	
	public PagedList<ResultadoExame> getListFunctionLoad(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoad);
	}
	
	public PagedList<ResultadoExame> getListFunctionLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
	
	public PagedList<ResultadoExame> getListLoadItemResultadoExames(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.loadItemResultadoExames);
	}
}
