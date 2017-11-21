package br.com.saude.api.model.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.model.entity.po.Convocacao;
import br.com.saude.api.model.entity.po.EmpregadoConvocacao;
import br.com.saude.api.model.entity.po.Exame;

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
			
			convocacao.getEmpregadoConvocacoes().forEach(e->{
				e.getExames().forEach(ex->ex = session.get(Exame.class, ex.getId()));
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
						
						eC.getEmpregado().getGrupoMonitoramentos().forEach(g->{
							if(g.getGrupoMonitoramentoExames() != null)
								Hibernate.initialize(g.getGrupoMonitoramentoExames());
						});
					}
				}
				
				if(eC.getExames() != null)
					Hibernate.initialize(eC.getExames());
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
					Hibernate.initialize(e.getExames());
				});
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			session.close();
		}
		
		return convocacao;
	}
	
	public Convocacao getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
}
