package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.Exame;
import br.com.saude.api.model.entity.po.ResultadoExame;

public class ConvocacaoDao extends GenericDao<Convocacao> {

	private static ConvocacaoDao instance;
	
	private ConvocacaoDao() {
		super();
	}
	
	public static ConvocacaoDao getInstance() {
		if(instance == null)
			instance = new ConvocacaoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = convocacao -> {
			convocacao = loadProfissiograma(convocacao);
			convocacao = loadGerenciaConvocacoes(convocacao);
			convocacao = loadEmpregadoConvocacoes(convocacao);
			return convocacao;
		};
		
		this.functionBeforeSave = pair -> {
			Convocacao convocacao = pair.getValue0();
			Session session = pair.getValue1();
			
			List<Exame> exames = new ArrayList<Exame>();
			
			convocacao.getEmpregadoConvocacoes().forEach(e->{
				e.getEmpregadoConvocacaoExames().forEach(ex->{
					int index = exames.indexOf(ex.getExame());
					if(index >= 0)
						ex.setExame(exames.get(index));
					else {
						ex.setExame(session.get(Exame.class, ex.getExame().getId()));
						exames.add(ex.getExame());
					}
				});
			});
			
			return convocacao;
		};
	}
	
	private Convocacao loadProfissiograma(Convocacao convocacao) {
		if(convocacao.getProfissiograma() != null)
			Hibernate.initialize(convocacao.getProfissiograma());
		return convocacao;
	}
	
	private Convocacao loadGerenciaConvocacoes(Convocacao convocacao) {
		if(convocacao.getGerenciaConvocacoes() != null)
			Hibernate.initialize(convocacao.getGerenciaConvocacoes());
		return convocacao;
	}
	
	private Convocacao loadEmpregadoConvocacoes(Convocacao convocacao) {
		if(convocacao.getEmpregadoConvocacoes() != null) {
			Hibernate.initialize(convocacao.getEmpregadoConvocacoes());
			
			convocacao.getEmpregadoConvocacoes().forEach(eC->{
				if(eC.getEmpregado() != null) {
					Hibernate.initialize(eC.getEmpregado());
					
					if(eC.getEmpregado().getGerencia() != null)
						Hibernate.initialize(eC.getEmpregado().getGerencia());
					
					if(eC.getEmpregado().getCargo() != null)
						Hibernate.initialize(eC.getEmpregado().getCargo());
					
					if(eC.getEmpregado().getFuncao() != null)
						Hibernate.initialize(eC.getEmpregado().getFuncao());
					
					if(eC.getEmpregado().getGrupoMonitoramentos() != null) {
						Hibernate.initialize(eC.getEmpregado().getGrupoMonitoramentos());
					}
				}
				
				if(eC.getEmpregadoConvocacaoExames() != null)
					Hibernate.initialize(eC.getEmpregadoConvocacaoExames());
			});
		}
		return convocacao;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Convocacao getByIdGerencia(Convocacao convocacao) {
		Session session = HibernateHelper.getSession();
		
		try {
			
			convocacao.setEmpregadoConvocacoes(
						session
							.createCriteria(EmpregadoConvocacao.class)
							.createAlias("convocacao", "convocacao")
							.createAlias("empregado", "empregado")
							.createAlias("empregado.gerencia", "gerencia")
							.add(Restrictions.eq("convocacao.id", convocacao.getId()))
							.add(Restrictions.eq("gerencia.id", convocacao.getGerenciaConvocacoes().get(0).getGerencia().getId()))
							.list());
			
			if(convocacao.getEmpregadoConvocacoes() != null)
				convocacao.getEmpregadoConvocacoes().forEach(e->{
					Hibernate.initialize(e.getEmpregado());
					Hibernate.initialize(e.getEmpregado().getGerencia());
					Hibernate.initialize(e.getEmpregadoConvocacaoExames());
				});
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		return convocacao;
	}
	
	public Convocacao getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Convocacao> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder, this.functionLoadAll);
	}
	
	public void saveList(List<Convocacao> convocacoes, List<EmpregadoConvocacao> empregadoConvocacoes, List<ResultadoExame> resultadoExames) throws Exception {
		Session session = HibernateHelper.getSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			
			for (Convocacao c : convocacoes) {
				Convocacao aux =  (Convocacao) session.merge(c);
				c.setId(aux.getId());
			}
			
			for (EmpregadoConvocacao ec : empregadoConvocacoes) {
				EmpregadoConvocacao aux =  (EmpregadoConvocacao) session.merge(ec);
				ec.setId(aux.getId());
			}
			
			for (ResultadoExame re : resultadoExames) {
				ResultadoExame aux =  (ResultadoExame) session.merge(re);
				re.setId(aux.getId());
			}
			
			transaction.commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			HibernateHelper.close(session);
		}
	}
}
