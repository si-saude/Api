package br.com.saude.api.model.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;

import br.com.saude.api.generic.GenericDao;
import br.com.saude.api.generic.GenericExampleBuilder;
import br.com.saude.api.generic.Helper;
import br.com.saude.api.generic.HibernateHelper;
import br.com.saude.api.generic.PagedList;
import br.com.saude.api.model.entity.po.Atendimento;
import br.com.saude.api.model.entity.po.Empregado;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacional;
import br.com.saude.api.model.entity.po.FilaAtendimentoOcupacionalAtualizacao;
import br.com.saude.api.model.entity.po.FilaEsperaOcupacional;
import br.com.saude.api.model.entity.po.Localizacao;
import br.com.saude.api.model.entity.po.Pessoa;
import br.com.saude.api.model.entity.po.Profissional;
import br.com.saude.api.model.entity.po.Tarefa;
import br.com.saude.api.util.constant.StatusFilaAtendimentoOcupacional;
import br.com.saude.api.util.constant.StatusTarefa;
import br.com.saude.api.model.entity.po.Aso;

public class AtendimentoDao extends GenericDao<Atendimento> {

	private static AtendimentoDao instance;
	
	private AtendimentoDao() {
		super();
	}
	
	public static AtendimentoDao getInstance() {
		if(instance == null)
			instance = new AtendimentoDao();
		return instance;
	}

	@Override
	protected void initializeFunctions() {
		this.functionLoadAll = atendimento -> {
			if(atendimento.getTarefa() != null)
				atendimento.setTarefa((Tarefa) Hibernate.unproxy(atendimento.getTarefa()));
			
			if(atendimento.getAso() != null)
				atendimento.setAso((Aso)Hibernate.unproxy(atendimento.getAso()));
			
			if(atendimento.getFilaAtendimentoOcupacional().getLocalizacao() != null)
				atendimento.getFilaAtendimentoOcupacional().setLocalizacao(
						(Localizacao) Hibernate.unproxy(atendimento.getFilaAtendimentoOcupacional().getLocalizacao()));
			
			if(atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() != null) {
				List<FilaAtendimentoOcupacionalAtualizacao> list = new ArrayList<FilaAtendimentoOcupacionalAtualizacao>();
				atendimento.getFilaAtendimentoOcupacional().getAtualizacoes().forEach(a -> {
					list.add((FilaAtendimentoOcupacionalAtualizacao) Hibernate.unproxy(a));
				});
				atendimento.getFilaAtendimentoOcupacional().setAtualizacoes(list);
			}				
			
			if(atendimento.getFilaEsperaOcupacional().getLocalizacao() != null)
				atendimento.getFilaEsperaOcupacional().setLocalizacao(
						(Localizacao) Hibernate.unproxy(atendimento.getFilaEsperaOcupacional().getLocalizacao()));
			
			return atendimento;
		};
		
		this.functionBeforeSave = pair -> {
			Atendimento atendimento = pair.getValue0();
			
			if(atendimento.getFilaAtendimentoOcupacional().getAtualizacoes() != null)
				atendimento.getFilaAtendimentoOcupacional().getAtualizacoes().forEach(a->
					a.setFila(atendimento.getFilaAtendimentoOcupacional()));
			
			return atendimento;
		};
	}
	
	public void devolverPraFila(Atendimento atendimento) throws Exception {
		Session session = HibernateHelper.getSession();
		
		try {
			Transaction transaction = session.beginTransaction();
						
			//SALVAR ATENDIMENTO E DELETAR
			session.merge(atendimento);
			
			StringBuilder queryBuilder = new StringBuilder("DELETE ");
			queryBuilder.append(atendimento.getClass().getSimpleName());
			queryBuilder.append(" WHERE id = :id");
			
			session.createQuery(queryBuilder.toString()).setParameter("id", atendimento.getId()).executeUpdate();
			
			transaction.commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			HibernateHelper.close(session);
		}
	}
	
	public Atendimento getByIdLoadAll(Object id) throws Exception {
		return super.getById(id,this.functionLoadAll);
	}
	
	public PagedList<Atendimento> getListLoadAll(GenericExampleBuilder<?, ?> exampleBuilder) throws Exception {
		return super.getList(exampleBuilder,this.functionLoadAll);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Atendimento> getListFilaAtendimentoLocalizacaoDoMomento(Atendimento atendimento){
		
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		Session session = HibernateHelper.getSession();
		
		try {
			
			Date inicio = Date.from(Helper.getToday().toInstant());
			Date fim = Date.from(inicio.toInstant());
			
			fim.setHours(23);
			fim.setMinutes(59);
			
			Criteria criteria = session.createCriteria(Atendimento.class,"a");
			
			//MONTAR CRITÉRIO
			criteria.createAlias("a.filaAtendimentoOcupacional", "filaAtendimentoOcupacional", 
					JoinType.RIGHT_OUTER_JOIN)
				.createAlias("filaAtendimentoOcupacional.localizacao", "localizacao", JoinType.INNER_JOIN)
				.createAlias("filaAtendimentoOcupacional.profissional", "profissional", JoinType.LEFT_OUTER_JOIN)
				.createAlias("profissional.empregado", "empregado", JoinType.LEFT_OUTER_JOIN)
				.createAlias("empregado.pessoa", "pessoa", JoinType.LEFT_OUTER_JOIN)
				.createCriteria("a.tarefa", "tarefa", JoinType.LEFT_OUTER_JOIN, 
					Restrictions.eq("tarefa.status", StatusTarefa.getInstance().EXECUCAO));
				criteria.createAlias("a.filaEsperaOcupacional", "filaEsperaOcupacional", JoinType.LEFT_OUTER_JOIN,
						Restrictions.conjunction().add(
								Subqueries.exists(
										DetachedCriteria.forClass(Atendimento.class,"a2")
										.createAlias("a2.tarefa", "t")
										.setProjection(Projections.id())
										.add(Restrictions.eqProperty("a.id", "a2.id"))
										.add(Restrictions.eq("t.status", StatusTarefa.getInstance().EXECUCAO)))))
				.createAlias("filaEsperaOcupacional.empregado", "emp", JoinType.LEFT_OUTER_JOIN)
				.createAlias("emp.pessoa", "pes", JoinType.LEFT_OUTER_JOIN);
			
			criteria
				.add(Restrictions.between("filaAtendimentoOcupacional.inicio", inicio, fim))
				.add(Restrictions.eq("localizacao.id",
						atendimento.getFilaAtendimentoOcupacional().getLocalizacao().getId()))
				.add(Restrictions.or(
						Restrictions.and(Restrictions.ne("filaAtendimentoOcupacional.status", 
											StatusFilaAtendimentoOcupacional.getInstance().EM_ATENDIMENTO), 
										Restrictions.ne("filaAtendimentoOcupacional.status", 
											StatusFilaAtendimentoOcupacional.getInstance().AGUARDANDO_EMPREGADO)), 
						Restrictions.isNotNull("tarefa.id")));
			
			ProjectionList pL = Projections.projectionList();
			pL.add(Projections.groupProperty("pessoa.nome"));
			pL.add(Projections.groupProperty("filaAtendimentoOcupacional.status"));
			pL.add(Projections.groupProperty("pes.nome"));
			criteria.setProjection(pL);
			criteria.addOrder(Order.asc("filaAtendimentoOcupacional.status"));
			criteria.addOrder(Order.asc("pessoa.nome"));
			
			List<Object[]> list = criteria.list();
			Atendimento a = null;
			
			for(Object[] obj : list) {
				a = new Atendimento();
				a.setFilaAtendimentoOcupacional(new FilaAtendimentoOcupacional());
				a.getFilaAtendimentoOcupacional().setProfissional(new Profissional());
				a.getFilaAtendimentoOcupacional().getProfissional().setEmpregado(new Empregado());
				a.getFilaAtendimentoOcupacional().getProfissional().getEmpregado().setPessoa(new Pessoa());
				a.getFilaAtendimentoOcupacional().getProfissional().getEmpregado().getPessoa().setNome((String)obj[0]);
				a.getFilaAtendimentoOcupacional().setStatus((String)obj[1]);
				
				if(obj[2] != null) {
					a.setFilaEsperaOcupacional(new FilaEsperaOcupacional());
					a.getFilaEsperaOcupacional().setEmpregado(new Empregado());
					a.getFilaEsperaOcupacional().getEmpregado().setPessoa(new Pessoa());
					a.getFilaEsperaOcupacional().getEmpregado().getPessoa().setNome((String)obj[2]);
				}
				
				atendimentos.add(a);
			}
		}catch (Exception ex) {
			throw ex;
		}
		finally {
			HibernateHelper.close(session);
		}
		
		return atendimentos;
	}
}
